package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RestaurantInterface extends Remote {
    
    /* User */
    public UserInterface getUser(String username, String password) throws RemoteException;
    
    /* Dish CRUD */
    public void createDish() throws RemoteException;
    public void editDish() throws RemoteException;
    

    
}
