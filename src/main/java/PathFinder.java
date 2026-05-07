import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFinder {
    private static final String APP_NAME    = "TaskTracker" ;
    private static final String FILE_NAME   = "tasks.json"  ;

    //  LIST OF OPERATING SYSTEMS.
    enum OS {
        win, linux, mac, unknown
    }

    //  DETECTING THE OS.
    public static OS detectOS() {
        String OS_Name = System.getProperty("os.name").toLowerCase();

        if (OS_Name.contains("win")) {
            return OS.win;
        } else if (OS_Name.contains("linux")) {
            return OS.linux;
        } else if (OS_Name.contains("mac")) {
            return OS.mac;
        } else {
            return OS.unknown;
        }
    }
    // THE PATH WHERE THE JSON FILE WILL BE STORED.
    public static Path getPath() {
        //  STORING WHICH OPERATING SYSTEM WE WORKING ON.
        OS os = detectOS();
        return switch (os) {
            case win -> {
                String path = System.getenv("APPDATA");
                //  FALLBACK
                if (path == null) {
                    path = System.getProperty("user.home") + "\\AppData\\Roaming";
                }
                yield Paths.get(path, APP_NAME, FILE_NAME);
            }
            case linux, mac, unknown -> {
                String path = System.getProperty("user.home");
                yield Paths.get(path, ".cache", APP_NAME, FILE_NAME);
            }
        };
    }

    // THE PATH WHERE THE TEMPORARY JSON FILE WILL BE STORED
    public static Path getTempPath() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        return Paths.get(tmpDir, APP_NAME + ".tmp");
    }

    //  PRINT THE RESULTS
    public static void printInfo() {
        OS os = detectOS();
        Path path = getPath();
        Path tmpPath = getTempPath();
        System.out.printf(" THE OS IS         : %s \n THE PATH IS       : %s \n THE TEMP PAHT IS  : %s", os, path ,tmpPath);

    }
}
