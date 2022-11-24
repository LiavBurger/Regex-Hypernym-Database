package Database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Database.HypernymDatabase maps hypernyms with hyponyms of it.
 * One hypernym can have multiple hyponyms.
 */
public class HypernymDatabase {
    private final TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * This function adds a relation to the map.
     *
     * @param hypernym The hypernym of the relation
     * @param hyponym  The hyponym of the relation.
     */
    public void addRelation(String hypernym, String hyponym) {
        hyponym = hyponym.toLowerCase();
        hypernym = hypernym.toLowerCase();
        //If the map has the hypernym
        if (this.map.containsKey(hypernym)) {
            //If it already has the hyponym, update it with +1 on the frequency counter.
            if (this.map.get(hypernym).containsKey(hyponym)) {
                int frequency = this.map.get(hypernym).get(hyponym);
                this.map.get(hypernym).put(hyponym, frequency + 1);
            } else {
                this.map.get(hypernym).put(hyponym, 1);
            }
        } else {
            this.map.put(hypernym, new TreeMap<>());
            this.map.get(hypernym).put(hyponym, 1);
        }
    }

    /**
     * @return Returns the tree map of the database.
     */
    public TreeMap<String, TreeMap<String, Integer>> getMap() {
        return this.map;
    }

    /**
     * This function creates the file from the hypernym database.
     *
     * @param path     path to the new file
     * @param database hypernym database
     */
    public void createFile(String path, HypernymDatabase database) {
        File file = new File(path + "\\hypernym_db.txt");
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            //Iterate over all the hypernyms
            for (String hypernym : database.getMap().keySet()) {
                //ignore hypernyms that don't have at least 3 hyponyms
                if (database.getMap().get(hypernym).size() > 2) {
                    //Get a sorted map sorted by the most common hyponyms.
                    Map<String, Integer> sortedMap = valueSort(database.getMap().get(hypernym));
                    out.write(hypernym + ":");
                    int count = sortedMap.keySet().size();
                    for (String hyponym : sortedMap.keySet()) {
                        count--;
                        out.write(" " + hyponym + " (" + database.getMap().get(hypernym).get(hyponym) + ")");
                        if (count != 0) {
                            out.write(",");
                        }
                    }
                    out.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is used to sort a treemap by value.
     *
     * @param map treemap
     * @param <K> key
     * @param <V> value
     * @return sorted treemap
     */
    public static <K, V extends Comparable<V>> Map<K, V>
    valueSort(final Map<K, V> map) {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        // return comparison results of values of two keys
        Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k2).compareTo(
                    map.get(k1));
            if (comp == 0) {
                return 1;
            } else {
                return comp;
            }
        };
        // SortedMap created using the comparator
        Map<K, V> sorted = new TreeMap<>(valueComparator);
        sorted.putAll(map);
        return sorted;
    }

    /**
     * This function will be used to find all hypernyms with the lemma.
     * @param lemma A specific hyponym.
     * @param database The hypernym database.
     */
    public void findLemma(String lemma, HypernymDatabase database) {
        TreeMap<String, Integer> map = new TreeMap<>();
        for (String hypernym: database.getMap().keySet()) {
            if (database.getMap().get(hypernym).containsKey(lemma)) {
                map.put(hypernym, database.getMap().get(hypernym).get(lemma));
            }
        }
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        for (String hypernym : sortedMap.keySet()) {
            System.out.println(hypernym + ": (" + sortedMap.get(hypernym) + ")");
        }
    }
}
