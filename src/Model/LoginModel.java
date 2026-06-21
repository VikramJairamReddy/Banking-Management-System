/**
 * Represents the login model of the banking system.
 * Responsible for validating employee login credentials.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class LoginModel {

    // Default employee username
    private static final String USERNAME = "bank";

    // Default employee password
    private static final String PASSWORD = "admin12";

    /**
     * Validates the entered username and password against the stored login data.
     *
     * @param username username entered by the employee
     * @param password password entered by the employee
     * @return true if the entered credentials are valid, otherwise false
     */
    public boolean isValidLogin(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}