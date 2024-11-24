package server;

import shared.IGuessTheNumber;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GuessTheNumberImpl extends UnicastRemoteObject implements IGuessTheNumber {
    private int secretNumber;
    private List<Player> leaderboard;
    private boolean gameInProgress;
    private String currentPlayerName;
    private int attempts;


    private static class Player {
        String name;
        int attempts;

        Player(String name, int attempts) {
            this.name = name;
            this.attempts = attempts;
        }
    }

    public GuessTheNumberImpl() throws RemoteException {
        super();
        leaderboard = new ArrayList<>();
        gameInProgress = false;
        attempts = 0;
    }

    @Override
    public String startNewGame() throws RemoteException {
        if (gameInProgress) {
            return "A game is already in progress!";
        }
        return "Enter your name to start the game!";
    }

    @Override
    public String makeGuess(String name, int guess) throws RemoteException {  
        if (!gameInProgress) {
            return "No game is in progress. Start a new game first.";
        }

        currentPlayerName = name; 

        attempts++;  

        if (guess < secretNumber) {
            return "Too low!";
        } else if (guess > secretNumber) {
            return "Too high!";
        } else {
           
            gameInProgress = false;
            leaderboard.add(new Player(currentPlayerName, attempts)); 
            sortLeaderboard(); 
            return "Correct! The number was " + secretNumber + ". You guessed it in " + attempts + " tries.";
        }
    }

    @Override
    public List<String> getLeaderboard() throws RemoteException {
        List<String> leaderboardDisplay = new ArrayList<>();
        for (Player player : leaderboard) {
            leaderboardDisplay.add(player.name + " - " + player.attempts + " attempts");
        }
        return leaderboardDisplay;
    }

    @Override
    public String getGameStatus() throws RemoteException {
        if (gameInProgress) {
            return "Game in progress. You are on attempt #" + attempts + ". Guess the number!";
        }
        return "No game in progress.";
    }
    

    @Override
    public String endGame() throws RemoteException {
        if (!gameInProgress) {
            return "No game is currently in progress.";
        }
        gameInProgress = false;
        return "Game ended prematurely.";
    }

    
    public String setPlayerName(String name) throws RemoteException {
        if (gameInProgress) {
            return "A game is already in progress!";
        }
        currentPlayerName = name;
        secretNumber = (int) (Math.random() * 100) + 1;  
        attempts = 0;
        gameInProgress = true;
        return "New game started! Guess the number between 1 and 100.";
    }

    
    private void sortLeaderboard() {
        leaderboard.sort((p1, p2) -> Integer.compare(p1.attempts, p2.attempts));
    }
}