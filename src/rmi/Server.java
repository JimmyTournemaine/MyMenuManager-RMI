package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import bean.Dish;
import bean.DishGroup;
import bean.User;
import database.manager.DishGroupManager;
import database.manager.DishManager;
import database.manager.UserManager;

public class Server implements ServerRMI {

    private UserManager userManager = new UserManager();
    private DishManager dishManager = new DishManager();
    private DishGroupManager dishGroupManager = new DishGroupManager();

    public Server() {

    }

    public static void main(String[] args) {

        int port = 1099;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        Server si = new Server();
        ServerRMI serveurRMI = null;

        Registry registry = null;

        try {
            LocateRegistry.createRegistry(port);
            registry = LocateRegistry.getRegistry(port);
        } catch (RemoteException e) {
            System.out.println("Erreur registry " + e.getMessage());
        }

        try {
            serveurRMI = (ServerRMI) UnicastRemoteObject.exportObject(si, 0);
        } catch (RemoteException e) {
            System.out.println("Erreur exportObject " + e.getMessage());
        }

        try {
            registry.rebind("monserveurrmi", serveurRMI);
        } catch (RemoteException e) {
            System.out.println("Erreur rebind " + e.getMessage());
        }
        System.out.println("Serveur RMI lancé");
    }

    @Override
    public User getUser(String username, String password) throws RemoteException {
        User user = userManager.findByUsername(username);
        if (user == null) return null;
        if (!user.getPassword().equals(password)) return null;
        return user;
    }

    @Override
    public List<Dish> getDishes() throws RemoteException {
        return dishManager.findAll();
    }

    @Override
    public Dish getDish(String name) throws RemoteException {
        return dishManager.findOneByName(name);
    }

    @Override
    public boolean createDish(Dish dish) throws RemoteException {
        return dishManager.create(dish);
    }

    @Override
    public boolean editDish(Dish result) throws RemoteException {
        return dishManager.update(result);
    }

    @Override
    public boolean deleteDish(Dish dish) throws RemoteException {
        return dishManager.delete(dish);
    }

    @Override
    public List<DishGroup> getGroups() throws RemoteException {
        return dishGroupManager.findAll();
    }

    @Override
    public DishGroup getGroup(String name) throws RemoteException {
        return dishGroupManager.findOneByName(name);
    }

    @Override
    public boolean createGroup(DishGroup group) throws RemoteException {
        return dishGroupManager.create(group);

    }

    @Override
    public boolean editGroup(DishGroup group) throws RemoteException {
        return dishGroupManager.update(group);
    }

    @Override
    public boolean deleteGroup(DishGroup group) throws RemoteException {
        return dishGroupManager.delete(group);
    }
}
