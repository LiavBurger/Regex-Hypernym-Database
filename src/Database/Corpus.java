package Database;

import Relations.Relation;
import Relations.As;
import Relations.Especially;
import Relations.Including;
import Relations.SuchAs;
import Relations.WhichIs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class is responsible for reading the corpus and adding information into the database.
 */
public class Corpus {
    /**
     * This method reads all the files in the path.
     * @param path Path to the directory
     * @param database The hypernym database
     */
    public static void  readFiles(String path, HypernymDatabase database) throws FileNotFoundException {
        File folder = new File(path);
        File[] folderFiles = folder.listFiles();
        if (folderFiles != null) {
            for (File file : folderFiles) {
                readFile(file, database);
            }
        } else {
            throw new FileNotFoundException();
        }
    }


    private static void readFile(File file, HypernymDatabase database) {
        ArrayList<Relation> relations = new ArrayList<>();
        relations.add(new SuchAs());
        relations.add(new As());
        relations.add(new Including());
        relations.add(new Especially());
        relations.add(new WhichIs());
        Matcher matcher;
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                if (!(line.contains(" such as <np>") || line.contains(" such <np>") || line.contains(" including <np>")
                        || line.contains(" especially <np>") || line.contains(" which is "))) {
                    continue;
                }
                for (Relation relation : relations) {
                    matcher = relation.getRegex().matcher(line);
                    if (matcher.find()) {
                        relation.addToDatabase(matcher.toString(), database);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}