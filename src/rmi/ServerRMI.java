package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import bean.Dish;
import bean.DishGroup;
import bean.User;

public interface ServerRMI extends Remote {
    
    /* User */
    public User getUser(String username, String password) throws RemoteException;
    
    /* Dish CRUD */
    public List<Dish> getDishes() throws RemoteException;
    public Dish getDish(int id) throws RemoteException;
    public Dish getDish(String name) throws RemoteException;
    public boolean createDish(Dish dish) throws RemoteException;
    public boolean editDish(Dish dish) throws RemoteException;
    public boolean deleteDish(Dish dish) throws RemoteException;

    /* DishGroup CRUD */
    public List<DishGroup> getGroups() throws RemoteException;
    public DishGroup getGroup(int id) throws RemoteException;
    public DishGroup getGroup(String name) throws RemoteException;
    public boolean createGroup(DishGroup group) throws RemoteException;
    public boolean editGroup(DishGroup group) throws RemoteException;
    public boolean deleteGroup(DishGroup group) throws RemoteException;

}
