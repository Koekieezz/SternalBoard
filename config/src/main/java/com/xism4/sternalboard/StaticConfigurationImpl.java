package com.xism4.sternalboard;

import net.elytrium.serializer.NameStyle;
import net.elytrium.serializer.SerializerConfig;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.Transient;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StaticConfigurationImpl extends SafeYamlSerializable {

    @Transient
    private static final SerializerConfig BUKKIT_CONFIG = new SerializerConfig.Builder()
            .setCommentValueIndent(1)
            .setFieldNameStyle(NameStyle.MACRO_CASE)
            .setNodeNameStyle(NameStyle.KEBAB_CASE)
            .build();


    public static final StaticConfigurationImpl IMP = new StaticConfigurationImpl();

    public StaticConfigurationImpl() {
        super(Paths.get("SternalBoard", "config.yml"), BUKKIT_CONFIG);
    }

    @Comment(value = {
            @CommentValue("SternalBoard - Scoreboard plugin for Spigot and paper servers"),
            @CommentValue(""),
            @CommentValue("Version: 2.2.6"),
            @CommentValue("Author: xism4 and Jonakls"),
            @CommentValue(""),
            @CommentValue("Support discord: https://discord.com/invite/jmWk4pR2SG"),
            @CommentValue("Spigot: https://www.spigotmc.org/resources/sternalboard-lightweight-animated-scoreboard.89245/"),
            @CommentValue("Polymart: https://polymart.org/resource/sternalboard-lightweight.1379"),
            @CommentValue("MC-Market: https://www.mc-market.org/resources/20395/"),
            @CommentValue("GitHub: https://github.com/ShieldCommunity/SternalBoard"),
            @CommentValue("If you have any problem, contact us in discord or report issue in GitHub section"),
            @CommentValue("Issues: https://github.com/ShieldCommunity/SternalBoard/issues")
    })

    @Comment(value = {
            @CommentValue(
                    "Select scoreboard mode:NORMAL, WORLD, PERMISSION"
            ),
            @CommentValue(
                    "NORMAL - scoreboard for all players"
            ),
            @CommentValue(
                    "WORLD-scoreboard per world, you can configure in scoreboard - world section"
            ),
            @CommentValue(
                    "PERMISSION-scoreboard per permission, you can configure in scoreboard - permission section"
            )
    })
    public String MODE = "NORMAL";

    @Comment(value = {
            @CommentValue(
                    "Should the scoreboard be animated? This will be modified from another config"
            )
    })
    public boolean ANIMATED_BOARD = false;

    @Comment(value = {
            @CommentValue(
                    "Scoreboard update interval, default 20 ticks"
            ),
            @CommentValue(
                    "If you have lag, you can increase this value"
            )
    })
    public int SCOREBOARD_INTERVAL_UPDATE = 20;

    @Comment(value = {
            @CommentValue(
                    "Scoreboard title"
            )
    })
    public String DEFAULT_SCOREBOARD_TITLE = "&e&lSternal&f&lBoard";

    @Comment(value = {
            @CommentValue(
                    "Lines for the scoreboard"
            )
    })
    public List<String> SCOREBOARD_LINES = Arrays.asList(
            "&8&m+-------------------+",
            "",
            "&a&l▼ &bData:",
            "&f● &7Nick: &b%player_name%",
            "&f● &7Rank: &b%vault_rank%",
            "&f● &7Coins: &e%vault_eco_balance_formatted%",
            "",
            "&a&l▼ &bExample:",
            "&f● &7Lobby: &b#1",
            "&f● &7Ping: &b%player_ping%",
            "&f● &7Players: &b%bungee_total%",
            "",
            "&f● &fIP: &bplay.yournetwork.com"
    );

    @Comment(value = {
            @CommentValue(
                    "Should the scoreboard blacklist worlds?"
            )
    })
    public boolean WORLD_BLACKLIST_ENABLED = false;

    @Comment(value = {
            @CommentValue(
                    "Blacklisted worlds (Scoreboard will not appear)"
            )
    })
    public List<String> WORLD_BLACKLIST = Arrays.asList(
            "world1",
            "world_nether",
            "world_the_end"
    );
    @Comment(value = {
            @CommentValue("If permission does not exist or is not configured, will use scoreboard section"),
            @CommentValue("Remember if you use this mode, you need to add permissions to your permission plugin"),
            @CommentValue("Example: sternalboard.admin, sternalboard.mod, sternalboard.dev, sternalboard.owner"),
            @CommentValue("If you have op, you will see all scoreboards, remember deny nodes in your permission plugin")
            })

   public String NODE = "node";
        public String title = "title";

    public Set<String> PERMISSION_SCOREBOARD_LINES = new LinkedHashSet<>(Arrays.asList(
            "&8&m+-------------------+",
            "",
            "&a&l▼ &bData:",
            "&f● &7Nick: &b%player_name%",
            "&f● &7Rank: &b%vault_rank%",
            "&f● &7Coins: &e%vault_eco_balance_formatted%",
            "",
            "&a&l▼ &bExample:",
            "&f● &7Lobby: &b#1",
            "&f● &7Ping: &b%player_ping%",
            "&f● &7Players: &b%bungee_total%",
            "",
            "&f● &fIP: &bplay.yournetwork.com"
    ));
    }

