/**
 * Represents a checking account.
 *
 * Supports overdraft protection up to a fixed limit and tracks overdraft usage.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class CheckingAccount extends Account {
        
    private  double overdraftLimit;

    public CheckingAccount(String accountNumber, Customer customer, double overdraftLimit) {
        super(accountNumber, customer);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Overrides the withdraw method to allow overdrafts up to a specific limit.
     * @param amount The amount to be withdrawn. 
     * Must be greater than 0 and less than or equal to the current balance plus the overdraft limit.
     **/
    @Override
    public boolean withdraw(double amount) {
        if(amount > 0 && amount <= getBalance() + overdraftLimit) {
            manageBalance(-amount);
            return true;
        }

        return false;
    }

    /**
     * Overrides the deposit method to manage the balance.
     * 
     * @param amount The amount to be deposited. Must be greater than 0.
     **/
    @Override
    public boolean deposit(double amount) {
        if(amount > 0) {
            manageBalance(amount);
            return true;
        }
        return false;
    }

    /**
     * Returns the maximum amount that can still be withdrawn,
     * taking the overdraft limit into count.
     *
     * @return available withdrawal amount
     */
    public double possibleWithdraw() {
        return getBalance() + overdraftLimit;
    }

    //Implements the getAccountType abstract method to return "Checking" as the account type.
    @Override
    public String getAccountType() {
        return "Checking";
    }

    /**
     * Returns the amount of overdraft currently being used.
     *
     * @return overdraft amount used
     */
    public double getOverdraftUsed() {
        return (getBalance() < 0)? Math.abs(getBalance()) : 0;
    }

    /**
     * Returns the overdraft limit for this account.
     *
     * @return overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
