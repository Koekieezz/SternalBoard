package com.xism4.sternalboard.managers;

import com.xism4.sternalboard.BukkitConfigurationImpl;
import com.xism4.sternalboard.Scoreboards;
import com.xism4.sternalboard.SternalBoard;
import com.xism4.sternalboard.SternalBoardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreboardManager {

    private final SternalBoardPlugin plugin;
    private final Map<UUID, SternalBoard> boardHandlerMap;
    private Integer updateTask;

    public ScoreboardManager(SternalBoardPlugin plugin) {
        this.plugin = plugin;
        this.boardHandlerMap = new ConcurrentHashMap<>();
    }

    public Map<UUID, SternalBoard> getBoardsHandler() {
        return boardHandlerMap;
    }

    public void init() {
        String scoreboardMode = BukkitConfigurationImpl.IMP.MODE.toUpperCase(Locale.ROOT);
        int updateTime = BukkitConfigurationImpl.IMP.SCOREBOARD_INTERVAL_UPDATE;
        BukkitConfigurationImpl.IMP.save();

        if (updateTime <= 0) {
            BukkitConfigurationImpl.IMP.save();
            updateTime = 20;
            BukkitConfigurationImpl.IMP.save();
        }

        if (plugin.isAnimationEnabled()) {
            return;
        }

        updateTask = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () ->
                boardHandlerMap.forEach((context, handler) -> {
            switch (scoreboardMode) {
                case "WORLD":
                    processWorldScoreboard(handler);
                    break;
                case "PERMISSION":
                    processPermissionScoreboard(handler);
                    break;
                case "NORMAL":
                default:
                    Scoreboards.updateFromSection(plugin, handler);
                    break;
            }
        }), 0, updateTime).getTaskId();
    }

    public void setScoreboard(Player player) {
        SternalBoard handler = new SternalBoard(player);
        FileConfiguration config = plugin.getConfig();

        if (plugin.isWorldEnabled() && plugin.isAnimationEnabled() && config.getInt("settings.scoreboard.update") != 0)
            return;

        Scoreboards.updateFromSection(plugin, handler);
        boardHandlerMap.put(player.getUniqueId(), handler);
    }

    public void removeScoreboard(Player player) {
        SternalBoard board = boardHandlerMap.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public void reload() {
        if (updateTask != null) {
            Bukkit.getServer().getScheduler().cancelTask(updateTask);
        }

        // TODO: 30/11/2022 view this condition, is it necessary?
        if (plugin.isAnimationEnabled() && updateTask != null) {
            for (SternalBoard board : this.boardHandlerMap.values()) {
                board.updateLines("");
            }
        }
        init();
    }

    public void toggle(Player player) {
        SternalBoard oldBoard = boardHandlerMap.remove(player.getUniqueId());
        if (oldBoard != null) {
            oldBoard.delete();
        } else {
            setScoreboard(player);
        }
    }

    // STATIC BOARD FEATURES
    private void processWorldScoreboard(SternalBoard handler) {
        String worldName = handler.getPlayer().getWorld().getName();

        Scoreboards.updateFromSection(plugin, handler);
    }

    private void processPermissionScoreboard(SternalBoard handler) {
        Set<String> permissions = BukkitConfigurationImpl.IMP.PERMISSION_SCOREBOARD_LINES;

        String permissionNode;
        for (String key : permissions) {
            permissionNode = BukkitConfigurationImpl.IMP.NODE;
            if (permissionNode == null) continue;
            if (handler.getPlayer().hasPermission(permissionNode)) {
                break;
            }
        }

        Scoreboards.updateFromSection(plugin, handler);
    }

    /**
     * Verifies if the player is in a world that is blacklisted.
     */
    public void setBoardAfterCheck(Player player) {
        ScoreboardManager manager = plugin.getScoreboardManager();

        if (!BukkitConfigurationImpl.IMP.WORLD_BLACKLIST_ENABLED) {
            if (manager.getBoardsHandler().containsKey(player.getUniqueId())) {
                return;
            }

            manager.setScoreboard(player);
        }

        //No need to check if world is null since player is always on a world
        List<String> worldBlacklist = BukkitConfigurationImpl.IMP.WORLD_BLACKLIST;


        if (worldBlacklist.contains(player.getWorld().getName())) {
            manager.removeScoreboard(player);
            return;
        }

        manager.setScoreboard(player);
    }
}
