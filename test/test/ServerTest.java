package test;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import org.junit.Before;
import org.junit.Test;
import bean.Dish;
import bean.DishGroup;
import bean.User;
import rmi.Server;

public class ServerTest {

    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server();
    }

    @Test
    public void testUser() throws RemoteException {
        String username = "admin";
        String password = "admin";
        User result = server.getUser(username, password);

        /* Get user */
        assertNotNull(result);
        assertTrue(username.equals(result.getUsername()));
        assertTrue(password.equals(result.getPassword()));

        /* Get invalid user */
        assertNull(server.getUser("truc", "truc"));
    }

    @Test
    public void testDish() throws RemoteException {
        Dish result, updated;
        Dish dish = new Dish();
        dish.setName("Villageoise");
        dish.setDescription("Un vin rouge raffiné");
        dish.setPrice(0.80f);

        /* Create */
        assertTrue(server.createDish(dish));
        result = server.getDish(dish.getName());
        assertTrue(dish.getName().equals(result.getName()));

        /* Edit */
        result.setPrice(1.2f);
        assertTrue(server.editDish(result));
        updated = server.getDish(result.getName());
        assertTrue(result.getPrice() == updated.getPrice());

        /* Delete */
        assertTrue(server.deleteDish(updated));
        assertNull(server.getDish(updated.getName()));
    }

    @Test
    public void testDishGroup() throws RemoteException {
        DishGroup result, updated;
        DishGroup wines = new DishGroup();

        /* Create dishes */
        Dish dish = new Dish();
        dish.setName("Villageoise");
        dish.setDescription("A sophisticated red wine");
        dish.setPrice(0.80f);
        Dish dish2 = new Dish();
        dish2.setName("Muscade");
        dish2.setDescription("A first-rate white wine");
        dish2.setPrice(8.3f);
        server.createDish(dish); // maybe already created
        server.createDish(dish2);// same
        assertNotNull(dish = server.getDish(dish.getName()));
        assertNotNull(dish2 = server.getDish(dish2.getName()));

        /* Start */
        wines.setName("Wines");
        wines.addDish(dish);

        /* Create */
        assertTrue(server.createGroup(wines));
        result = server.getGroup(wines.getName());
        if (result == null) fail();
        /* Group OK */
        assertTrue(wines.getName().equals(result.getName()));
        /* Dish loaded */
        assertTrue(result.getDishes().size() == 1);
        assertNotNull(result.getDishes().get(0).getName().equals("Villageoise"));

        /* Update : change name and add a dish */
        result.setName("Best Wines");
        result.addDish(dish2);
        assertTrue(server.editGroup(result));
        updated = server.getGroup(result.getName());
        assertNotNull(updated);
        assertTrue(updated.getName().equals(result.getName()));
        assertTrue(updated.getDishes().size() == 2);

        /* Delete */
        assertTrue(server.deleteGroup(result));
        assertTrue(server.deleteDish(dish));
        assertTrue(server.deleteDish(dish2));
    }

}
