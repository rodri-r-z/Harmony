package net.brydget.util;

public abstract class ValidateUtil {

    static String VISA_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";
    static String MASTERCARD_REGEX = "^5[1-5][0-9]{14}$";
    public static String AMEX_REGEX = "^3[47][0-9]{13}$";
    public static String DISCOVER_REGEX = "^6(?:011|5[0-9]{2})[0-9]{12}$";
    public static String DINERS_CLUB_REGEX = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";
    public static String JCB_REGEX = "^(?:2131|1800|35\\d{3})\\d{11}$";
    public static String NUMERIC_REGEX = "^[0-9]+$";
    public static String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";
    public static String UUID_REGEX = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
    public static String IP_REGEX = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    public static String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static String URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    public static String IPV6_REGEX = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";


    /**
     * Checks if the given card number matches any of the known card number regex patterns.
     *
     * @param  str  the card number to be checked
     * @return      true if the card number matches any of the known card number regex patterns, false otherwise
     */
    public static boolean isCardNumber(String str) {
        return isVisaCard(str)
                || isMasterCard(str)
                || isAMEXCard(str)
                || isDiscoverCard(str)
                || isDinersClubCard(str)
                || isJCBCard(str);
    }

    /**
     * Checks if the input string is a Visa card.
     *
     * @param  str  the input string to be checked
     * @return     true if the input string is a Visa card, false otherwise
     */
    public static boolean isVisaCard(String str) {
        return StringUtil.strictMatches(str, VISA_REGEX);
    }

    /**
     * Checks if the given string is a MasterCard number.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a MasterCard number, false otherwise
     */
    public static boolean isMasterCard(String str) {
        return StringUtil.strictMatches(str, MASTERCARD_REGEX);
    }

    /**
     * Checks if the given string represents an American Express card.
     *
     * @param  str  the string to be checked
     * @return      true if the string matches the American Express card regex, otherwise false
     */
    public static boolean isAmericanExpressCard(String str) {
        return StringUtil.strictMatches(str, AMEX_REGEX);
    }

    /**
     * Checks if the given string represents an American Express card.
     *
     * @param  str  the string to be checked
     * @return      true if the string matches the American Express card regex, otherwise false
     */
    public static boolean isAMEXCard(String str) {
        return isAmericanExpressCard(str);
    }

    /**
     * Check if the given string is a Discover card number.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a Discover card number, false otherwise
     */
    public static boolean isDiscoverCard(String str) {
        return StringUtil.strictMatches(str, DISCOVER_REGEX);
    }

    /**
     * Checks if the given string represents a Diners Club card.
     *
     * @param  str  the string to be checked
     * @return      true if the string matches the Diners Club card regex, false otherwise
     */
    public static boolean isDinersClubCard(String str) {
        return StringUtil.strictMatches(str, DINERS_CLUB_REGEX);
    }

    /**
     * Checks if the input string is a JCB card.
     *
     * @param  str  the input string to be checked
     * @return      true if the input string is a JCB card, false otherwise
     */
    public static boolean isJCBCard(String str) {
        return StringUtil.strictMatches(str, JCB_REGEX);
    }

    /**
     * Check if the given string is numeric.
     *
     * @param  str  the string to be checked
     * @return     true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String str) {
        return StringUtil.strictMatches(str, NUMERIC_REGEX);
    }

    /**
     * Checks if the input string is alphanumeric.
     *
     * @param  str  the input string to be checked
     * @return      true if the input string is alphanumeric, false otherwise
     */
    public static boolean isAlphaNumeric(String str) {
        return StringUtil.strictMatches(str, ALPHANUMERIC_REGEX);
    }

    /**
     * Checks if the given string is a valid UUID.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a valid UUID, false otherwise
     */
    public static boolean isUUID(String str) {
        return StringUtil.strictMatches(str, UUID_REGEX);
    }

    /**
     * Check if the input string is a valid boolean value.
     *
     * @param  str  the string to be checked
     * @return     true if the string is "true" or "false", false otherwise
     */
    public static boolean isBoolean(String str) {
        return StringUtil.strictMatches(str, "true|false");
    }

    /**
     * Check if the given string is a valid IP address.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a valid IP address, false otherwise
     */
    public static boolean isIp(String str) {
        return StringUtil.strictMatches(str, IP_REGEX);
    }

    /**
     * Checks if the given string is a valid email.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a valid email, false otherwise
     */
    public static boolean isEmail(String str) {
        return StringUtil.strictMatches(str, EMAIL_REGEX);
    }

    /**
     * Check if the given string is a valid URL.
     *
     * @param  str  the string to be checked
     * @return      true if the string is a valid URL, false otherwise
     */
    public static boolean isURL(String str) {
        return StringUtil.strictMatches(str, URL_REGEX);
    }

    /**
     * Checks if the input string is a valid IPv4 address.
     *
     * @param  str  the string to be checked
     * @return      true if the input string is a valid IPv4 address, false otherwise
     */
    public static boolean isIpv4(String str) {
        return StringUtil.strictMatches(str, IPV4_REGEX);
    }

    public static boolean isIpv6(String str) {
        return StringUtil.strictMatches(str, IPV6_REGEX);
    }
}
