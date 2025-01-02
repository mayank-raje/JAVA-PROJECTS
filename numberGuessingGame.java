import java.util.Random;
import java.util.Scanner;

public class numberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int playAgain;

        do {
            int attempts = 0;
            int maxAttempts = 10; 
            int numberToGuess = random.nextInt(100) + 1; 
            boolean correctGuess = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            
            while (attempts < maxAttempts && !correctGuess) {
                attempts++;
                System.out.print("Attempt " + attempts + ": Enter your guess: ");
                int userGuess = sc.nextInt();

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    correctGuess = true;
                    System.out.println("Congratulations! You guessed the correct number!");
                    System.out.println("It took you " + attempts + " attempts.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've run out of attempts! The correct number was " + numberToGuess + ".");
            }

            System.out.print("Do you want to play another round? (enter 1 for Yes , enter 0 for No): ");
            playAgain = sc.nextInt();

        } while (playAgain == 1); 

        sc.close();
        System.out.println("Thanks for playing! Goodbye.");
    }
}
