package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Blob;

public interface DishInterface extends Remote {

    public void setName(String name) throws RemoteException;

    public String getName() throws RemoteException;

    public void setDescription(String name) throws RemoteException;

    public String getDescription() throws RemoteException;

    public void setImage(Blob name) throws RemoteException;

    public Blob getImage() throws RemoteException;

    public void setPrice(Float price) throws RemoteException;

    public float getPrice() throws RemoteException;
}
