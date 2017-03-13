package database.manager;

import java.util.Iterator;
import java.util.List;
import bean.Dish;
import bean.DishGroup;

public class DishGroupManager extends ObjectManager<DishGroup> {

    private DishManager dishManager;

    public DishGroupManager() {
        super(DishGroup.class);
        dishManager = new DishManager();
    }

    @Override
    public DishGroup find(int id) {
        DishGroup group = super.find(id); // load group
        hydrate(group);
        return group;
    }

    @Override
    public List<DishGroup> findAll() {
        List<DishGroup> groups = super.findAll();
        for (DishGroup g : groups) {
            hydrate(g);
        }
        return groups;
    }

    @Override
    public List<DishGroup> findBy(String prop, Object value) {
        List<DishGroup> groups = super.findBy(prop, value);
        for (DishGroup g : groups) {
            hydrate(g);
        }
        return groups;
    }

    @Override
    public DishGroup findOneBy(String prop, Object value) {
        DishGroup group = super.findOneBy(prop, value);
        hydrate(group);
        return group;
    }

    public DishGroup findOneByName(String name) {
        return this.findOneBy("name", name);
    }

    @Override
    public boolean create(DishGroup entity) {
        if(super.create(entity) == false)
            return false;
        entity.setId(this.findOneBy("name", entity.getName()).getId()); // refresh
        entity.getDishes().forEach((Dish dish) -> dish.setGroup(entity));
        
        if(updateDishes(entity) == false)
            return false;
        
        return true;
    }

    @Override
    public boolean update(DishGroup entity) {
        updateDishes(entity);
        return super.update(entity);
    }

    @Override
    public boolean delete(DishGroup entity) {
        for (Dish d : entity.getDishes()) {
            d.setGroup(null);
            dishManager.update(d);
        }
        return super.delete(entity);
    }

    private void hydrate(DishGroup group) {
        if (group == null) return;
        group.setDishes(dishManager.findBy("groupId", group.getId()));
    }

    private boolean updateDishes(DishGroup group) {
        Iterator<Dish> it = group.getDishes().iterator();
        while (it.hasNext()) {
            Dish d = it.next();
            if (dishManager.update(d) == false) return false;
        }
        return true;
    }

}
