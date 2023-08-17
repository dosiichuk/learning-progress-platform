package tracker;

import java.util.Scanner;

public class Tracker implements Runnable {
    private Scanner scanner;
    private UserInterface userInterface;
    private StudentDatabase studentDatabase;


    public Tracker() {
        System.out.println("Learning Progress Tracker");
        this.scanner = new Scanner(System.in);
        this.studentDatabase = new StudentDatabase(scanner);
        this.userInterface = new UserInterface(scanner, studentDatabase);
        this.studentDatabase.setUserInterface(userInterface);
    }

    @Override
    public void run() {
        while(true) {
            userInterface.getAndProcessCommand();
        }
    }
}
