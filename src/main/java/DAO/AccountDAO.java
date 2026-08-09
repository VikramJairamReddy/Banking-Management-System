/**
 * Handles database operations for bank accounts.
 *
 * Responsibilities:
 * - Add new accounts.
 * - Find accounts by account number.
 * - Remove accounts.
 * - Search accounts.
 * - Retrieve all accounts.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package DAO;

import Database.DatabaseConnection;
import Model.Account;
import Model.CheckingAccount;
import Model.Customer;
import Model.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccountDAO {

    private Connection connection;

    /**
     * Creates a new AccountDAO.
     */
    public AccountDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a new account to database.
     *
     * @param account account to add
     * @return true if successful
     */
    public boolean addAccount(Account account) {

        String sql = "INSERT INTO Accounts "
                + "(accountNumber, customerId, balance, accountType, interestRate, overdraftLimit) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, account.getAccountNumber());
            statement.setString(2, account.getCustomer().getCustomerId());
            statement.setDouble(3, account.getBalance());

            if(account instanceof SavingsAccount) {

                SavingsAccount savings = (SavingsAccount) account;

                statement.setString(4, "savings");
                statement.setDouble(5, savings.getInterestRate());
                statement.setDouble(6, 0.0);
            }
            else {
                CheckingAccount checking = (CheckingAccount) account;

                statement.setString(4, "checking");
                statement.setDouble(5, 0.0);
                statement.setDouble(6, checking.getOverdraftLimit());
            }

            int result = statement.executeUpdate();
            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to add account.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Finds account by account number.
     *
     * @param accountNumber account number
     * @return account object
     */
    public Account findByAccountNumber(String accountNumber) {

        String sql = "SELECT a.*, c.name, c.phone, c.email, c.address "
                    + "FROM Accounts AS a "
                    + "JOIN Customers AS c ON a.customerId = c.customerId "
                    + "WHERE a.accountNumber = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountNumber);
            ResultSet result = statement.executeQuery();

            if(result.next()) {

                String customerId = result.getString("customerId");
                Customer customer = new Customer(customerId,
                        result.getString("name"),
                        result.getString("phone"),
                        result.getString("email"),
                        result.getString("address"));

                String type = result.getString("accountType");
                double balance = result.getDouble("balance");

                Account account;
                
                if(type.equals("savings")) {
                    double interestRate = result.getDouble("interestRate");

                    SavingsAccount savingsAccount = new SavingsAccount(accountNumber, customer, 
                                    interestRate);

                    if(balance > 0) {
                        savingsAccount.deposit(balance);
                    }

                    account = savingsAccount;
            
                }
                else {
                    double overdraftLimit = result.getDouble("overdraftLimit");

                    CheckingAccount checkingAccount = new CheckingAccount(accountNumber,customer,
                                    overdraftLimit);

                    if(balance >= 0) {
                        checkingAccount.deposit(balance);
                    }
                    else {
                        checkingAccount.withdraw(Math.abs(balance));
                    }
                    
                    account = checkingAccount;
                }

                return account;
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to find account.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes an account.
     *
     * @param accountNumber account number
     * @return true if deleted
     */
    public boolean deleteAccount(String accountNumber) {

        String sql = "DELETE FROM Accounts WHERE accountNumber = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountNumber);
            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to delete account.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Returns all accounts.
     *
     * @return all accounts
     */
    public Collection<Account> getAllAccounts() {

        Collection<Account> accounts = new ArrayList<>();

        String sql = "SELECT accountNumber FROM Accounts";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                Account account = findByAccountNumber(result.getString("accountNumber"));

                if(account != null) {
                    accounts.add(account);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to retrieve accounts.");
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * Searches accounts by account number or customer name.
     *
     * @param search search value
     * @return matching accounts
     */
    public Collection<Account> searchAccounts(String search) {

        Collection<Account> accounts = new ArrayList<>();

        String sql = "SELECT accountNumber "
                    + "FROM Accounts a "
                    + "JOIN customers c ON a.customerId = c.customerId "
                    + "WHERE a.accountNumber LIKE ? OR c.name LIKE ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Account account = findByAccountNumber(result.getString("accountNumber"));

                if(account != null) {
                    accounts.add(account);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to search accounts.");
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * Generates unique account number.
     *
     * @return new account number
     */
    public String generateAccountNumber() {

        String sql = "SELECT MAX(accountNumber) FROM Accounts";
        int nextId = 100001;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if(result.next()) {

                String lastId = result.getString(1);

                if(lastId != null) {
                    int number = Integer.parseInt(lastId.substring(4));
                    nextId = number + 1;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Failed to generate account number.");
            e.printStackTrace();
        }

        return "ACCT" + nextId;
    }
}