/**
 * Handles database operations for customers.
 *
 * Responsibilities:
 * - Add customers.
 * - Find customers.
 * - Update customer information.
 * - Delete customers.
 * - Search customers.
 * - Retrieve all customers.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package DAO;

import Database.DatabaseConnection;
import Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDAO {

    // Database connection
    private Connection connection;

    /**
     * Creates CustomerDAO object.
     */
    public CustomerDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a customer into database.
     *
     * @param customer customer object
     * @return true if added successfully
     */
    public boolean addCustomer(Customer customer) {

        String sql = "INSERT INTO Customers "
                    + "(customerId, name, phone, email, address) "
                    + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getAddress());

            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to add customer.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Finds customer using customer ID.
     *
     * @param customerId customer ID
     * @return customer object
     */
    public Customer findById(String customerId) {

        String sql = "SELECT * FROM Customers WHERE customerId = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customerId);

            ResultSet result = statement.executeQuery();

            if(result.next()) {

                return new Customer(
                        result.getString("customerId"),
                        result.getString("name"),
                        result.getString("phone"),
                        result.getString("email"),
                        result.getString("address")
                );
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to find customer.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns all customers.
     *
     * @return collection of customers
     */
    public Collection<Customer> getAllCustomers() {

        Collection<Customer> customers = new ArrayList<>();

        String sql = "SELECT customerId FROM Customers";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while(result.next()) {

                Customer customer = findById(result.getString("customerId"));

                if(customer != null) {
                    customers.add(customer);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to retrieve customers.");
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Searches customers by ID, name, phone or email.
     *
     * @param search search text
     * @return matching customers
     */
    public Collection<Customer> searchCustomers(String search) {

        Collection<Customer> customers = new ArrayList<>();

        String sql = "SELECT customerId FROM Customers "
                    + "WHERE customerId LIKE ? OR name LIKE ? OR phone LIKE ? OR email LIKE ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            String value = "%" + search + "%";

            statement.setString(1, value);
            statement.setString(2, value);
            statement.setString(3, value);
            statement.setString(4, value);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = findById(result.getString("customerId"));

                if(customer != null) {
                    customers.add(customer);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to search customers.");
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Updates customer information.
     *
     * @param customer updated customer
     * @return true if updated
     */
    public boolean updateCustomer(Customer customer) {

        String sql = "UPDATE Customers SET "
                    + "name=?, phone=?, email=?, address=? "
                    + "WHERE customerId=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getCustomerId());

            int result = statement.executeUpdate();
            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to update customer.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Deletes customer.
     *
     * @param customerId customer ID
     * @return true if deleted
     */
    public boolean deleteCustomer(String customerId) {

        String sql = "DELETE FROM Customers WHERE customerId=?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerId);

            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to delete customer.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Generates customer ID.
     *
     * @return generated ID
     */
    public String generateCustomerId() {

        String sql = "SELECT MAX(customerId) FROM Customers";
        int nextId = 100001;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();


            if(result.next()) {
                String lastId = result.getString(1);

                if(lastId != null) {
                    int number = Integer.parseInt(lastId.substring(1));
                    nextId = number + 1;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to generate customer ID.");
            e.printStackTrace();
        }

        return "C" + nextId;
    }
}