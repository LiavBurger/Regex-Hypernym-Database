package Relations;

import Database.HypernymDatabase;

import java.util.regex.Pattern;

/**
 * The relation interface will be used for getting the regex for hypernym - hyponym relation.
 */
public interface Relation {
    /**
     * Return the regex of the relation.
     * @return the regex of the relation.
     */
    Pattern getRegex();

    /**
     * This function adds the information from the string to the database.
     * @param string string
     * @param database hypernym database
     */
    void addToDatabase(String string, HypernymDatabase database);
}
