package bean;

import java.util.Set;
import database.annotation.Column;
import database.annotation.Id;

/**
 * 
 */
public class DishGroup {

    /**
     * Default constructor
     */
    public DishGroup() {
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_group")
    private DishGroup parent;

    public DishGroup getParent() {
        return parent;
    }

    public void setParent(DishGroup parent) {
        this.parent = parent;
    }

    private Set<Dish> dishes;
    private Set<DishGroup> subGroups;

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

    public Set<Dish> getDishes() {
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

    public Set<DishGroup> getSubGroups() {
        return subGroups;
    }

    public void addSubGroup(DishGroup subGroup) {
        subGroup.setParent(this);
        this.subGroups.add(subGroup);
    }

    public void removeSubGroup(DishGroup subGroup) {
        subGroup.setParent(null);
        this.subGroups.remove(subGroup);
    }

}
