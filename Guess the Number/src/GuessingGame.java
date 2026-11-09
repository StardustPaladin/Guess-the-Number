import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Learning: implements connects this class to GameInterface.
// This class must provide the methods required by the interface.
public class GuessingGame implements GameInterface {

    // Learning: These objects help generate numbers, read input,
    // and track statistics.
    private Random random;
    private Scanner scanner;
    private GameStatistics statistics;

    // Learning: These variables store the settings for each round.
    private int secretNumber;
    private int maximumNumber;
    private int maximumAttempts;

    // Learning: This ArrayList stores the guesses for the current round.
    private ArrayList<Integer> roundGuesses;

    // Learning: The constructor prepares the objects the game needs.
    public GuessingGame(Scanner scanner, GameStatistics statistics) {
        this.random = new Random();
        this.scanner = scanner;
        this.statistics = statistics;
        this.roundGuesses = new ArrayList<>();
    }

    // Learning: @Override means this method implements an interface method.
    @Override
    public void displayRules() {
        System.out.println("\n===== HOW TO PLAY =====");
        System.out.println("1. The computer picks a secret number.");
        System.out.println("2. Enter guesses to find the number.");
        System.out.println("3. The game tells you if your guess is high or low.");
        System.out.println("4. You have a limited number of attempts.");
        System.out.println("5. Guess correctly before you run out!");
        System.out.println("=======================\n");
    }

    // Learning: This method sets the number range and attempts.
    private void chooseDifficulty() {
        System.out.println("\nChoose a difficulty:");
        System.out.println("1. Easy (1-50, 10 attempts)");
        System.out.println("2. Medium (1-100, 7 attempts)");
        System.out.println("3. Hard (1-200, 5 attempts)");

        int choice = readInteger("Enter your choice: ", 1, 3);

        // Learning: switch selects the settings for the chosen difficulty.
        switch (choice) {
            case 1:
                maximumNumber = 50;
                maximumAttempts = 10;
                break;

            case 2:
                maximumNumber = 100;
                maximumAttempts = 7;
                break;

            case 3:
                maximumNumber = 200;
                maximumAttempts = 5;
                break;

            default:
                maximumNumber = 100;
                maximumAttempts = 7;
                break;
        }
    }

    // Learning: This method keeps asking until the player enters
    // a whole number within the allowed range.
    private int readInteger(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);

            // Learning: hasNextInt() checks whether the input is an integer.
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                // Learning: && means both conditions must be true.
                if (number >= minimum && number <= maximum) {
                    return number;
                }

                System.out.println(
                        "Enter a number from " + minimum
                                + " to " + maximum + "."
                );
            } else {
                // Learning: Remove invalid input before repeating the loop.
                System.out.println("Please enter a whole number.");
                scanner.next();
            }
        }
    }

    // Learning: Random generates the secret number for each round.
    private void generateSecretNumber() {
        // Learning: Adding 1 makes the range start at 1 instead of 0.
        secretNumber = random.nextInt(maximumNumber) + 1;
    }

    // Learning: This method compares a guess with the secret number.
    // It returns true if the player guesses correctly.
    private boolean checkGuess(int guess) {
        if (guess == secretNumber) {
            System.out.println("Correct! You guessed the number!");
            return true;
        } else if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else {
            System.out.println("Too high! Try a lower number.");
        }

        return false;
    }

    // Learning: This method displays the guesses from the current round.
    private void displayRoundGuesses() {
        System.out.print("Your guesses this round: ");

        if (roundGuesses.isEmpty()) {
            System.out.println("None");
        } else {
            // Learning: A for-each loop visits every item in the list.
            for (int guess : roundGuesses) {
                System.out.print(guess + " ");
            }

            System.out.println();
        }
    }

    // Learning: This method runs one round of the game.
    @Override
    public void playGame() {
        // Learning: clear() removes guesses from the previous round.
        roundGuesses.clear();

        chooseDifficulty();
        generateSecretNumber();

        int attemptsRemaining = maximumAttempts;
        int guessesUsed = 0;
        boolean won = false;

        System.out.println(
                "\nI picked a number from 1 to " + maximumNumber + "."
        );
        System.out.println("You have " + maximumAttempts + " attempts.");

        // Learning: The loop continues while attempts remain
        // and the player has not won.
        while (attemptsRemaining > 0 && !won) {
            int guess = readInteger(
                    "Enter your guess: ", 1, maximumNumber
            );

            // Learning: Store each guess in the two lists.
            roundGuesses.add(guess);
            statistics.recordGuess(guess);

            guessesUsed++;
            attemptsRemaining--;

            // Learning: The returned true or false value updates won.
            won = checkGuess(guess);

            if (!won && attemptsRemaining > 0) {
                System.out.println(
                        "Attempts remaining: " + attemptsRemaining
                );
            }
        }

        // Learning: The if statement determines whether the round was won.
        if (won) {
            statistics.recordWin(guessesUsed);
            System.out.println("You won in " + guessesUsed + " guesses!");
        } else {
            statistics.recordLoss(guessesUsed);
            System.out.println(
                    "Game over! The number was " + secretNumber + "."
            );
        }

        displayRoundGuesses();
    }
}