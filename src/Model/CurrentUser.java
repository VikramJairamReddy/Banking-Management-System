/**
 * Manages the current logged-in user.
 * Stores user information after successful login.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class CurrentUser {

    // Stores the currently logged-in user.
    private static User currentUser;

    /**
     * Sets the current user after successful login.
     *
     * @param user authenticated user
     */
    public static void login(User user) {
        currentUser = user;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return current logged-in user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current user during logout.
     */
    public static void logout() {
        currentUser = null;
    }
}