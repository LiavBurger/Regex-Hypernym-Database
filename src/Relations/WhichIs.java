package Relations;

import Database.HypernymDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the "Which is" hypernym - hyponym relation.
 * Will have the pattern of: "NP {,} which is {{an example|a kind|a class} of} NP"
 */
public class WhichIs implements Relation {
    /**
     * This function returns the regex of the relation Relations.WhichIs.
     * @return the regex of the relation.
     */
    @Override
    public Pattern getRegex() {
        //NP {,} which is {{an example|a kind|a class} of} NP
        String whichIsPattern =
                "<np>[^<]+</np>( | , )which is(( an example| a kind| a class)? of)? <np>[^<]+</np>.";
        return Pattern.compile(whichIsPattern);
    }

    /**
     * This function adds the information from the string to the database.
     * @param whichIsRelation The string that was matched with the relation.
     * @param database The database that the information will be added to.
     */
    @Override
    public void addToDatabase(String whichIsRelation, HypernymDatabase database) {
        Pattern npPattern = Pattern.compile("<np>([^<]+)</np>");
        Matcher npMatcher = npPattern.matcher(whichIsRelation);
        String hyponym;
        try {
            if (npMatcher.find()) {
                //The hyponym is the first match
                hyponym = npMatcher.group(1);
                //The second match is the hypernym
                while (npMatcher.find()) {
                    database.addRelation(npMatcher.group(1), hyponym);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
