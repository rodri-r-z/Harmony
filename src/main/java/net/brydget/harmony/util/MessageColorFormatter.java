package net.brydget.harmony.util;

import net.brydget.harmony.BackendPlugin;
import org.bukkit.ChatColor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class MessageColorFormatter {

    static String HEX_PATTERN_STRING = "#(?:[0-9a-fA-F]{3}){1,2}";
    static String GRADIENT_COLOR_PATTERN_STRING = "\\{gradient:"+HEX_PATTERN_STRING+":"+HEX_PATTERN_STRING+"}";
    static Pattern GRADIENT_COLOR_PATTERN = Pattern.compile(GRADIENT_COLOR_PATTERN_STRING, Pattern.CASE_INSENSITIVE);
    static Pattern HEX_PATTERN = Pattern.compile("^"+HEX_PATTERN_STRING+"$");
    static boolean IS_LEGACY = BackendPlugin.getInstance().isLegacy();

    public static String colorize(String message) {
        // Check if the server is legacy
        if (!IS_LEGACY) {
            // Replace all HEX colors
            Matcher matcher = HEX_PATTERN.matcher(message);
            Matcher gradientMatcher = GRADIENT_COLOR_PATTERN.matcher(message);
            while (matcher.find()) {
                String hex = matcher.group();
                // The Bungee's API Text Component (Integrated with Spigot API)
                // process the HEX colors this way: #fff -> &x&f&f&f
                // we only need to replace it, don't use any reflection to do it
                message = message.replace(
                        hex,
                        "&x" + Arrays.stream(hex.substring(1)
                                        .split(""))
                                .map(a -> "&" + a).collect(Collectors.joining(""))
                );
            }

            while (gradientMatcher.find()) {
                String hex = matcher.group();

                // Split the HEX gradient into two colors
                String[] hexArray = hex.split(":");

                String from = hexArray[0].replaceFirst("(?i)\\{gradient:", "");
                String to = hexArray[1];
                message = colorize(message.replace(hex, ""), from, to);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorize(Iterable<String> message) {
        return colorize(String.join("\n", message));
    }

    public static String colorize(String... message) {
        return colorize(String.join("\n", message));
    }

    public static String colorize(List<String> message) {
        return colorize(String.join("\n", message));
    }

    // Code from
    // https://www.spigotmc.org/threads/gradient-chat-particles.470496/

    static double[] interpolate(double from, double to, int max) {
        final double[] res = new double[max];
        for (int i = 0; i < max; i++) {
            res[i] = from + i * ((to - from) / (max - 1));
        }
        return res;
    }

    /**
     * Interpolates am RGB gradient onto the input string using the given colors.
     *
     * @param  str  the input string to colorize
     * @param  from the starting color
     * @param  to   the ending color
     * @return      the colorized string
     */
    public static String rgbGradient(String str, Color from, Color to) {

        // interpolate each component separately
        final double[] red = interpolate(from.getRed(), to.getRed(), str.length());
        final double[] green = interpolate(from.getGreen(), to.getGreen(), str.length());
        final double[] blue = interpolate(from.getBlue(), to.getBlue(), str.length());

        final StringBuilder builder = new StringBuilder();

        // create a string that matches the input-string but has
        // the different color applied to each char
        for (int i = 0; i < str.length(); i++) {
            builder.append(colorize(rgbToHex(
                            (int) Math.round(red[i]),
                            (int) Math.round(green[i]),
                            (int) Math.round(blue[i]))))

                    .append(str.charAt(i));
        }

        return builder.toString();
    }

    /**
     * Returns a string after applying a gradient from one color to another.
     *
     * @param  str   the input string
     * @param  from  the starting color of the gradient
     * @param  to    the ending color of the gradient
     * @return       the string after applying the color gradient
     */
    public static String gradient(String str, Color from, Color to) {
        return rgbGradient(str, from, to);
    }

    /**
     * Generates a gradient color string from the given input string using the
     * specified start and end colors.
     *
     * @param  str  the input string
     * @param  from the start color in hex format
     * @param  to   the end color in hex format
     * @return      the gradient color string
     */
    public static String gradient(String str, String from, String to) {
        return rgbGradient(str, getColorFromHex(from), getColorFromHex(to));
    }

    /**
     * colorize the given string from one color to another.
     *
     * @param  str  the string to colorize
     * @param  from the starting color
     * @param  to   the target color
     * @return      the colorized string
     */
    public static String colorize(String str, Color from, Color to) {
        return rgbGradient(str, from, to);
    }

    /**
     * Colorizes the given string from one color to another.
     *
     * @param  str  the input string to be colorized
     * @param  from the initial color in hex format
     * @param  to   the target color in hex format
     * @return      the colorized string
     */
    public static String colorize(String str, String from, String to) {
        return rgbGradient(str, getColorFromHex(from), getColorFromHex(to));
    }

    /**
     * Retrieves a Color object from a given hexadecimal string.
     *
     * @param  hex  the hexadecimal string representing the color
     * @return      the Color object created from the hexadecimal string
     */
    public static Color getColorFromHex(String hex) {
        int red = Integer.parseInt(hex.substring(1, 3), 16);
        int green = Integer.parseInt(hex.substring(3, 5), 16);
        int blue = Integer.parseInt(hex.substring(5, 7), 16);
        return new Color(red, green, blue);
    }

    public static String stripColors(String str, boolean includeHex) {
        return ChatColor.stripColor(str)
                .replaceAll(includeHex ? HEX_PATTERN_STRING : "", "")
                .replaceAll(includeHex ? GRADIENT_COLOR_PATTERN_STRING : "", "");
    }

    public static String stripColors(String str) {
        return stripColors(str, true);
    }

    static String rgbToHex(int red, int green, int blue) {
        return String.format("#%02x%02x%02x", red, green, blue);
    }

}
