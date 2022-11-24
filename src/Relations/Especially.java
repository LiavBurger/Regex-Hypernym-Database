package Relations;

/**
 * This class represents the "such as" hypernym - hyponym relation.
 * Will have the pattern of: "NP {,} especially NP {, NP, ..., {and|or} NP}"
 */
public class Especially extends BasicRelation {
    /**
     * This function returns the string representation of "especially".
     * @return "especially".
     */
    public String getString() {
        return "especially";
    }
}
