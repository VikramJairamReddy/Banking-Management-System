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

    /**
    * Returns the maximum amount that can be withdrawn from this account.
    *
    * @return maximum withdrawable amount
    */
    public abstract double possibleWithdraw();

    private final String ACCOUNT_NUMBER;
    private final Customer customer;
    private double balance = 0;

    /**
     * Creates a new account.
     *
     * @param accountNumber unique account number
     * @param customer the customer who owns this account
     */
    public Account(String accountNumber, Customer customer) {

        if(!Validate.isValidAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid Account Number");
        }
        if(customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        
        this.ACCOUNT_NUMBER = accountNumber;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public String getAccountHolderName() {
        return customer.getName();
    }

    public String getPhoneNumber() {
        return customer.getPhone();
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("[type='%s', accountNumber='%s', balance=%.2f, customer=%s]",
                getAccountType(), ACCOUNT_NUMBER, balance, customer);
    }

    public String toDisplayString() {
        return "\nAccount Number : " + getAccountNumber() + "\n" +
               "Account Type   : " + getAccountType() + "\n" +
               "Balance        : $" + String.format("%.2f", getBalance()) + "\n" +
               customer.toDisplayString() +
               "---------------------------------\n";
    }
}
