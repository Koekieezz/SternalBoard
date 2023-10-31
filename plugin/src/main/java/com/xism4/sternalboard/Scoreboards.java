package com.xism4.sternalboard;

import com.xism4.sternalboard.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Scoreboards {

    public static void updateFromSection(
            SternalBoardPlugin plugin, SternalBoard handler
    ) {

       String title = BukkitConfigurationImpl.IMP.DEFAULT_SCOREBOARD_TITLE;
       List<String> lines = new ArrayList<>(BukkitConfigurationImpl.IMP.SCOREBOARD_LINES);

        if (title == null) {
            title = handler.getTitle();
        }

        if (lines.isEmpty()) {
            lines = handler.getLines();
        }

        lines.replaceAll(line -> TextUtils.processPlaceholders(plugin, handler.getPlayer(), line));

        handler.updateTitle(TextUtils.processPlaceholders(plugin, handler.getPlayer(), title));
        handler.updateLines(lines);
    }

}
