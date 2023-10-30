package com.xism4.sternalboard;

import net.elytrium.serializer.NameStyle;
import net.elytrium.serializer.SerializerConfig;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.Transient;

import java.nio.file.Paths;

public class BukkitConfigurationImpl extends SafeYamlSerializable {

    @Transient
    private static final SerializerConfig BUKKIT_CONFIG = new SerializerConfig.Builder()
            .setCommentValueIndent(1)
            .setFieldNameStyle(NameStyle.MACRO_CASE)
            .setNodeNameStyle(NameStyle.KEBAB_CASE)
            .build();


    public static final BukkitConfigurationImpl IMP = new BukkitConfigurationImpl();

    public BukkitConfigurationImpl() {
        super(Paths.get("config.yml"), BUKKIT_CONFIG);
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
    public boolean ANIMATED_SCOREBOARD = false;

    @Comment(value = {
            @CommentValue(
                    "Select scoreboard mode:NORMAL, WORLD, PERMISSION"
            ),
            @CommentValue(
                    "NORMAL - scoreboard for all players"
            ),
            @CommentValue(
                    "WORLD-scoreboard per world, u can configure in scoreboard - world section"
            ),
            @CommentValue(
                    "PERMISSION-scoreboard per permission, you can configure in scoreboard - permission section"
            )
    })
    public String MODE = "NORMAL";

    @Comment(value = {
            @CommentValue(
                    "Scoreboard update interval, default 20 ticks"
            ),
            @CommentValue(
                    "If you have lag, you can increase this value"
            )
    })
    public int SCOREBOARD_INTERVAL_UPDATE = 20;
}
