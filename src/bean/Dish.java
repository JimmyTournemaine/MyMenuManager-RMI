package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Blob;
import database.annotation.Column;
import database.annotation.Id;
import database.annotation.Table;

@Table(name = "dish")
public class Dish implements Serializable {

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
    private float price;

    @Column(name = "dish_group_id")
    private Integer groupId;
    
    private DishGroup group;

    public Dish() throws RemoteException {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public DishGroup getGroup() {
        return group;
    }

    public void setGroup(DishGroup group) {
        this.group = group;
        if (group != null) {
            this.groupId = group.getId();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;

    }

    public float getPrice() {
        return price;
    }

}
