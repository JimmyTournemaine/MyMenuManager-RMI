package bean;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import database.annotation.Column;
import database.annotation.Table;
import model.UserInterface;

@Table(name = "user")
public class User extends UnicastRemoteObject implements UserInterface {

    private static final long serialVersionUID = 6768866785999319045L;

    protected User() throws RemoteException {
        super();
    }

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
