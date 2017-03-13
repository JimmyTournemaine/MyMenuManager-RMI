package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import database.annotation.Column;
import database.annotation.Table;

@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 6768866785999319045L;

    public User() throws RemoteException {
        super();
    }

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
