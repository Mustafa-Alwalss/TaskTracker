import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TasksHandler {

    private static final Scanner input = new Scanner(System.in);

    private static final List<Task> listOfTasks = JsonStorage.load();


    public static void tasksServices(String typeOfService) {
        switch (typeOfService) {

            case "ALL_TASKS":
                showMenu("ALL_TASKS");
                break;

            case "SELECT_TASK":
                showMenu("SELECT_TASK");

                try {
                    int SELECTED_TASK = input.nextInt();
                    input.nextLine();
                    if (SELECTED_TASK < 0 || SELECTED_TASK >= listOfTasks.size()) {
                        System.out.println("Invalid index!");
                    } else {
                        printTask(listOfTasks.get(SELECTED_TASK));
                        showMenu("TASK_OPS");
                        String userChoose = input.next();
                        switch (userChoose) {
                            case "1":
                                editTask(SELECTED_TASK, listOfTasks.get(SELECTED_TASK));
                                break;
                            case "2":
                                System.out.printf("%s DELETED", listOfTasks.get(SELECTED_TASK).getTitle());
                                deleteTask(listOfTasks.get(SELECTED_TASK));
                                break;
                            default:
                                System.out.println("WRONG INPUT!");
                                tasksServices(typeOfService);
                        }
                    }
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("PLEASE ENTER A NUMBER!");
                }

                break;

            case "ADD_TASK":
                addTask();
                break;
        }
        JsonStorage.save(listOfTasks);

    }

    private static void addTask() {
        input.nextLine();
        System.out.print("ENTER THE TITLE: ");
        String title = input.nextLine();

        System.out.println("TYPE THE DESCRIPTION: ");
        String description = input.nextLine();

        listOfTasks.add(new Task(title, description));
    }

    private static void deleteTask(Task taskToDelete) {
        listOfTasks.remove(taskToDelete);
    }

    private static void editTask(int indexOfTask,Task taskToEdit) {
        printTask(taskToEdit);

        System.out.print("WHAT DO YOU WANT TO EDIT? 1) TITLE 2) DESCRIPTION 3) PUT TASK IN PROGRESS 4) MARK AS DONE [1|2|3|4]:");

        int userChoose = input.nextInt();
        input.nextLine();
        switch (userChoose) {
            case 1:
                System.out.print("ENTER THE NEW TITLE: ");
                String newTitle = input.nextLine();
                taskToEdit.setTitle(newTitle);
                System.out.println();
                break;
            case 2:
                System.out.print("ENTER THE NEW DESCRIPTION: ");
                String newDescription = input.nextLine();
                taskToEdit.setDescription(newDescription);
                break;
            case 3:
                System.out.println("THE TASK IS IN PROGRESS NOW.");
                taskToEdit.setStatus(Task.TASK_STATUS.IN_PROGRESS);
                break;
            case 4:
                System.out.println("THE TASK MARKED AS DONE!");
                taskToEdit.setStatus(Task.TASK_STATUS.DONE);
                break;
            default:
                System.out.println("WRONG INPUT!");
                editTask(indexOfTask ,taskToEdit);
                break;
        }
    }


    private static void printTask(Task task) {
        System.out.printf("Title:%s\n--->\t%s\nDate:%s\nStatus: %s\n", task.getTitle() , task.getDescription() , task.getDate() , task.getStatus());
    }

    private static void showMenu(String whatPage) {
        switch (whatPage) {

            case "ALL_TASKS":
                for (int task = 0; task < listOfTasks.size(); task++) {
                    System.out.printf("\nTitle:%s\n--->\t%s\nDate:%s\nStatus: %s \n", listOfTasks.get(task).getTitle(),  listOfTasks.get(task).getDescription(), listOfTasks.get(task).getDate() , listOfTasks.get(task).getStatus());
                }
                break;

            case "SELECT_TASK":
                for (int task = 0; task < listOfTasks.size(); task++) {
                    System.out.printf("%d) %s\n", task, listOfTasks.get(task).getTitle());
                }
                break;

            case "TASK_OPS":
                System.out.print
                (
                               "SELECT THE NUMBER OF OPERATION\n"               +
                               "1) EDIT: CHANGE THE TITLE OF THE DESCRIPTION\n" +
                               "2) DELETE: REMOVE A TASK FROM THE LIST.\n"      +
                               "CHOSE: "
                );
                break;
        }
    }
}
