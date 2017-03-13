package database.manager;

import bean.User;

public class UserManager extends ObjectManager<User> {

    public UserManager() {
        super(User.class);
    }

    public User findByUsername(String username) {
        return (User) this.findOneBy("username", username);
    }

}
