package data;

/**
 * MeleeWeapon constant
 */
public enum MeleeWeapon {
    CHAIN_SWORD,
    POWER_SWORD,
    MANREAPER,
    POWER_BLADE,
    POWER_FIST;

    /**
     *
     * @return list of constant
     */
    public static String Listing() {
        String list = "";
        for (MeleeWeapon item : values()) {
            list += item + " ";
        }
        return list;
    }
}
