import java.util.ArrayList;

// Learning: This class keeps track of game results separately
// from the code that runs the guessing game.
public class GameStatistics {

    // Learning: These variables store statistics across rounds.
    private int wins;
    private int losses;
    private int totalGuesses;
    private int gamesPlayed;

    // Learning: ArrayList lets me store multiple guesses in a list.
    private ArrayList<Integer> allGuesses;

    // Learning: A constructor initializes the statistics.
    public GameStatistics() {
        wins = 0;
        losses = 0;
        totalGuesses = 0;
        gamesPlayed = 0;
        allGuesses = new ArrayList<>();
    }

    // Learning: This method records a win.
    public void recordWin(int guessesUsed) {
        wins++;
        gamesPlayed++;
        totalGuesses += guessesUsed;
    }

    // Learning: This method records a loss.
    public void recordLoss(int guessesUsed) {
        losses++;
        gamesPlayed++;
        totalGuesses += guessesUsed;
    }

    // Learning: add() saves a guess in the ArrayList.
    public void recordGuess(int guess) {
        allGuesses.add(guess);
    }

    // Learning: Getters allow other classes to read private variables.
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Learning: This method calculates the average guesses per game.
    public double getAverageGuesses() {
        if (gamesPlayed == 0) {
            return 0.0;
        }

        return (double) totalGuesses / gamesPlayed;
    }

    // Learning: This method displays the player's overall results.
    public void displayStatistics() {
        System.out.println("\n===== GAME STATISTICS =====");
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Total guesses: " + totalGuesses);

        System.out.printf(
                "Average guesses per game: %.2f%n",
                getAverageGuesses()
        );

        System.out.println("Guesses recorded: " + allGuesses.size());

        // Learning: A for-each loop goes through every guess in the list.
        System.out.print("All guesses: ");

        if (allGuesses.isEmpty()) {
            System.out.print("No guesses yet.");
        } else {
            for (int guess : allGuesses) {
                System.out.print(guess + " ");
            }
        }

        System.out.println("\n===========================\n");
    }
}