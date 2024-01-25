package dev.rodriigo.minecraft.util;

import org.bukkit.ChatColor;

import java.util.regex.Pattern;

public abstract class MessageColorizer {

    static Pattern HEX_PATTERN = Pattern.compile("#[0-9a-fA-F]{6}");

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
