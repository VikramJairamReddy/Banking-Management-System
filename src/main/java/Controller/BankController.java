/**
 * Controller layer between the GUI and the banking model.
 *
 * Handles account, customer, and transaction operations by coordinating between 
 * the view, model, and DAO classes.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.*;

import java.util.Collection;
import java.util.List;

import DAO.AccountDAO;
import DAO.CustomerDAO;

public class BankController {

    private Bank bank;
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;

    public BankController() {
        this.bank = Bank.getInstance();
        this.customerDAO = new CustomerDAO();
        this.accountDAO = new AccountDAO();
    }

    // -------- ACCOUNT --------

    /**
     * Requests creation of a new account.
     *
     * @param name customer name
     * @param phone customer phone number
     * @param email customer email address
     * @param address customer address
     * @param type account type (Savings or Checking)
     * @return the created account object if successful, otherwise null
     */
    public Account createAccount(String name, String phone, String email, String address, String type) {

        String customerId = customerDAO.generateCustomerId();

        Customer customer = new Customer(customerId, name, phone, email, address);

        boolean success = customerDAO.addCustomer(customer);

        if(!success) {
            return null;
        }

        String accountNumber = accountDAO.generateAccountNumber();
        Account account;

        if("Savings".equals(type)) {
            account = new SavingsAccount(accountNumber, customer, Bank.getInterestRate());
        }
        else {
            account = new CheckingAccount(accountNumber, customer, Bank.getOverdraftLimit());
        }

        boolean accountCreated = accountDAO.addAccount(account);

        if(!accountCreated) {
            return null;
        }

        return account;
    }

    /**
     * Requests deletion of an account.
     *
     * @param accountNumber account number of the account to delete
     * @return true if successful, otherwise false
     */
    public boolean removeAccount(String accountNumber) {
        return accountDAO.deleteAccount(accountNumber);
    }

    /**
     * Requests to find an account.
     *
     * @param accountNumber account number of the account be found
     * @return the account object if present, otherwise null
     */
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber);
    }

    /**
     * Requests to get all Accounts.
     *
     * @return the collection of account objects.
     */
    public Collection<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    /**
     * Checks whether an account exists.
     *
     * @param account account number to check
     * @return true if successful, otherwise false
     */
    public boolean accountExists(String accountNumber) {
        return getAccountByAccountNumber(accountNumber) != null;
    }

    /**
     * Searches accounts using the search value.
     *
     * @param search search value entered by the user
     * @return collection of matching accounts
     */
    public Collection<Account> searchAccounts(String search) {
        return accountDAO.searchAccounts(search);
    }

    // -------- TRANSACTIONS --------

    /**
     * Requests a deposit operation.
     *
     * @param accountNumber destination account
     * @param amount amount to deposit
     * @return true if successful, otherwise false
     */
    public boolean deposit(String accountNumber, double amount) {
        return bank.deposit(accountNumber, amount);
    }

    /**
     * Requests a withdrawal operation.
     *
     * @param accountNumber account number
     * @param amount amount to withdraw
     * @return true if successful, otherwise false
     */
    public boolean withdraw(String accountNumber, double amount) {
        return bank.withdraw(accountNumber, amount);
    }

    /**
     * Requests a transfer between accounts.
     *
     * @param from source account
     * @param to destination account
     * @param amount amount to transfer
     * @return true if successful, false otherwise
     */
    public boolean transfer(String from, String to, double amount) {
        return bank.transfer(from, to, amount);
    }

    /**
     * Returns all transactions in the bank.
     *
     * @return list of all transactions
     */
    public List<Transaction> getAllTransactions() {
        return bank.getAllTransactions();
    }

    /**
     * Requests a list of transaction history of a particular account.
     * 
     * @param accountNumber account number
     * @return List of all transactions
     * */
    public List<Transaction> getTransactionHistory(String accountNumber) {
        if(!bank.accountExists(accountNumber)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return bank.getTransactionHistory(accountNumber);
    }

    /**
     * Searches transactions based on the provided search term.
     * 
     * @param searchTerm value used to search transactions
     * @return list of transactions matching the search term
     */
    public List<Transaction> searchTransactions(String searchTerm) {
        return bank.searchTransactions(searchTerm);
    }

    // -------- ID GENERATION --------

    /**
     * Requests generation of new account number.
     *
     * @return unique account number
     */
    public String generateAccountNumber() {
        return bank.generateAccountNumber();
    }

    /**
     * return the total number of accounts stored.
     *
     * @return total number of Accounts
     */
    public int getNumberOfAccounts() {
        return bank.getNumberOfAccounts();
    }

    /**
     * Returns the number of transactions that occurred today.
     *
     * @return count of today's transactions
     */
    public int getTodayTransactionCount() {
        return bank.getTodayTransactionCount();
    }
}