package Relations;

/**
 * This class represents the "such as" hypernym - hyponym relation.
 * Will have the pattern of: "NP {,} including NP {, NP, ..., {and|or} NP}"
 */
public class Including extends BasicRelation {
    /**
     * This function returns the string representation of "including".
     * @return "including".
     */
    public String getString() {
        return "including";
    }
}
