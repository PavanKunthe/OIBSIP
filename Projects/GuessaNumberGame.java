import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
class Game {
    public int number;
    public int inputNumber;
    public int noOfGuesses = 0;
    public final int maxAttempts=10;

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }
    public int getScore(){
        return (inputNumber==number)?(maxAttempts-noOfGuesses+1):0;
    }

    Game() {
        Random rand = new Random();
        number = rand.nextInt(100)+1;
    }

    public void takeUserInput(Scanner sc) {
        System.out.println("Enter the number: ");
        inputNumber = sc.nextInt();
    }

    public boolean isCorrectNumber() {
        noOfGuesses++;
        if (number == inputNumber) {
            System.out.println("Your guess is correct!!! and guessed in " + noOfGuesses+" guesses");

            return true;
        } else if (inputNumber > number) {
            System.out.println("Too high..");
        } else if (inputNumber < number) {
            System.out.println("Too low..");
        }
            return false;

    }
}

public class GuessaNumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max_Attempts = 10;
        int totalRounds = 0;
        int totalGuesses = 0;
        int totalScore=0;
        List<Integer> roundscores=new ArrayList<>();
        String playagain;
        System.out.println("Welcome to Guess the Number Game: ");
        do {
            Game g = new Game();
            boolean isCorrect = false;
            while (!isCorrect && g.getNoOfGuesses() < max_Attempts) {
                g.takeUserInput(sc);
                isCorrect = g.isCorrectNumber();
            }
            if (!isCorrect) {
                System.out.println("Game over!! the correct guess was: "+g.number);
            }
            int roundscore=g.getScore();
            roundscores.add(roundscore);
            totalScore+=roundscore;
            totalRounds++;
            totalGuesses += g.getNoOfGuesses();
            System.out.println("Do you want to continue: Yes or no: ");
            sc.nextLine();
            playagain = sc.nextLine();

    } while(playagain.equalsIgnoreCase("yes"));

        System.out.println("\n Game Summary");
        System.out.println("Total rounds played: "+totalRounds);
        System.out.println("Total guesses made: "+totalGuesses);
        System.out.println("Total Score is: "+totalScore);
        System.out.println("Round-wise scores: ");
        for(int i=0;i<roundscores.size();i++){
            System.out.println("Round "+(i+1)+": "+roundscores.get(i));
        }
        sc.close();
    }
}