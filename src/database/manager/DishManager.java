package database.manager;

import bean.Dish;

public class DishManager extends ObjectManager<Dish> {

    public DishManager() {
        super(Dish.class);
    }
    
    public Dish findOneByName(String name) {
        return (Dish) this.findOneBy("name", name);
    }
    
}
