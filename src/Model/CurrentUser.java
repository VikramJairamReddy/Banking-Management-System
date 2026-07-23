/**
 * Manages the current logged-in user.
 * Stores user information after successful login.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class CurrentUser {

    // Stores the currently logged-in user.
    private static Employee currentUser;

    /**
     * Sets the current user after successful login.
     *
     * @param user authenticated user
     */
    public static void login(Employee user) {
        currentUser = user;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return current logged-in user
     */
    public static Employee getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the current user during logout.
     */
    public static void logout() {
        currentUser = null;
    }

    /**
     * Returns if any user logged-in or not
     * 
     * @return returns true if logged-in by any user, otherwise false
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}