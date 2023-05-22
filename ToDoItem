import java.util.Calendar;
import java.util.Date;

/**
 * ToDoItem constructor.
 * @author  Jonathan Le
 * @version 1.00
 */
public class ToDoItem {
    private String name;
    private String type;
    private int hours;

    /**
     * ToDoItem constructor.
     * @param name Name of item.
     * @param type Type of item.
     * @param hours Hours required for item.
     */
    public ToDoItem(String name, String type, String hours) {
        this.name = name;
        this.type = type;
        this.hours = Integer.parseInt(hours);
    }

    @Override
    public String toString() {
        Calendar due = Calendar.getInstance();
        due.setTime(new Date());
        due.add(Calendar.HOUR_OF_DAY, this.hours);
        return ("Task: " + this.name + " | " + "Task type: " + this.type + " | Complete by " + due.getTime());
    }

    /**
     * Getter for name.
     * @return String name for this object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for type.
     * @return String type for this object.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter for hours required to finish.
     * @return int hours required for this object.
     */
    public int getHours() {
        return this.hours;
    }

}
