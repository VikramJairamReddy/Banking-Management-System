/**
 * Stores and manages all employees.
 *
 * Responsibilities:
 * - Add and remove employees.
 * - Search employees by ID or username.
 * - Store all employee records.
 * - Generate unique employee IDs.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    private static EmployeeManager instance;

    private final Map<String, Employee> employeesById;
    private final Map<String, Employee> employeesByUsername;

    private int nextEmployeeId = 1001;

    /**
     * Creates the employee manager and loads default employees.
     */
    public EmployeeManager() {

        employeesById = new HashMap<>();
        employeesByUsername = new HashMap<>();

        addEmployee("Administrator", "admin", "admin123", Role.ADMIN, "admin@gmail.com");
        addEmployee("Manager", "manager", "manager123", Role.MANAGER, "manager@gmail.com");
        addEmployee("Employee", "employee", "employee123", Role.EMPLOYEE, "employee@gmail.com");
    }

    /**
     * Returns the single EmployeeManager instance.
     *
     * @return EmployeeManager instance
     */
    public static EmployeeManager getInstance() {

        if(instance == null) {
            instance = new EmployeeManager();
        }

        return instance;
    }

    /**
     * Checks whether a username already exists.
     *
     * @param username username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        return employeesByUsername.containsKey(username.toLowerCase());
    }

    /**
     * Creates a new employee and stores it.
     *
     * @param name employee name
     * @param username login username
     * @param password login password
     * @param role employee role
     * @return created employee
     */
    public void addEmployee(String name, String username, String password, Role role, String email) {

        if(usernameExists(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        String employeeId = generateEmployeeId();

        Employee employee = new Employee(employeeId, name, username.toLowerCase(), password, role, email);
        employeesById.put(employeeId.toLowerCase(), employee);
        employeesByUsername.put(username, employee);
    }

    /**
     * Removes an employee.
     *
     * @param employeeId employee ID
     * @return true if removed successfully
     */
    public boolean removeEmployeeById(String employeeId) {

        Employee employee = employeesById.remove(employeeId);

        if(employee != null) {
            employeesByUsername.remove(employee.getUsername());
            return true;
        }

        return false;
    }

    /**
     * Finds an employee using employee ID.
     *
     * @param employeeId employee ID
     * @return matching employee or null
     */
    public Employee findEmployeeById(String employeeId) {
        return employeesById.get(employeeId);
    }

    /**
     * Finds an employee using employee username.
     *
     * @param employeeId employee username
     * @return matching employee or null
     */
    public Employee findEmployeeByUsername(String username) {
        return employeesByUsername.get(username.toLowerCase());
    }

    /**
     * Updates an existing employee's role and email.
     * Finds the employee using the employee ID and the updated role and email values.
     *
     * @param employeeId employee ID of the employee to update
     * @param role new employee role
     * @param email new employee email address
     * @return true if employee was updated successfully, false if employee was not found
     */
    public boolean updateEmployee(String employeeId, Role role, String email) {

        Employee employee = findEmployeeById(employeeId);
    
        if(employee == null) {
            return false;
        }
    
        employee.setRole(role);
        employee.setEmail(email);
    
        return true;
    }

    /**
     * Searches employees using employee ID, name, or username.
     *
     * @param search search text
     * @return matching employees
     */
    public Collection<Employee> searchEmployees(String search) {

        Collection<Employee> results = new ArrayList<>();
        String value = search.toLowerCase();

        for(Employee employee : employeesById.values()) {

            if(employee.getEmployeeId().toLowerCase().contains(value) || 
                employee.getName().toLowerCase().contains(value) || 
                employee.getUsername().toLowerCase().contains(value)) {

                results.add(employee);
            }
        }

        return results;
    }

    /**
     * Returns all employees.
     *
     * @return collection of employees
     */
    public Collection<Employee> getAllEmployees() {
        return employeesById.values();
    }

    /**
     * Returns the total number of employees.
     *
     * @return employee count
     */
    public int getTotalEmployeesCount() {
        return employeesById.size();
    }

    /**
     * Generates a unique employee ID.
     *
     * @return generated employee ID
     */
    private String generateEmployeeId() {
        return "EMP" + nextEmployeeId++;
    }
}