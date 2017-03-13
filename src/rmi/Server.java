package rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import bean.Dish;
import bean.DishGroup;
import bean.User;
import database.manager.DishGroupManager;
import database.manager.DishManager;
import database.manager.UserManager;
import model.RestaurantInterface;

public class Server extends UnicastRemoteObject implements RestaurantInterface {

    private static final long serialVersionUID = 8547387726813420962L;

    private UserManager userManager = new UserManager();
    private DishManager dishManager = new DishManager();
    private DishGroupManager dishGroupManager = new DishGroupManager();
    
    public Server() throws RemoteException {
        
    }

    @SuppressWarnings("deprecation")
    public static void main(String args[]) throws IOException {

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        try {
            InetAddress machine = InetAddress.getLocalHost();
            String hostname = machine.getHostName();

            if (hostname.indexOf(".") != -1)
                hostname = hostname.substring(0, hostname.indexOf("."));

            String service = "//" + hostname + ":" + args[0] + "/RestaurantRMI";

            Server serv = new Server();
            Naming.rebind(service, serv);

            System.out.println("RestaurantRMI enregistre : " + service);
        } catch (RemoteException e) {
            System.out.println("RestaurantRMI err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username, String password) throws RemoteException {
        User user = userManager.findByUsername(username);
        if (user == null) return null;
        if(!user.getPassword().equals(password)) return null;
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
