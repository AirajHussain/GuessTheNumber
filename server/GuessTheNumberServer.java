package server;

import shared.IGuessTheNumber;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class GuessTheNumberServer {
    public static void main(String[] args) {
        try {

            
            IGuessTheNumber gameService = new GuessTheNumberImpl();
            
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            
            registry.rebind("GuessTheNumberService", gameService);
            
            System.out.println("Game server is ready!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
