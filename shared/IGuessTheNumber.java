package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGuessTheNumber extends Remote {

    String startNewGame() throws RemoteException;
    String makeGuess(String name, int guess) throws RemoteException; 
    List<String> getLeaderboard() throws RemoteException;
    String getGameStatus() throws RemoteException;
    String endGame() throws RemoteException;
    String setPlayerName(String name) throws RemoteException; 
    
}
