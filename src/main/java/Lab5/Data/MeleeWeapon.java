package Lab5.Data;

public enum MeleeWeapon {
    CHAIN_SWORD,
    POWER_SWORD,
    MANREAPER,
    POWER_BLADE,
    POWER_FIST;

    public static String Listing() {
        String list = "";
        for (MeleeWeapon item : values()) {
            list += item + " ";
        }
        return list;
    }
}
