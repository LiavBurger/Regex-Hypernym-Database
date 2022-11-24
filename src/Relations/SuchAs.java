package Relations;

/**
 * This class represents the "such as" hypernym - hyponym relation.
 * Will have the pattern of: "NP {,} such as NP {, NP, ..., {and|or} NP}"
 */
public class SuchAs extends BasicRelation {
    /**
     * This function returns the string representation of "such as".
     * @return "such as".
     */
    public String getString() {
        return "such as";
    }
}
