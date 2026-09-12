/**
 * Handles database operations for banking transactions.
 *
 * Responsibilities:
 * - Add transactions.
 * - Retrieve all transactions.
 * - Retrieve transaction history for an account.
 * - Search transactions.
 * - Count today's transactions.
 * - Generate unique transaction IDs.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package DAO;

import Database.DatabaseConnection;
import Model.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionDAO {

    private Connection connection;

    /**
     * Creates a new TransactionDAO.
     */
    public TransactionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a transaction to the database.
     *
     * @param transaction transaction to add
     * @return true if successfully added, false otherwise
     */
    public boolean addTransaction(Transaction transaction) {

        String sql = "INSERT INTO Transactions "
                + "(transactionId, accountNumber, secondAccountNumber, "
                + "transactionType, amount, transactionTime) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, transaction.getTransactionId());
            statement.setString(2, transaction.getAccountNumber());
            statement.setString(3, transaction.getSecondAccountNumber());
            statement.setString(4, transaction.getTransactionType());
            statement.setDouble(5, transaction.getAmount());
            statement.setTimestamp(6, Timestamp.valueOf(transaction.getTime()));

            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to add transaction.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Finds a transaction by its transaction ID.
     *
     * @param transactionId transaction ID to find
     * @return transaction object, or null if not found
     */
    public Transaction findByTransactionId(long transactionId) {

        String sql = "SELECT * FROM Transactions "
                    + "WHERE transactionId = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, transactionId);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                return createTransaction(result);
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to find transaction.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves all transactions from the database.
     *
     * @return collection containing all transactions
     */
    public Collection<Transaction> getAllTransactions() {

        Collection<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM Transactions "
                + "ORDER BY transactionTime DESC";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                transactions.add(createTransaction(result));
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to retrieve transactions.");
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Retrieves all transactions related with an account.
     *
     * Includes transactions where the account is either the primary or secondary account.
     *
     * @param accountNumber account number to search
     * @return transactions associated with the account
     */
    public Collection<Transaction> getTransactionHistory(String accountNumber) {

        Collection<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM Transactions "
                + "WHERE accountNumber = ? "
                + "OR secondAccountNumber = ? "
                + "ORDER BY transactionTime DESC";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, accountNumber);
            statement.setString(2, accountNumber);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                transactions.add(createTransaction(result));
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to retrieve transaction history.");
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Searches transactions by account number, transaction type, or transaction ID.
     *
     * @param searchTerm value to search for
     * @return matching transactions
     */
    public Collection<Transaction> searchTransactions(String searchTerm) {

        Collection<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM Transactions "
                + "WHERE accountNumber LIKE ? "
                + "OR secondAccountNumber LIKE ? "
                + "OR transactionType LIKE ? "
                + "OR CAST(transactionId AS CHAR) LIKE ? "
                + "ORDER BY transactionTime DESC";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            String search = "%" + searchTerm + "%";

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                transactions.add(createTransaction(result));
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to search transactions.");
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Returns the number of transactions created today.
     *
     * @return number of today's transactions
     */
    public int getTodayTransactionCount() {

        String sql = "SELECT COUNT(*) FROM Transactions "
                + "WHERE DATE(transactionTime) = CURDATE()";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                return result.getInt(1);
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to count today's transactions.");
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Creates a Transaction object from a database result.
     *
     * @param result database result
     * @return transaction created from database data
     * @throws SQLException if a database value cannot be retrieved
     */
    private Transaction createTransaction(ResultSet result) throws SQLException {

        long transactionId = result.getLong("transactionId");
        String accountNumber = result.getString("accountNumber");
        String secondAccountNumber = result.getString("secondAccountNumber");
        String transactionType = result.getString("transactionType");
        double amount = result.getDouble("amount");
        LocalDateTime time = result.getTimestamp("transactionTime").toLocalDateTime();

        return new Transaction(transactionId, accountNumber, secondAccountNumber, transactionType, 
                amount, time);
    }


    /**
     * method to perform the withdraw action on the database
     * It will create a new transaction with the given account number, amount, and transaction type.
     * 
     * @param accountNumber the account number to withdraw from
     * @param amount the amount to withdraw
     * @return true if the transaction was successful, false otherwise
     */
    public boolean withdraw(String accountNumber, double amount) {

        String sql = "UPDATE Accounts SET balance = balance - ? WHERE accountNumber = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            long transactionId = generateTransactionId();

            statement.setDouble(1, amount);
            statement.setString(2, accountNumber);

            addTransaction(new Transaction(transactionId, accountNumber, 
                null, "withdraw", amount, LocalDateTime.now()));

            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to perform withdraw transaction.");
            e.printStackTrace();
        
        }
        return false;
    }

    /**
     * method to perform the deposit action on the database
     * It will create a new transaction with the given account number, amount, and transaction type.
     * 
     * @param accountNumber the account number to deposit to
     * @param amount the amount to deposit
     * @return true if the transaction was successful, false otherwise
     */
    public boolean deposit(String accountNumber, double amount) {

        String sql = "UPDATE Accounts SET balance = balance + ? WHERE accountNumber = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            long transactionId = generateTransactionId();

            statement.setDouble(1, amount);
            statement.setString(2, accountNumber);

            addTransaction(new Transaction(transactionId, accountNumber, 
                null, "withdraw", amount, LocalDateTime.now()));

            int result = statement.executeUpdate();

            statement.close();

            return result > 0;
        }
        catch(SQLException e) {
            System.out.println("Failed to perform withdraw transaction.");
            e.printStackTrace();
        
        }
        return false;
    }

    /**
     * Generates the next transaction ID.
     *
     * @return new transaction ID
     */
    public long generateTransactionId() {

        String sql = "SELECT MAX(transactionId) FROM Transactions";
        long nextId = 10000001;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if(result.next()) {

                long lastId = result.getLong(1);

                if(!result.wasNull()) {
                    nextId = lastId + 1;
                }
            }

            statement.close();
        }
        catch(SQLException e) {
            System.out.println("Failed to generate transaction ID.");
            e.printStackTrace();
        }

        return nextId;
    }
}