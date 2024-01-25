package dev.rodriigo.minecraft.util;

import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class MessageColorFormatter {

    static Pattern HEX_PATTERN = Pattern.compile("^#(?:[0-9a-fA-F]{3}){1,2}$");
    static boolean IS_LEGACY = BackendPlugin.getInstance().isLegacy();

    public static String colorize(String message) {
        // Check if the server is legacy
        if (!IS_LEGACY) {
            // Replace all HEX colors
            Matcher matcher = HEX_PATTERN.matcher(message);
            while (matcher.find()) {
                String hex = matcher.group();
                // The Bungee's API Text Component (Integrated with Spigot API)
                // process the HEX colors this way: #fff -> &x&f&f&f
                // we only need to replace it, don't use any reflection to do it
                message = message.replace(
                        hex,
                        "&" + Arrays.stream(hex.substring(1)
                                .split(""))
                                .map(a -> "&"+a).collect(Collectors.joining(""))
                );
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
