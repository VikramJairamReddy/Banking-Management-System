/**
 * Acts as a layer between the GUI and the model.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.*;

import java.util.Collection;
import java.util.List;

public class BankController {

    private Bank bank;

    public BankController() {
        bank = new Bank();
    }

    // -------- ACCOUNT --------

    /**
     * Requests creation of a new account.
     *
     * @param account account to create
     * @return true if successful, otherwise false
     */
    public boolean createAccount(Account account) {
        return bank.createAccount(account);
    }

    /**
     * Requests deletion of an account.
     *
     * @param accountNumber account number of the account to delete
     * @return true if successful, otherwise false
     */
    public boolean removeAccount(String accountNumber) {
        return bank.removeAccount(accountNumber);
    }

    /**
     * Requests to find an account.
     *
     * @param accountNumber account number of the account be found
     * @return the account object if present, otherwise null
     */
    public Account findAccount(String accountNumber) {
        return bank.findAccount(accountNumber);
    }

    /**
     * Requests to get all Accounts.
     *
     * @return the collection of account objects.
     */
    public Collection<Account> getAllAccounts() {
        return bank.getAllAccounts();
    }

    /**
     * Checks whether an account exists.
     *
     * @param account account number to check
     * @return true if successful, otherwise false
     */
    public boolean accountExists(String accountNumber) {
        return bank.accountExists(accountNumber);
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
     * Requests a list of transaction history of a particular account.
     * 
     * @param accountNumber account number
     * @return List of all transactions
     * */
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return bank.getTransactionHistory(accountNumber);
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
}