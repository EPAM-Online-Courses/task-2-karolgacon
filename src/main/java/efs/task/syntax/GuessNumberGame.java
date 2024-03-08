package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;
public class GuessNumberGame {
    int M;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            M = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("'" + argument + "'" + UsefulConstants.WRONG_ARGUMENT + " - nie jest liczbą");
            throw new IllegalArgumentException(e);
        }
        if (M > UsefulConstants.MAX_UPPER_BOUND || M < 1){
            System.out.println(M + " to " + UsefulConstants.WRONG_ARGUMENT + " - nie jest " +
                    "w zakresie <1, " + UsefulConstants.MAX_UPPER_BOUND + ">");
            throw new IllegalArgumentException();
        }
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        boolean check = true;
        int limitOfTries = (int) Math.abs(Math.floor(Math.log(M) / Math.log(2) + 1));
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(M) + 1;
        System.out.println("Zaczynamy! Zgadnij liczbę, która znajduje się w zakresie: <1," + M +">");
        int userGuess;
        int counterOfTries = 0;
        do {
            progressBar(counterOfTries, limitOfTries);
            System.out.println(UsefulConstants.GIVE_ME + " liczbę: ");
            String userInput = scanner.nextLine();
            try {
                userGuess = Integer.parseInt(userInput);
            } catch( NumberFormatException e){
                ++counterOfTries;
                System.out.println(userInput + " - " + UsefulConstants.NOT_A_NUMBER);
                continue;
            }
            if (userGuess > randomNumber){
                System.out.println(UsefulConstants.TO_MUCH);
                ++counterOfTries;
            } else if (userGuess < randomNumber){
                System.out.println(UsefulConstants.TO_LESS);
                ++counterOfTries;
            } else {
                System.out.println(UsefulConstants.YES);
                ++counterOfTries;
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + counterOfTries + " - ilość prób, " +
                        "w której zgadłeś liczbę " + randomNumber);
                check = false;
            }
        } while (counterOfTries + 1 < limitOfTries + 1 && check);
        if (check){
            System.out.println(UsefulConstants.UNFORTUNATELY + ", limit prób (" + limitOfTries + ") został wyczerpany." +
                    " Liczba do odganięcia to "+ randomNumber);
        }
    }

    public void progressBar(int counterOfTries, int limitOfTries){
        String bar = "";
        for (int i = 0; i < counterOfTries + 1; i++){
            bar += "*";
        }
        for (int i = 0; i < limitOfTries - (counterOfTries + 1); i++){
            bar += ".";
        }
        System.out.println("Próby: [" + bar + "]");
    }
}
