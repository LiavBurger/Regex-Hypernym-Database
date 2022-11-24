import Database.Corpus;
import Database.HypernymDatabase;

import java.io.IOException;
import java.rmi.UnexpectedException;
/**
 * The program will read all the files in the directory,
 * find and aggregate hypernym relations that match the Hearst patterns using regular expressions,
 * and save them in a txt file.
 */
public class CreateHypernymDatabase {
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
            database.createFile(args[1], database);
        }
        if (args.length > 2) {
            throw new UnexpectedException("Too many arguments or problem reading the path!");
        }
    }
}