/**
 * Represents a single banking transaction.
 *
 * Stores transaction details:
 * - Transaction ID
 * - Account number
 * - Transaction type
 * - Amount
 * - Time
 *
 * @author Ganta Vikram Jairam Reddy
 */
package Model;

import java.time.LocalDateTime;

public class Transaction {
    
    private final long transactionId;
    private final String accountNumber;
    private final String transactionType;
    private final double amount;
    private final LocalDateTime time;

    /**
     * Creates a new transaction and records the current date and time.
     *
     * @param transactionId unique transaction ID
     * @param accountNumber associated account number
     * @param transactionType type of transaction
     * @param amount transaction amount
     */
    public Transaction(long transactionId, String accountNumber,
                        String transactionType, double amount) {

        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    //Getters for transaction properties
    public long getTransactionId() {
        return transactionId;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public double getAmount() {
        return amount;
    }

    /**
     * Returns a formatted string representation
     * of the transaction.
     */
    @Override
    public String toString() {
        return String.format("[%d] %s | %s | $%.2f | %s",
                    transactionId, transactionType, accountNumber, amount, time);
    }
}
