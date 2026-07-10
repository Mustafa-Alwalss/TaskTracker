import java.util.Scanner;

public class Main {
   public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        do {
            System.out.print
                    (
                            "HELLO TO TASK TRACKER!\n"          +
                            "1) PRINT ALL TASKS.\n"             +
                            "2) SELECT A TASK.\n"               +
                            "3) ADD A TASK\n"                     +
                            "e) EXIT.\n"                        +
                            "CHOSE THE PROCESS: "
                    );

            String userChoose = input.next().toLowerCase();

            if (userChoose.length() != 1) {
                System.out.println("PLEASE ENTER ONE CHARACTER.");
            } else {
                switch (userChoose) {
                    case "1":
                        TasksHandler.tasksServices("ALL_TASKS");
                        break;
                    case "2":
                        TasksHandler.tasksServices("SELECT_TASK");
                        break;
                    case "3":
                        TasksHandler.tasksServices("ADD_TASK");
                        break;
                    case "e":
                        System.exit(0);
                    default:
                        System.out.println("WRONG INPUT!");
                }
            }
        } while (true);

    }


}

