package lab.utility;

import lab.data.SpaceMarine;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;


/**
 * Argument loader for SpaceMarine
 */
public class SpaceMarineArgumentLoader extends ArgumentLoader {
    IOManager ioManager;

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
