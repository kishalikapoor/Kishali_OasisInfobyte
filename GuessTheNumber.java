import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lowerBound = 1;
        int upperBound = 100;
        int attempts = 0;
        int score = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound + ":");
            
            int guess;
            while (true) {
                guess = scanner.nextInt();
                attempts++;
                if (guess == generatedNumber) {
                    score += 100 - (attempts * 2); // Score calculation
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    break;
                } else if (guess < generatedNumber) {
                    System.out.println("Try higher.");
                } else {
                    System.out.println("Try lower.");
                }
            }
            
            System.out.println("Your score: " + score);
            System.out.println("Do you want to play again? (yes/no)");
            String playChoice = scanner.next().toLowerCase();
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
            attempts = 0; // Reset attempts for the new game
        }
        
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
