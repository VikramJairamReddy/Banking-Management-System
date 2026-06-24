/**
 * Represents a savings account.
 *
 * Stores interest rate and provides a method to apply interest to the current balance.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, String phoneNumber, double interestRate) {
        super(accountNumber, accountHolderName, phoneNumber);
        this.interestRate = interestRate;
    }

    /**
     * Calculates and deposits interest based on the current account balance and interest rate.
     */
    public void addInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
    }

    /**
     * Returns the account's interest rate.
     *
     * @return interest rate percentage
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Updates the interest rate for the account.
     *
     * @param interestRate new interest rate percentage
     */
    public void setInterestRate(double interestRate) {
        if(interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.interestRate = interestRate;
    }

    //Implements the getAccountType abstract method to return "Savings" as the account type.
    @Override
    public String getAccountType() {
        return "Savings";
    }
}
