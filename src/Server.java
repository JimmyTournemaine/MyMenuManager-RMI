
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import model.RestaurantInterface;
import model.UserInterface;

public class Server extends UnicastRemoteObject implements RestaurantInterface {

    private static final long serialVersionUID = 8547387726813420962L;

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
    public UserInterface getUser(String username, String password) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createDish() throws RemoteException {
        // TODO Auto-generated method stub

    }

    @Override
    public void editDish() throws RemoteException {
        // TODO Auto-generated method stub

    }
}
