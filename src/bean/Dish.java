package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Blob;
import database.annotation.Column;
import database.annotation.Id;
import database.annotation.Table;
import model.DishInterface;

@Table(name = "dish")
public class Dish extends UnicastRemoteObject implements DishInterface, Serializable {

    private static final long serialVersionUID = -5887556494718198589L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private Blob image;

    @Column(name = "price")
    private Float price;
    
    @Column(name="dish_group_id")
    private DishGroup group;

    
    public DishGroup getGroup() {
        return group;
    }

    
    public void setGroup(DishGroup group) {
        this.group = group;
    }

    public Dish() throws RemoteException {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Blob getImage() {
        return image;
    }

    @Override
    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;

    }

    @Override
    public float getPrice() {
        return price;
    }

}
