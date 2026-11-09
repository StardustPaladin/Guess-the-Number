import java.util.Scanner;

// Learning: Main is the starting point of the Java program.
// It creates the objects and controls the main menu.
public class Main {

    public static void main(String[] args) {

        // Learning: Scanner reads the player's keyboard input.
        Scanner scanner = new Scanner(System.in);

        // Learning: These objects connect the program's different classes.
        GameStatistics statistics = new GameStatistics();
        GuessingGame game = new GuessingGame(scanner, statistics);

        // Learning: This variable controls when the menu loop ends.
        boolean running = true;

        System.out.println("==============================");
        System.out.println("      GUESS THE NUMBER");
        System.out.println("==============================");
        System.out.println("Welcome to my Java guessing game!");

        // Learning: The while loop repeats until running becomes false.
        while (running) {
            displayMenu();

            System.out.print("Choose an option: ");

            // Learning: Check the input type to prevent invalid text
            // from crashing the menu.
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                // Learning: switch chooses an action based on the menu option.
                switch (choice) {
                    case 1:
                        game.playGame();
                        break;

                    case 2:
                        game.displayRules();
                        break;

                    case 3:
                        statistics.displayStatistics();
                        break;

                    case 4:
                        running = false;
                        System.out.println("Thanks for playing!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Choose 1 through 4."
                        );
                        break;
                }
            } else {
                // Learning: Remove invalid input and show the menu again.
                System.out.println("Please enter a whole number.");
                scanner.next();
            }
        }

        // Learning: Close the Scanner when the program is finished.
        scanner.close();
    }

    // Learning: A separate method keeps the menu code organized.
    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Play a game");
        System.out.println("2. View rules");
        System.out.println("3. View statistics");
        System.out.println("4. Exit");
        System.out.println("===============================");
    }
}   