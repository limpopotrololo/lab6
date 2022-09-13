package Lab5.Data;

public enum AstartesCategory {
    DREADNOUGHT,
    ASSAULT,
    TACTICAL;


    public static String Listing() {
        String list = "";
        for (AstartesCategory item : values()) {
            list += item + " ";
        }
        return list;
    }

}
