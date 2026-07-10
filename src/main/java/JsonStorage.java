import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {

    public static void save(List<Task> tasks) {
        Path pathToSave = PathFinder.getPath()      ;
        Gson  gson      = new Gson()                ;

        String tasksJson = gson.toJson(tasks);

        //CHECK IF THE FOLDER EXISTS OR NOT.
        try {
            Files.createDirectories(pathToSave.getParent());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        //WRITE THE FILE.
        try {
            Path tempPath = Files.createTempFile("TaskTracker", ".tmp");
            Files.writeString(tempPath, tasksJson);
            Files.move(tempPath, pathToSave , StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.ATOMIC_MOVE );
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static List<Task> load() {
        Path pathToSave = PathFinder.getPath()      ;
        Gson  gson      = new Gson()                ;
        if (!Files.exists(pathToSave)) return new ArrayList<>();

        try {
            String json = Files.readString(pathToSave);
            Type ListType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(json, ListType);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }

    }


}
