import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void main(String[] args) {

        Path p = PathFinder.getTempPath();
//
//        System.out.println(p);
//        System.out.println(p.getParent());
//
//        System.out.println(Files.exists(p));
//        try {
//            Files.createDirectories(p.getParent());
//            System.out.println("folder create successfully! ");
//            Files.writeString(p, "tsasdst \n lkdjasldkj");
//            System.out.println("writing ");
//            System.out.printf("the contnet is : %s" , Files.readString(p));
//        } catch (Exception err) {
//            System.out.println(err);
//        }
//
        try {
            Files.writeString(p, "Hello world!");

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
