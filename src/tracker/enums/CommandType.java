package tracker.enums;

public enum CommandType {
    ADD_STUDENTS("add students", "Enter student credentials or 'back' to return\n"),
    BACK("back", ""),
    EXIT("exit", "Bye!\n"),
    NO_INPUT("", "No input.\n"),
    UNKNOWN_COMMAND( "UNKNOWN_COMMAND", "Error: unknown command!\n");

    public final String commandPrompt;
    public final String message;

    CommandType(String patternRegex, String message) {
        this.commandPrompt = patternRegex;
        this.message = message;
    }

    public String getCommandPrompt() {
        return commandPrompt;
    }

    public String getMessage() {
        return message;
    }

    public static CommandType getCommandTypeFromCommandPrompt(String commandPrompt) {
        if (commandPrompt.isBlank()) return CommandType.NO_INPUT;
        for (CommandType commandType: CommandType.values()) {
            if (commandType.getCommandPrompt().equals(commandPrompt)) {
                return commandType;
            }
        }
        return CommandType.UNKNOWN_COMMAND;
    }
}
