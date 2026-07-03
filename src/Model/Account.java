/**
 * Abstract base class representing a bank account.
 *
 * Stores common account information:
 * - Account number
 * - Account holder name
 * - Phone number
 * - Balance
 *
 * Provides basic deposit and withdrawal functionality shared by all account types.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public abstract class Account {

    /**
     * Returns the type of account.
     *
     * @return account type as a String
     */
    public abstract String getAccountType();

    private final String ACCOUNT_NUMBER;
    private String accountHolderName;
    private double balance = 0;
    private String phoneNumber;

    /**
     * Creates a new account.
     *
     * @param accountNumber unique account number
     * @param accountHolderName account holder's name
     * @param phoneNumber account holder's phone number
     */
    public Account(String accountNumber, String accountHolderName, String phoneNumber) {

        if(!Validate.isValidAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid Account Number");
        }
        if(!Validate.isValidName(accountHolderName)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if(!Validate.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
        this.ACCOUNT_NUMBER = accountNumber;
        this.accountHolderName = accountHolderName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method to deposit money into the account.
     * @param amount The amount to be deposited. Must be greater than 0.
     **/
    public boolean deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    /**
     * Method to withdraw money from the account.
     * @param amount The amount to be withdrawn. 
     * Must be greater than 0 and less than or equal to the current balance.
     **/
    public boolean withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Setters for account properties
    public boolean setAccountHolderName(String name) {

        if(!Validate.isValidName(name)) {
            return false;
        }
        this.accountHolderName = name;

        return true;
    }

    public boolean setPhoneNumber(String phoneNumber) {

        if(!Validate.isValidPhoneNumber(phoneNumber)) {
            return false;
        }
        this.phoneNumber = phoneNumber;

        return true;
    }

    /** Protected method only allows subclasses to modify the balance, 
     * ensuring that the balance changes through the deposit and withdraw methods only.
     * 
     * @param amount The amount to be added to the balance. 
     * positive for deposits, and negative for withdrawals.
     **/
    protected void manageBalance(double amount) {
        this.balance += amount;
    }

    // Getters for the account properties
    public String getAccountNumber() {
        return ACCOUNT_NUMBER;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a formatted string representation of the account.
     */
    @Override
    public String toString() {
        return String.format
            ("[type='%s', accountNumber='%s', name='%s', balance=%.2f, phone='%s']",
            getAccountType(), ACCOUNT_NUMBER, accountHolderName, balance, phoneNumber);
    }

    /**
     * Returns a user friendly display string for the account.
     */
    public String toDisplayString() {
        return "\nAccount Number : " + getAccountNumber() + "\n" +
               "Customer Name    : " + getAccountHolderName() + "\n" +
               "Account Type     : " + getAccountType() + "\n" +
               "Balance          : $" + String.format("%.2f", getBalance()) + "\n" +
               "---------------------------------\n";
    }
}
