/**
 * An action superclass for different actions
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Action {
    //Instance variables
    private String command;
    private String description;
    
    /**
     * Constructs an action
     * 
     * @param c
     * @param d 
     */
    public Action(String c, String d) {
        command = c;
        description = d;
    }
    
    /**
     * Performs an action
     * 
     * @param player
     * @param location 
     */
    public void perform(Player player, Location location) {
    }
    
    /**
     * Returns the command for an action
     * 
     * @return 
     */
    public String getCommand() {
        return command;
    }
    
    /**
     * Returns the description of a command
     * @return 
     */
    public String getDescription() {
        return description;
    }
}
