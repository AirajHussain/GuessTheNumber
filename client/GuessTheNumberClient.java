package client;

import shared.IGuessTheNumber;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class GuessTheNumberClient {
    private static IGuessTheNumber gameService;

    public static void main(String[] args) {
        try {
           
            Registry registry = LocateRegistry.getRegistry("localhost", 1099); 
            gameService = (IGuessTheNumber) registry.lookup("GuessTheNumberService");

            Scanner scanner = new Scanner(System.in);
            int choice;

           
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            System.out.println(gameService.setPlayerName(playerName)); 

            do {
                System.out.println("\n-- Guess the Number Game --");
                System.out.println("1. Start New Game");
                System.out.println("2. Make a Guess");
                System.out.println("3. View Leaderboard");
                System.out.println("4. Check Game Status");
                System.out.println("5. End Game");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println(gameService.setPlayerName(playerName)); 
                        break;
                    case 2:
                        System.out.print("Enter your guess: ");
                        int guess = scanner.nextInt();
                        System.out.println(gameService.makeGuess(playerName, guess)); 
                        break;
                    case 3:
                        List<String> leaderboard = gameService.getLeaderboard(); 
                        System.out.println("Leaderboard:");
                        for (String entry : leaderboard) {
                            System.out.println(entry);
                        }
                        break;
                    case 4:
                        System.out.println(gameService.getGameStatus()); 
                        break;
                    case 5:
                        System.out.println(gameService.endGame());
                        break;
                    case 0:
                        System.out.println("Exiting game.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 0);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
