package Lab5.Data;

import Lab5.Exeptions.*;

public class Chapter {
    private String name; //field can't be null
    private String parentLegion;

    public Chapter(String name, String parentLegion) throws IncorrectData {
        this.setName(name);
        this.setParentLegion(parentLegion);
    }
    public Chapter(){}

    private void setName(String name) throws IncorrectData {
        if ((name == null) || (name.trim().equals(""))) {
            throw new IncorrectData();
        }
        this.name = name;
    }

    private void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public String toString() {
        return "\n" + "Chapter Name = " + name + "\n" +
                "Parent Legion = " + parentLegion + "\n";
    }

    public String getParentLegion() {
        return parentLegion;
    }

    public String getName() {
        return name;
    }
}
