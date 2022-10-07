package lab.utility;

import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.ArgumentLoader;
import utility.IOManager;

/**
 * Argument loader for SpaceMarine
 */
public class SpaceMarineArgumentLoader extends ArgumentLoader {
    utility.IOManager ioManager;

    public SpaceMarineArgumentLoader(String[] arguments, IOManager ioManager) {
        super(arguments);
        this.ioManager = ioManager;
    }

    /**
     * Build SpaceMarine items
     * @return SpaceMarine instance
     * @throws IncorrectData
     * @throws EmptyElement
     */
    public SpaceMarine loadSpaceMarin() throws IncorrectData, EmptyElement {
        return new SpaceMarineBuilder(ioManager).setName().setCoordinates().setHealth().setAchievements().setCategory().setMeleeWeapon().setChapter().build();
    }
}
