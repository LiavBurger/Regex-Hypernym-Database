package Relations;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Database.HypernymDatabase;
/**
 * This class represents the basic hypernym - hyponym relation.
 * Generally will have the pattern of: "NP {,} -RELATION- NP {, NP, ..., {and|or} NP}"
 */
public abstract class BasicRelation implements Relation {
    /**
     * This function returns the regex of the relation.
     * @return the regex of the relation.
     */
    @Override
    public Pattern getRegex() {
        //NP {,} such as NP {, NP, ..., {and|or} NP}
        //such NP as NP {, NP, ..., {and|or} NP}
        //NP {,} including NP {, NP, ..., {and|or} NP}
        //NP {,} especially NP {, NP, ..., {and|or} NP}
        String basicPattern = "(such )?"
                + "<np>[^<]+</np>( | , )" + this.getString() + " <np>[^<]+</np>"
                + "(( | , )(and |or )?<np>[^<]+</np>)*.";
        return Pattern.compile(basicPattern);
    }

    /**
     * @return This function gets the string representation of a relation
     */
    public abstract String getString();

    /**
     * This function adds the information from the string to the database.
     * @param basicRelation the string that was matched with the relation
     * @param database the database that the information will be added to
     */
    @Override
    public void addToDatabase(String basicRelation, HypernymDatabase database) {
        Pattern pattern = Pattern.compile("<np>([^<]+)</np>");
        Matcher matcher = pattern.matcher(basicRelation);
        String hypernym;
        try {
            if (matcher.find()) {
                //hypernym is the first match
                hypernym = matcher.group(1);
                //every other match is a hyponym
                while (matcher.find()) {
                    database.addRelation(hypernym, matcher.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}