package net.brydget.harmony.util;

import net.brydget.harmony.annotation.NeverNull;
import org.bukkit.entity.Player;

public class StringVariableReplace {

    static String PLAYER_NAME_REGEX = "(?i)\\{player}|(?i)%player%";
    static String PLAYER_NICKNAME_REGEX = "(?i)\\{nickname}|(?i)%nickname%";
    static String PLAYER_WORLD_REGEX = "(?i)\\{world}|(?i)%world%";
    static String PLAYER_GAME_MODE_REGEX = "(?i)\\{gamemode}|(?i)%gamemode%";

    // Replace {variables} and %variables%
    // from strings

    public static String replace(
            @NeverNull String string,
            @NeverNull Player player
    ) {

        return string
                .replaceAll(
                        PLAYER_NAME_REGEX,
                        player.getName()
                ).replaceAll(
                        PLAYER_WORLD_REGEX,
                        player.getWorld().getName()
                ).replaceAll(
                        PLAYER_GAME_MODE_REGEX,
                        player.getGameMode().name()
                ).replaceAll(
                        PLAYER_NICKNAME_REGEX,
                        player.getDisplayName()
                );

    }


}
