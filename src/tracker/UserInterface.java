package tracker;

import tracker.enums.CommandType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private StudentDatabase studentDatabase;
    private List<CommandType> commandSequence;

    public UserInterface(Scanner scanner, StudentDatabase studentDatabase) {
        this.scanner = scanner;
        this.studentDatabase = studentDatabase;
        this.commandSequence = new ArrayList<>();
    }

    public CommandType getCommand() {
        String commandPrompt = scanner.nextLine();
        return CommandType.getCommandTypeFromCommandPrompt(commandPrompt);
    }

    public void getAndProcessCommand() {
        CommandType commandType = getCommand();
        commandSequence.add(0, commandType);
        System.out.printf(commandType.getMessage());
        processCommand(commandType);
    }


    public void processCommand(CommandType commandType) {
        switch(commandType) {
            case ADD_STUDENTS:
                studentDatabase.addStudent();
                break;
            case BACK:
                if ((commandSequence.size() == 1 && commandSequence.get(0).equals(CommandType.BACK)) ||
                        (commandSequence.size() > 1 && commandSequence.get(0).equals(CommandType.BACK))) {
                    System.out.println("Enter 'exit' to exit the program.");
                } else {
                    System.out.printf("Total %d students have been added.\n", studentDatabase.getStudents().size());
                }
                break;
            case EXIT:
                System.exit(0);
                break;
            case UNKNOWN_COMMAND:
                return;
            case NO_INPUT:
        }
    }
    public Scanner getScanner() {
        return scanner;
    }

}
