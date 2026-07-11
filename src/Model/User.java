/**
 * Represents a user who can log in to the banking management system.
 * Each user has a username, password, and role that determines the permissions.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class User {

    private String username;
    private String password;
    private Role role;

    /**
     * Creates a new user with the specified credentials and role.
     *
     * @param username the login username
     * @param password the login password
     * @param role the role of the user
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the role assigned to the user.
     *
     * @return the user's role
     */
    public Role getRole() {
        return role;
    }

    /**
     * verifies whether the provided password matches the user's password.
     * 
     * @param password the password entered by the user during login
     * @return true if the password matches the stored password, otherwise false
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}