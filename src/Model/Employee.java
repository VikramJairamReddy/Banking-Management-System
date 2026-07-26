/**
 * Represents an employee in the Banking Management System.
 *
 * Stores employee information including employee ID, name,
 * login credentials, and assigned role.
 *
 * Password is kept private and is only checked through
 * the checkPassword() method for authentication.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class Employee {

    private final String employeeId;
    private final String name;
    private final String username;
    private String password;
    private Role role;
    private String email;


    /**
     * Creates a new employee object.
     *
     * @param employeeId unique employee ID
     * @param name employee full name
     * @param username employee login username
     * @param password employee login password
     * @param role employee assigned role
     */
    public Employee(String employeeId, String name, String username, String password, 
                    Role role, String email) {

        this.employeeId = employeeId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    /**
     * Returns the employee ID.
     *
     * @return employee ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the employee name.
     *
     * @return employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the employee username.
     *
     * @return employee username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the employee role.
     *
     * @return employee role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Returns the employee email.
     *
     * @return employee email
     */
    public String getEmployeeEmail() {
        return email;
    }

    /**
     * Updates the employee role.
     *
     * @param role new employee role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Updates the employee email.
     *
     * @param email new employee email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks whether the entered password matches
     * the employee's stored password.
     *
     * @param password password entered during login
     * @return true if password matches, otherwise false
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}