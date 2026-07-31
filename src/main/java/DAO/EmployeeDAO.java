/**
 * Provides database operations for Employee records.
 *
 * Responsibilities:
 * - Add, find, update, and delete employees in the MySQL database.
 * - Convert database rows into Employee objects.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package DAO;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import Model.Employee;
import Model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    // Connection used for all employee database operations.
    private Connection connection;

    /**
     * Creates an EmployeeDAO and gets a database connection.
     */
    public EmployeeDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a new employee to the database.
     *
     * @param employee employee object to store
     * @return true if employee is added successfully, otherwise false
     */
    public boolean addEmployee(Employee employee) {

        String sql = "INSERT INTO Employees "
                + "(employeeId, username, password, role, name, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, employee.getEmployeeId());
            statement.setString(2, employee.getUsername());
            statement.setString(3, employee.getPassword());
            statement.setString(4, employee.getRole().toString());
            statement.setString(5, employee.getName());
            statement.setString(6, employee.getEmployeeEmail());

            int rowsInserted = statement.executeUpdate();

            statement.close();

            return rowsInserted > 0;
        } 
        catch(SQLException e) {
            System.out.println("Failed to add employee.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds an employee using username.
     *
     * @param username employee username
     * @return Employee object if found, otherwise null
     */
    public Employee findByUsername(String username) {

        String sql = "SELECT * FROM Employees WHERE username = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if(result.next()) {

                String employeeId = result.getString("employeeId");
                String name = result.getString("name");
                String employeeUsername = result.getString("username");
                String password = result.getString("password");
                String roleValue = result.getString("role");
                String email = result.getString("email");

                Role role = Role.valueOf(roleValue);

                return new Employee(employeeId, name, employeeUsername, password, role, email);
            }
        } 
        catch(SQLException e) {
            System.out.println("Failed to find employee.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Finds an employee using employee ID.
     *
     * @param employeeId employee ID
     * @return Employee object if found, otherwise null
     */
    public Employee findById(String employeeId) {

        String sql = "SELECT * FROM Employees WHERE employeeId = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employeeId);
            ResultSet result = statement.executeQuery();

            if(result.next()) {

                String id = result.getString("employeeId");
                String name = result.getString("name");
                String employeeUsername = result.getString("username");
                String password = result.getString("password");
                String roleValue = result.getString("role");
                String email = result.getString("email");

                Role role = Role.valueOf(roleValue);

                return new Employee(id, name, employeeUsername, password, role, email);
            }
        } 
        catch(SQLException e) {
            System.out.println("Failed to find employee.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return list of all employees
     */
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM Employees";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                String employeeId = result.getString("employeeId");
                String name = result.getString("name");
                String username = result.getString("username");
                String password = result.getString("password");
                String roleValue = result.getString("role");
                String email = result.getString("email");

                Role role = Role.valueOf(roleValue);

                Employee employee = new Employee(employeeId, name, username, password, role, email);

                employees.add(employee);
            }


        } 
        catch (SQLException e) {
            System.out.println("Failed to get employees.");
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * Searches employees by employee ID, name, or username.
     *
     * @param search search keyword
     * @return list of matching employees
     */
    public List<Employee> searchEmployees(String search) {

        List<Employee> results = new ArrayList<>();

        String sql = "SELECT * FROM Employees "
                + "WHERE LOWER(employeeId) LIKE ? "
                + "OR LOWER(name) LIKE ? "
                + "OR LOWER(username) LIKE ?";

        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            String value = "%" + search.toLowerCase() + "%";

            statement.setString(1, value);
            statement.setString(2, value);
            statement.setString(3, value);

            ResultSet result = statement.executeQuery();

            while(result.next()) {

                String employeeId = result.getString("employeeId");
                String name = result.getString("name");
                String username = result.getString("username");
                String password = result.getString("password");
                String roleValue = result.getString("role");
                String email = result.getString("email");

                Role role = Role.valueOf(roleValue);

                Employee employee = new Employee(employeeId, name, username, password, role, email);

                results.add(employee);
            }

        } 
        catch(SQLException e) {
            System.out.println("Failed to search employees.");
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Updates employee information in the database.
     *
     * @param employee employee object with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean updateEmployee(Employee employee) {

        String sql = "UPDATE Employees SET "
                + "role = ?, "
                + "email = ? "
                + "WHERE employeeId = ?";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, employee.getRole().toString());
            statement.setString(2, employee.getEmployeeEmail());
            statement.setString(3, employee.getEmployeeId());

            int rowsUpdated = statement.executeUpdate();

            statement.close();

            return rowsUpdated > 0;


        } 
        catch(SQLException e) {

            System.out.println("Failed to update employee.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an employee from the database.
     *
     * @param employeeId employee ID to remove
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteEmployee(String employeeId) {

        String sql = "DELETE FROM Employees WHERE employeeId = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employeeId);

            int rowsDeleted = statement.executeUpdate();

            statement.close();

            return rowsDeleted > 0;
        } 
        catch(SQLException e) {
            System.out.println("Failed to delete employee.");
            e.printStackTrace();
            return false;
        }
    }
}
