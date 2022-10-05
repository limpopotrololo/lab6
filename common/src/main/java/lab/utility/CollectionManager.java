package lab.utility;

import lab.commands.Command;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;
import lab.data.Chapter;
import lab.data.SpaceMarine;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class for work with collection
 */
public class CollectionManager {

    private static Stack<SpaceMarine> collection;
    private final LocalDateTime localDateTime;
    private final TreeSet<Long> id;
    private final HashSet<Double> healthCollection = new HashSet<>();
    private CommandPool commandPool = null;


    public CollectionManager(CommandPool commandPool) {
        collection = new Stack<SpaceMarine>();
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
        this.commandPool = commandPool;
    }

    public CollectionManager(Stack<SpaceMarine> collection) {
        this.collection = collection;
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
    }


    /**
     * Load collection
     *
     * @param collection
     */

    public void loadCollection(Stack<SpaceMarine> collection) {
        this.collection = collection;
    }

    /**
     * Add collection instance to collection
     *
     * @param spaceMarine
     * @throws IncorrectData
     * @return
     */
    public HashMap<String, Command> getCommands(){
        return commandPool.getCommands();

    }
    public boolean addMarine(SpaceMarine spaceMarine) throws IncorrectData {
        if (Objects.equals(spaceMarine.getId(), null)) {
            if (id.isEmpty()) {
                spaceMarine.setId(1L);
                id.add(1l);
            } else {
                spaceMarine.setId(id.last() + 1);
                id.add(id.last() + 1);
            }
        } else if (id.contains(spaceMarine.getId())) {
            spaceMarine.setId(id.last() + 1);
            id.add(id.last() + 1);
        } else {
            id.add(spaceMarine.getId());
        }
        collection.push(spaceMarine);
        sortCollection();
        return true;
    }

    /**
     * @return localDataTime
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * @return Sorted collection
     */
    private Stack<SpaceMarine> sortCollection() {
        Collections.sort(collection);
        return collection;
    }

    /**
     * Return collection size
     *
     * @return Integer
     */
    public Integer getSize() {
        return collection.size();
    }

    /**
     * @return collection
     */
    public Stack<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Add candidate to collection if it is bigger than max element in collection
     *
     * @param candidate
     * @throws IncorrectData
     */
    public boolean AllowAddIfMax(SpaceMarine candidate) throws IncorrectData {
        if (collection.isEmpty()) {
            addMarine(candidate);
            return true;
        }
        if (Collections.max(collection).compareTo(candidate) < 0) {
            addMarine(candidate);
            return true;
        } else
            return false;

    }

    /**
     * Calculation average health point in collection
     * return double
     */
    public double getAverageHealth() {
        double sum = 0;
        if (collection.isEmpty())
            return 0;

        for (SpaceMarine spaceMarine : collection) {
            sum += spaceMarine.getHealth();
        }
        return sum / collection.size();
    }

    /**
     * Clear whole collection
     */
    public void clearCollection() {
        id.clear();
        collection.clear();
    }

    /**
     * Delete element with the give id
     * @param id
     *
     */
    public boolean deleteElementById(Long id) {

        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Long curId = iterator.next().getId();
            if (curId == id) {
                this.id.remove(id);
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Remove first element in collection
     */
    public boolean removeFirstElement() {
        Iterator<SpaceMarine> iterator = collection.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            return true;
        } else
            return false;
    }

    /**
     *Find uniq health value
     * @return Set of health value which exist in collection
     * @throws IncorrectData
     */
    public HashSet<Double> getUniqueHealth() throws IncorrectData {

        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            healthCollection.add(iterator.next().getHealth());
        }
        return healthCollection;

    }

    /**
     * Remove lower element in collection
     * @throws IncorrectData
     * @throws EmptyElement
     */
    public boolean removeLower(SpaceMarine spaceMarine) throws IncorrectData, EmptyElement {

        addMarine(spaceMarine);

        if (collection.isEmpty())
            return false;
        else {
            Double reference = spaceMarine.getHealth();
            Iterator<SpaceMarine> iterator = collection.iterator();
            while (iterator.hasNext()) {
                SpaceMarine item = iterator.next();
                if (item.getHealth() < reference) {
                    iterator.remove();
                    id.remove(item.getId());

                }
            }
            return true;
        }

    }

    /**
     *
     * @return Map with health value and Chapter instance
     * @throws EmptyElement
     */
    public TreeMap<Double, Chapter> printChapterFields() throws EmptyElement {
        if (collection.isEmpty()) throw new EmptyElement();
        TreeMap<Double, Chapter> chapterTreeMap = new TreeMap<>();
        for (SpaceMarine spaceMarine : collection) {
            chapterTreeMap.put(spaceMarine.getHealth(), spaceMarine.getChapter());
        }
        return chapterTreeMap;


    }

    /**
     * Find element by id
     * @param id
     * @return
     */
    public SpaceMarine findElementById(Long id) {
        for (SpaceMarine spaceMarine : collection) {
            if (id.equals(spaceMarine)) {
                return spaceMarine;
            }
        }
        return null;
    }

    /**
     * Load set id in collection which load from file
     */
    public void startSetId() {
        for (SpaceMarine spaceMarine : collection) {
            id.add(spaceMarine.getId());
        }

    }


}
