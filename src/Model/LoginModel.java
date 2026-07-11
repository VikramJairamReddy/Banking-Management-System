/**
 * Represents the login model of the banking system.
 * Responsible for validating employee login credentials.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

import java.util.Map;
import java.util.HashMap;

public class LoginModel {

    // Stores users with username as the key
    private final Map<String, User> users;
    
    public LoginModel() {
        users = new HashMap<>();
    
        users.put("admin", new User("admin", "admin12", Role.ADMIN));
        users.put("manager", new User("manager", "manager12", Role.MANAGER));
        users.put("employee", new User("employee", "employee12", Role.EMPLOYEE));
    }

    /**
     * Validates the entered username and password against the stored login data.
     *
     * @param username username entered
     * @param password password entered
     * @return User object if the entered credentials are valid, otherwise null
     */
    public User validateLogin(String username, String password) {
        User user = users.get(username);

        if(user != null && user.checkPassword(password)) {
            return user;
        }

        return null;
    }
}