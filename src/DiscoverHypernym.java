import Database.Corpus;
import Database.HypernymDatabase;

import java.io.IOException;
import java.rmi.UnexpectedException;

/**
 * The program will search all the possible hypernyms of the input lemma and print them to the console as follows:
 * hypernym1: (x)
 * hypernym2: (x)
 * ...
 * where (x) corresponds to the number of occurrences of the relations (across all possible patterns) in the corpus.
 */
public class DiscoverHypernym {
    /**
     * Main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new UnexpectedException("Must get 2 arguments!");
        }
        if (args.length == 2) {
            HypernymDatabase database = new HypernymDatabase();
            Corpus.readFiles(args[0], database);
            database.findLemma(args[1], database);
        }
        if (args.length > 2) {
            throw new UnexpectedException("Too many arguments or problem reading the path!");
        }
    }
}