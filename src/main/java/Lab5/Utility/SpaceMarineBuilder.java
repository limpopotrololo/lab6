package Lab5.Utility;

import java.util.*;

import Lab5.Data.*;
import Lab5.Exeptions.*;


public class SpaceMarineBuilder {


    IOManager ioManager;
    private final SpaceMarine MarineItem = new SpaceMarine();

    public SpaceMarineBuilder(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    SpaceMarineBuilder setName() throws IncorrectData {
        String name;
        ioManager.println("Введите имя");
        name = new Scanner(System.in).nextLine();
        MarineItem.setName(name);
        return this;
    }

    public SpaceMarineBuilder setCoordinates() throws EmptyElement, IncorrectData {
        Coordinates coordinates;
        ioManager.println("Введите координаты");
        coordinates = new Coordinates(enterX(), enterY());
        MarineItem.setCoordinates(coordinates);
        return this;
    }

    private Integer enterX() throws EmptyElement {
        Integer x = null;
        String input;
        try {
            ioManager.println("x = ");
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            x = Integer.parseInt(input);
            return x;
        } catch (NumberFormatException | EmptyElement e) {
            ioManager.println("Введите корректные данные");
            return enterX();
        }

    }

    private Integer enterY() throws EmptyElement {
        Integer y = null;
        String input;
        try {
            ioManager.println("y = ");
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            y = Integer.parseInt(input);
            return y;
        } catch (NumberFormatException | EmptyElement e) {
            ioManager.println("Введите корректные данные");
            return enterY();
        }

    }

    public SpaceMarineBuilder setHealth() throws EmptyElement, IncorrectData {
        String input;
        double health;
        try {
            ioManager.println("Введите параметры здоровья");
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            health = Double.parseDouble(input);
            MarineItem.setHealth(health);
            return this;
        } catch (NumberFormatException e) {
            ioManager.println("Введите корректные данные");
            return setHealth();
        }

    }

    public SpaceMarineBuilder setAchievements() throws EmptyElement, IncorrectData {

        String achievements;
        try {
            ioManager.println("Введите достижения");
            achievements = new Scanner(System.in).nextLine();
            if (achievements.equals("")) throw new EmptyElement();
            MarineItem.setAchievements(achievements);
            return this;
        } catch (EmptyElement e) {
            ioManager.println("Введите корректные данные");
            return setAchievements();
        }
    }

    public SpaceMarineBuilder setCategory() throws EmptyElement, IncorrectData {
        AstartesCategory category;
        String input;
        try {
            ioManager.println("Выберите категорию из предложенного списка --- " + AstartesCategory.Listing());
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            category = AstartesCategory.valueOf(input);
            MarineItem.setCategory(category);
            return this;
        } catch (IllegalArgumentException | EmptyElement e) {
            ioManager.println("Данные не соответствуют требованиям");
            return setCategory();

        }

    }

    public SpaceMarineBuilder setMeleeWeapon() throws IncorrectData {
        MeleeWeapon meleeWeapon;
        String input;
        try {
            ioManager.println("Выберите оружие из предложенного списка --- " + MeleeWeapon.Listing());
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            meleeWeapon = MeleeWeapon.valueOf(input);
            MarineItem.setMeleeWeapon(meleeWeapon);
            return this;
        } catch (IllegalArgumentException | EmptyElement e) {

            ioManager.println("Пожалуйста выберите оружие из СПИСКА");
            return setMeleeWeapon();
        }
    }

    public SpaceMarineBuilder setChapter() throws IncorrectData {
        Chapter chapter;

        ioManager.println("Определите главу");
        chapter = new Chapter(setChapterName(), setChapterParentLegion());
        MarineItem.setChapter(chapter);
        return this;
    }

    private String setChapterName() {
        String input;
        try {
            ioManager.println("Введите имя");
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            return input;
        } catch (EmptyElement e) {
            ioManager.println("Введите корректные данные");
            return setChapterName();
        }
    }

    private String setChapterParentLegion() {
        String input;
        try {
            ioManager.println("Введите родительский регион");
            input = new Scanner(System.in).nextLine();
            if (input.equals("")) throw new EmptyElement();
            return input;
        } catch (EmptyElement e) {
            ioManager.println("Введите корректные данные");
            return setChapterParentLegion();
        }
    }

    public SpaceMarine build() {
        return MarineItem;
    }

}
