package Relations;

/**
 * This class represents the "as" hypernym - hyponym relation.
 * Will have the pattern of: "NP {,} as NP {, NP, ..., {and|or} NP}"
 */
public class As extends BasicRelation {
    /**
     * This function returns the string representation of "as".
     * @return "as".
     */
    public String getString() {
        return "as";
    }
}
