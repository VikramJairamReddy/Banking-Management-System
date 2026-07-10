/**
 * Represents the banking system
 *
 * Responsibilities:
 * - Store and manage all accounts.
 * - Handling deposits, withdrawals, and transfers.
 * - Maintain transaction history.
 * - Generate unique account and transaction ID's.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Model;

import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;

public class Bank {

    private static final String DEPOSIT = "Deposit";
    private static final String WITHDRAW = "Withdraw";
    private static final String TRANSFER_SENDER = "Transfer Sender";
    private static final String TRANSFER_RECEIVER = "Transfer Receiver";
    private static final double INTEREST_RATE = 6.0;

    private long nextAccountNumber;
    private long transactionId ;

    private final Map<String, Account> accounts;
    private final Map<String, List<Transaction>> transactionHistory;

    public Bank() {
        accounts = new HashMap<>();
        transactionHistory = new HashMap<>();
        nextAccountNumber = 100001;
        transactionId = 10000000;
    }
    
    //  -------- ACCOUNTS --------

    /**
     * Creating a new account to the bank.
     *
     * @param account the account to add
     * @return true if the account was successfully created, otherwise false
     */
    public boolean createAccount(Account account) {
        if(account == null) {
            return false;
        }

        String accountNumber = account.getAccountNumber();

        if(accountExists(accountNumber) ||
            !Validate.isValidName(account.getAccountHolderName()) || 
            !Validate.isValidPhoneNumber(account.getPhoneNumber())) {

            return false;
        }

        accounts.put(accountNumber, account);
        return true;
    }

    /**
     * Removes an account and its transaction history.
     *
     * @param accountNumber account number of the account to be removed
     * @return true if successfully removed, otherwise false
     */
    public boolean removeAccount(String accountNumber) {
        if(!accountExists(accountNumber)) {
            return false;
        }

        accounts.remove(accountNumber);
        transactionHistory.remove(accountNumber);
        return true;
    }
    
    /**
     * Finds an account by account number.
     *
     * @param accountNumber account number to search
     * @return matching Account object, or null if not found
     */
    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Searches for accounts whose account holder name contains
     * the given search word, irrespective of Upper or Lower Case.
     *
     * @param name the name to search
     * @return a list of matching accounts; empty list if no results found
     */
    public List<Account> getAccountByName(String name) {
        List<Account> list = new ArrayList<>();

        if(name == null || name.trim().isEmpty()) {
            return list;
        }

        String searchName = name.trim().toLowerCase(); 

        for(Account account : accounts.values()) {
            if(account.getAccountHolderName().toLowerCase().contains(searchName)) {
                list.add(account);
            }
        }
        return list;
    }

    /**
     * Checks is the given account number exists and is a valid account number
     * 
     * @param accountNumber account number to be checked
     **/
    public boolean accountExists(String accountNumber) {
        return Validate.isValidAccountNumber(accountNumber) && accounts.containsKey(accountNumber);
    }

    /**
     * This method returns all the accounts stored
     * 
     * @return the Collection of Accounts
     **/
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    //  -------- TRANSACTIONS --------

    /**
     * Deposits money into an account and store it in transaction history
     *
     * @param accountNumber destination account number
     * @param amount amount to be deposited
     * @return true if deposit was successful, otherwise false
     */
    public boolean deposit(String accountNumber, double amount) {
        if(!accountExists(accountNumber)) {
            throw new IllegalArgumentException("Account not found");
        }
    
        if(!Validate.isValidAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
    
        Account account = accounts.get(accountNumber);
    
        if(account.deposit(amount)) {
            addTransaction(accountNumber, amount, DEPOSIT);
            return true;
        }
    
        throw new IllegalArgumentException("Deposit failed");
    }

    /**
     * Withdraws money from an account and store it in transaction history.
     *
     * @param accountNumber account number
     * @param amount amount to withdraw
     * @return true if withdrawal was successful, otherwise false
     */
    public boolean withdraw(String accountNumber, double amount) {
        if(!accountExists(accountNumber)) {
            throw new IllegalArgumentException("Account not found");
        }
    
        if(!Validate.isValidAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
    
        Account account = accounts.get(accountNumber);
    
        // Checks balance and overdraft limit
        if(amount > account.possibleWithdraw()) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    
        if(account.withdraw(amount)) {
            addTransaction(accountNumber, amount, WITHDRAW);
            return true;
        }
    
        throw new IllegalArgumentException("Withdrawal failed");
    }

    /**
     * Transfers money between two accounts.
     *
     * @param fromAccountNumber source account number
     * @param toAccountNumber destination account number
     * @param amount amount to transfer
     * @return true if transfer was successful, otherwise false
     */
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        if(!accountExists(fromAccountNumber)) {
            throw new IllegalArgumentException("Sender account not found");
        }

        if(!accountExists(toAccountNumber)) {
            throw new IllegalArgumentException("Recipient account not found");
        }

        if(!Validate.isValidAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if(fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        Account sender = accounts.get(fromAccountNumber);
        Account receiver = accounts.get(toAccountNumber);

        // Check sender balance before withdrawal
        if(amount > sender.possibleWithdraw()) {
            throw new IllegalArgumentException("Insufficient balance");
        }


        if(!sender.withdraw(amount)) {
            throw new IllegalArgumentException("Transfer failed");
        }

        if(receiver.deposit(amount)) {
            addTransaction(fromAccountNumber, amount, TRANSFER_SENDER);
            addTransaction(toAccountNumber, amount,TRANSFER_RECEIVER);
            return true;
        }


        // Rollback if receiver deposit fails
        if(!sender.deposit(amount)) {
            throw new IllegalStateException("Rollback failed. Contact ADMIN.");
        }

        throw new IllegalArgumentException("Transfer failed. Amount returned to sender");
    }

    /**
     * Records a transaction in the account's history.
     *
     * @param accountNumber associated account
     * @param amount transaction amount
     * @param type transaction type
     */
    public void addTransaction(String accountNumber, double amount, String transactionType) {
        if(transactionHistory.get(accountNumber) == null) {
            transactionHistory.put(accountNumber, new ArrayList<>());
        }

        transactionHistory.get(accountNumber)
            .add(new Transaction(generateTransactionId(), accountNumber, transactionType, amount));
    }

    /**
     * To give all the transaction history of a particular account
     * 
     * @param accountNumber account number of the account
     * @return the list of all the Transaction made
     */
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return (transactionHistory.containsKey(accountNumber))?
        new ArrayList<>(transactionHistory.get(accountNumber)) : new ArrayList<>();
    }

    /**
     * Returns the number of transactions that occurred today.
     *
     * @return count of today's transactions
     */
    public int getTodayTransactionCount() {

        int count = 0;
        LocalDate today = LocalDate.now();

        for(List<Transaction> list : transactionHistory.values()) {
            for(Transaction t : list) {
                if(t.getTime().toLocalDate().equals(today)) {
                    count++;
                }
            }
        }

        return count;
    }

    // -------- GENERATING ID --------

    /**
     * Generates a unique account number.
     *
     * @return newly generated account number
     */
    public String generateAccountNumber() {
        return "ACCT" + nextAccountNumber++;
    }

    /**
     * Generates a unique transaction ID.
     *
     * @return newly generated transaction ID
     */
    public long generateTransactionId() {
        return ++transactionId;
    }

    /**
     * return the total number of accounts stored.
     *
     * @return total number of Accounts
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Returns the Interest rate applied on the account.
     * It can only be called on the class name because it is a static method
     * 
     * @return the Interest rate applied on the savings accouont
     */
    public static double getInterestRate() {
        return INTEREST_RATE;
    }
}
