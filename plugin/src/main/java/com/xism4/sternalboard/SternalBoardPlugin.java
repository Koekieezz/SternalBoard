package com.xism4.sternalboard;

import com.xism4.sternalboard.commands.SternalCommand;
import com.xism4.sternalboard.commands.completer.OldPaperTabCompleter;
import com.xism4.sternalboard.commands.completer.PaperTabCompleter;
import com.xism4.sternalboard.commands.completer.SpigotTabCompleter;
import com.xism4.sternalboard.listeners.ScoreboardListener;
import com.xism4.sternalboard.listeners.WorldScoreboardListener;
import com.xism4.sternalboard.managers.library.LibraryManager;
import com.xism4.sternalboard.managers.ScoreboardManager;
import com.xism4.sternalboard.managers.animation.AnimationManager;
import com.xism4.sternalboard.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SternalBoardPlugin extends JavaPlugin {

    private static final int STERNAL_ID_METRICS = 13409;

    public ScoreboardManager scoreboardManager;
    public AnimationManager animationManager;
    public BukkitConfiguration animConfig;
    public BukkitConfiguration config;
    public boolean animateScoreboard; //build


    @Override
    public void onLoad() {
        StaticConfigurationImpl settings = new StaticConfigurationImpl();
        AnimatedConfigurationImpl animatedSettings = new AnimatedConfigurationImpl();
        settings.reload(
                this.getDataFolder().toPath()
        );
        animatedSettings.reload(
                this.getDataFolder().toPath()
        );

        //keep for build now
        this.animConfig = new BukkitConfiguration(
                this, "animated-board"
        );

        setAnimateScoreboard(StaticConfigurationImpl.IMP.ANIMATED_BOARD);
    }

    @Override
    public void onEnable() {
        LibraryManager.loadLibs(this);
        loadScoreboardMgr();
        commandHandler();
        loadTabCompletions();
        eventHandler();

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> new Metrics(this, STERNAL_ID_METRICS));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public boolean isAnimationEnabled() {
        return animateScoreboard;
    }

    public boolean isWorldEnabled() {
        return StaticConfigurationImpl.IMP.WORLD_BLACKLIST_ENABLED;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public boolean placeholderCheck() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    // TODO 09/04/2023: review instance of FileConfiguration on reload class
    public @NotNull FileConfiguration getAnimConfig() {
        return animConfig.get();
    }

    // TODO 09/04/2023: review instance of FileConfiguration on reload class
    @Override
    public @NotNull FileConfiguration getConfig() {
        return config.get();
    }

    public void loadScoreboardMgr() {
        scoreboardManager = new ScoreboardManager(this);

        if (animateScoreboard) {
            setAnimationManager(new AnimationManager(this)
            );
        }

        scoreboardManager.init();
    }

    public void commandHandler() {
        Objects.requireNonNull(this.getCommand(
                "sternalboard")).setExecutor(new SternalCommand(this)
        );
    }

    public void loadTabCompletions() {
        try {
            Class.forName("com.destroystokyo.paper.event.server.AsyncTabCompleteEvent");
            getServer().getPluginManager().registerEvents(
                    componentCompletions()
                            ? new PaperTabCompleter()
                            : new OldPaperTabCompleter(),
                    this);
        } catch (ClassNotFoundException e) {
            Objects.requireNonNull(this.getCommand(
                    "sternalboard")).setTabCompleter(new SpigotTabCompleter()
            );
        }
    }

    public boolean componentCompletions() {
        try {
            Class<?> clazz = Class.forName(
                    "com.destroystokyo.paper.event.server.AsyncTabCompleteEvent$Completion"
            );
            Class<?> examinable = Class.forName("net.kyori.examination.Examinable");
            return clazz.isAssignableFrom(examinable);
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }

    public void eventHandler() {
        getServer().getPluginManager().registerEvents(
                new ScoreboardListener(this),
                this
        );
        getServer().getPluginManager().registerEvents(
                new WorldScoreboardListener(this),
                this
        );
    }

    public void setAnimateScoreboard(boolean animateScoreboard) {
        this.animateScoreboard = animateScoreboard;
    }

    public void setAnimationManager(AnimationManager animationManager) {
        this.animationManager = animationManager;
    }
}
