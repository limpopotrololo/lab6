package Lab5.Utility;

import Lab5.Data.Chapter;
import Lab5.Data.SpaceMarine;
import Lab5.Exeptions.*;


import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {

    private static Stack<SpaceMarine> collection;
    private final LocalDateTime localDateTime;
    private final TreeSet<Long> id;
    private final HashSet<Double> healthCollection = new HashSet<>();


    public CollectionManager() {
        collection = new Stack<SpaceMarine>();
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
    }

    public CollectionManager(Stack<SpaceMarine> collection)  {
        this.collection = collection;
        localDateTime = LocalDateTime.now();
        id = new TreeSet<>();
    }

    public void loadCollection(Stack<SpaceMarine> collection) {
        this.collection = collection;
    }


    public void addMarine(SpaceMarine spaceMarine) throws IncorrectData {
        if (Objects.equals(spaceMarine.getId(),null)) {
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

    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    private Stack<SpaceMarine> sortCollection() {
        Collections.sort(collection);
        return collection;
    }

    public Integer getSize() {
        return collection.size();
    }

    public Stack<SpaceMarine> getCollection() {
        return collection;
    }

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

    public double getAverageHealth() {
        double sum = 0;
        if (collection.isEmpty())
            return 0;

        for (SpaceMarine spaceMarine : collection) {
            sum += spaceMarine.getHealth();
        }
        return sum / collection.size();
    }

    public void clearCollection() {
        id.clear();
        collection.clear();
    }

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

    public boolean removeFirstElement() {
        Iterator<SpaceMarine> iterator = collection.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            return true;
        } else
            return false;
    }

    // public void printMarineCollection() {

    //    for (SpaceMarine spaceMarine : collection) {
    //     ioManager.println(spaceMarine);
    //    }
    //  }


    public HashSet<Double> getUniqueHealth() throws IncorrectData {


        Iterator<SpaceMarine> iterator = collection.iterator();
        while (iterator.hasNext()) {
            healthCollection.add(iterator.next().getHealth());
        }
        return healthCollection;


    }

    public boolean removeLower(ArgumentLoader arguments) throws IncorrectData, EmptyElement {

        SpaceMarine spaceMarine = ((SpaceMarineArgumentLoader) arguments).loadSpaceMarin();
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

    public TreeMap<Double, Chapter> printChapterFields() throws EmptyElement {
        if (collection.isEmpty()) throw new EmptyElement();
        TreeMap<Double, Chapter> chapterTreeMap = new TreeMap<>();
        for (SpaceMarine spaceMarine : collection) {
            chapterTreeMap.put(spaceMarine.getHealth(), spaceMarine.getChapter());
        }
        return chapterTreeMap;


    }

    public SpaceMarine findElementById(Long id) {
        for (SpaceMarine spaceMarine : collection) {
            if (id.equals(spaceMarine)) {
                return spaceMarine;
            }
        }
        return null;
    }

    public void startSetId() {
        for (SpaceMarine spaceMarine : collection) {
            id.add(spaceMarine.getId());
        }

    }


}
