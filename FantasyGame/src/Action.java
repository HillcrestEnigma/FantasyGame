public class Action {
    private String command;
    private String description;

    public Action(String c, String d) {
        command = c;
        description = d;
    }

    public void perform(Player player, Location location) {
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
