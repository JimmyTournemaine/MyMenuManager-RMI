package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import database.annotation.Column;
import database.annotation.Id;
import database.annotation.Table;

@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 6768866785999319045L;

    public User() throws RemoteException {
        super();
    }
    
    public void setLastLoginToNow() {
        lastLogin = new Timestamp((new Date()).getTime());
    }

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login")
    private Timestamp lastLogin;

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

    
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

}
