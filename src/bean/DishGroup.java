package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import database.annotation.Column;
import database.annotation.Id;
import database.annotation.Table;

@Table(name="dish_group")
public class DishGroup implements Serializable {

    private static final long serialVersionUID = 4930559450565766783L;
    
    /**
     * Default constructor
     */
    public DishGroup() {
        dishes = new ArrayList<Dish>();
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    private List<Dish> dishes;

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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dish.setGroup(this);
        this.dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dish.setGroup(null);
        this.dishes.remove(dish);
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}
