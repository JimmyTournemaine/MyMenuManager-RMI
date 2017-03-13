package bean;

import java.util.Set;
import database.annotation.Column;

public class MenuEntry {

    /**
     * Default constructor
     */
    public MenuEntry() {
    }

    @Column(name="name")
    private String name;

    private Set<Dish> dishes;

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Set<Dish> getDishes() {
        return dishes;
    }

    
    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

}