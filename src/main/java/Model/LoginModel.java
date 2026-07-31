/**
 * Handles employee authentication.
 *
 * Responsibilities:
 * - Authenticate login.
 * - Validate username and password.
 * - Return the authenticated employee.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

import DAO.EmployeeDAO;

public class LoginModel {

    private final EmployeeDAO employeeDAO;

    /**
     * Creates the login model.
     */
    public LoginModel() {
        employeeDAO = new EmployeeDAO();
    }

    /**
     * Validates the entered username and password against the stored login data.
     *
     * @param username username entered
     * @param password password entered
     * @return the authenticated employee
     */
    public Employee validateLogin(String username, String password) {

        Employee employee = employeeDAO.findByUsername(username);

        if(employee == null || !employee.checkPassword(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return employee;
    }
}