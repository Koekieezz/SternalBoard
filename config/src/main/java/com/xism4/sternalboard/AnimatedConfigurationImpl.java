package com.xism4.sternalboard;

import net.elytrium.serializer.NameStyle;
import net.elytrium.serializer.SerializerConfig;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.Transient;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AnimatedConfigurationImpl extends SafeYamlSerializable {

    @Transient
    private static final SerializerConfig BUKKIT_CONFIG = new SerializerConfig.Builder()
            .setCommentValueIndent(1)
            .setFieldNameStyle(NameStyle.MACRO_CASE)
            .setNodeNameStyle(NameStyle.KEBAB_CASE)
            .build();


    public static final AnimatedConfigurationImpl IMP = new AnimatedConfigurationImpl();

    public AnimatedConfigurationImpl() {
        super(Paths.get("SternalBoard","animated.yml"), BUKKIT_CONFIG);
    }

    @Comment(value = {
           @CommentValue(
                   "Title for the animated scoreboard"
           )
    })
    public String ANIMATED_SCOREBOARD_TITLE = "&b&lSternal&f&lBoard";

    @Comment(value = {
            @CommentValue(
                    "Interval to update scoreboard title"
            )
    })
    public int ANIMATED_SCOREBOARD_TITLE_UPDATE_RATE = 1;

    @Comment(value = {
            @CommentValue(
                    "Lines for the scoreboard"
            )
    })
    public List<String> ANIMATED_SCOREBOARD_LINES = Arrays.asList(
            "&f&lS&b&lternal&f&lBoard",
            "&f&lSt&b&lernal&f&lBoard",
            "&f&lSte&b&lrnal&f&lBoard",
            "&b&lS&f&lter&b&lnal&f&lBoard",
            "&b&lSt&f&lern&b&lal&f&lBoard",
            "&b&lSte&f&lrna&b&ll&f&lBoard",
            "&b&lSte&f&lrna&b&ll&f&lBoard",
            "&b&lSter&f&lnal&f&lBoard",
            "&b&lStern&f&lal&f&lBoard",
            "&b&lSterna&f&ll&f&lBoard",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&b&lB&f&loard",
            "&b&lSternal&b&lBo&f&lard",
            "&b&lSternal&b&lBoa&f&lrd",
            "&b&lSternal&f&lB&b&loar&f&ld",
            "&b&lSternal&f&lBo&b&lard",
            "&b&lSternal&f&lBoa&b&lrd",
            "&b&lSternal&f&lBoar&b&ld",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&f&lBoard",
            "&b&bSternal&f&blBoard",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&f&lBoard",
            "&b&lSternal&f&lBoard"
    );

}
