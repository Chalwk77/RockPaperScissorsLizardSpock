package com.chalwk;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static String[] options = {"R", "P", "S", "L", "SP"};
    private static int game_over = 0;

    public static void main(String[] args) {

        Map<String, String[]> choices = new HashMap<>();
        choices.put("R", new String[]{"S", "L"});
        choices.put("P", new String[]{"R", "SP"});
        choices.put("S", new String[]{"P", "L"});
        choices.put("L", new String[]{"SP", "P"});
        choices.put("SP", new String[]{"S", "R"});

        System.out.println("================================================");
        System.out.println("Welcome to Rock, Paper, Scissors, Lizard, Spock!");
        System.out.println("================================================");
        System.out.println("Rules:");
        System.out.println("- ROCK crushes SCISSORS and LIZARD");
        System.out.println("- PAPER covers ROCK and disproves SPOCK");
        System.out.println("- SCISSORS cuts PAPER and decapitates LIZARD");
        System.out.println("- LIZARD poisons SPOCK and eats PAPER");
        System.out.println("- SPOCK smashes SCISSORS and vaporizes ROCKS");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice: (R, P, S, L, SP)");

        Init(input, choices);
        game_over = 0;
    }

    private static void Init(Scanner input, Map<String, String[]> choices) {
        while (true) {

            String computer = getRandom();
            String user_choice = input.nextLine().toUpperCase();

            String[] user_defeats = choices.get(user_choice);
            String[] computer_defeats = choices.get(computer);

            boolean winner = won(user_choice, computer, user_defeats, computer_defeats);
            if (winner) {
                game_over++;
                System.out.println(" ");
                System.out.println("Play again? (Y/N)");
                String play_again = input.nextLine().toUpperCase();
                if (play_again.equals("Y")) {
                    System.out.println("Enter your choice: (R, P, S, L, SP)");
                    continue;
                } else {
                    System.out.println("Thanks for playing!");
                    System.out.println("You played " + game_over + " games.");
                    break;
                }
            } else {
                System.out.println("Invalid choice, try again!");
                System.out.println("Enter your choice: (R, P, S, L, SP)");
            }
        }
    }

    private static boolean won(String user, String computer, String[] user_defeats, String[] computer_defeats) {

        if (user.equals(computer)) {
            System.out.println("It's a tie!");
            System.out.println("Computer chose: " + computer);
            System.out.println("You chose: " + user);
            return true;
        }

        for (String opposition : user_defeats) {
            if (opposition.equals(computer)) {
                System.out.println("You win!");
                System.out.println("Computer chose: " + computer);
                System.out.println("You chose: " + user);
                return true;
            }
        }

        for (String opposition : computer_defeats) {
            if (opposition.equals(user)) {
                System.out.println("Computer Wins!");
                System.out.println("Computer chose: " + computer);
                System.out.println("You chose: " + user);
                return true;
            }
        }

        return false;
    }

    public static String getRandom() {
        int rand = new Random().nextInt(options.length);
        return options[rand];
    }
}
