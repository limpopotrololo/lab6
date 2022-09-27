package lab6.data;

/**
 * Enumeration with marine category constants.
 */

public enum AstartesCategory {
    DREADNOUGHT,
    ASSAULT,
    TACTICAL;

    /**
     *
     * @return list of constant
     */
    public static String Listing() {
        String list = "";
        for (AstartesCategory item : values()) {
            list += item + " ";
        }
        return list;
    }

}
