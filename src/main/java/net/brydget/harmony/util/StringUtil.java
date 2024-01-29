package net.brydget.harmony.util;

import java.util.regex.Pattern;

public abstract class StringUtil {

    /**
     * Calculates the Levenshtein distance between two strings.
     *
     * @param  s1  the first input string
     * @param  s2  the second input string
     * @return     the Levenshtein distance between s1 and s2
     */
    public static int distance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int difference(String s1, String s2) {
        return distance(s1, s2);
    }

    /**
     * Capitalizes the first letter of the input string.
     *
     * @param  str  the input string to be capitalized
     * @return      the capitalized string
     */
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Returns a new string with the first letter of the input string converted to lowercase.
     *
     * @param  str  the input string
     * @return      the modified string
     */
    public static String unCapitalize(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * Converts the input string to lowercase.
     *
     * @param  str  the input string to be converted
     * @return      the lowercase version of the input string
     */
    public static String lowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * Returns the uppercase version of the input string.
     *
     * @param  str  the input string
     * @return     the uppercase version of the input string
     */
    public static String upperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * Trims the input string.
     *
     * @param  str  the input string to be trimmed
     * @return      the trimmed string
     */
    public static String trim(String str) {
        return str.trim();
    }

    /**
     * Check if two strings are strictly equal.
     *
     * @param  s1  the first string
     * @param  s2  the second string
     * @return     true if the strings are strictly equal, false otherwise
     */
    public static boolean strictEquals(String s1, String s2) {
        return s1.equals(s2);
    }

    /**
     * Checks if the input string matches the given regular expression pattern.
     *
     * @param  s1 the input string to be checked
     * @param  s2 the regular expression pattern to match
     * @return    true if the input string matches the regular expression pattern, false otherwise
     */
    public static boolean strictMatches(String s1, String s2) {
        return s1.matches(s2);
    }

    /**
     * A function to check if a string matches a given pattern.
     *
     * @param  s1 The input string to be checked.
     * @param  s2 The pattern to match against the input string.
     * @return    True if the pattern is found in the input string, otherwise false.
     */
    public static boolean matches(String s1, String s2) {
        return Pattern.compile(s2).matcher(s1).find();
    }

}
