import java.time.LocalDate;
import java.util.UUID;

public class Task {

    private enum TASK_STATUS {
        DONE , NOT_DONE , IN_PROGRESS
    }

    private String      id;
    private String      title;
    private String      description;
    private String      date;
    private TASK_STATUS statu ;

    public Task(String title, String description) {
        this.id             = UUID.randomUUID().toString().substring(0,7);
        this.title          = title;
        this.description    = description;
        this.statu          = TASK_STATUS.NOT_DONE;
        this.date           = LocalDate.now().toString();
    }

//    GETTERS
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public TASK_STATUS getStatu() {
        return statu;
    }

//    SETTERS
    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStatu(TASK_STATUS statu){
        this.statu = statu;
    }

    //    OVERRIDE "toString"
    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s", id, title, description, statu);
    }
}
