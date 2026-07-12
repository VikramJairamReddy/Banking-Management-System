/**
 * Controls the Transaction History.
 *
 * Responsibilities:
 * - Gets all transactions from the bank.
 * - Searches transactions by account number.
 * - Formats transaction data for display.
 * - Displays the transaction history in the view.
 * - Returns to the dashboard when the Back button is pressed.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import View.TransactionHistoryFrame;
import Model.Transaction;

import java.util.List;

import javax.swing.JOptionPane;

public class TransactionHistoryController {
    
    private final TransactionHistoryFrame historyFrame;
    private final DashboardController dashboard;
    private final BankController bankController;

    /**
     * Creates the transaction history controller.
     *
     * @param bankController handles bank operations
     * @param dashboard returns to dashboard after closing
     */
    public TransactionHistoryController(BankController bankController, DashboardController dashboard) {
        this.dashboard = dashboard;
        this.bankController = bankController;

        historyFrame = new  TransactionHistoryFrame();

        dashboard.showDashboard(false);

        historyFrame.getSearchButton().addActionListener(e -> searchTransaction());
        historyFrame.getBackButton().addActionListener(e -> closeWindow());

        loadAllTransactions();

        historyFrame.setVisible(true);
    }

    /**
     * generates a formatted string containing the transactions details.
     *
     * @param transactions transactions list of transactions to format
     * @return formatted transaction history, or a message if no
     *         transactions are available
     */
    private String generateText(List<Transaction> transactions) {
        
        if(transactions == null || transactions.isEmpty()) {
            return "No transactions available.";
        }

        StringBuilder sb = new StringBuilder();

        for(Transaction transaction : transactions) {
            sb.append("Transaction ID : ").append(transaction.getTransactionId());

            if("Transfer".equals(transaction.getTransactionType())) {
                sb.append("\nSender Account   : ").append(transaction.getAccountNumber())
                .append("\nReceiver Account : ").append(transaction.getSecondAccountNumber());
            } 
            else {
                sb.append("\nAccount Number   : ").append(transaction.getAccountNumber());
            }

            sb.append("\nType             : ").append(transaction.getTransactionType())
            .append("\nAmount           : $").append(transaction.getAmount())
            .append("\nDate             : ").append(transaction.getTime())
            .append("\n----------------------------------------\n\n");
        }

        return sb.toString();
    }

    /**
     * Searches transactions using the entered account number and displays the result.
     */
    private void searchTransaction() {
        String accountNumber = historyFrame.getAccountNumber();

        if(accountNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(historyFrame, "Enter account number");
            return;
        }

        try {
            List<Transaction> transactions = bankController.getTransactionHistory(accountNumber);
            historyFrame.setHistoryText(generateText(transactions));
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog( historyFrame, e.getMessage(),
                        "Search Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all transactions in the frame.
     *
     * @param transactions list of transactions
     */
    private void loadAllTransactions() {
        List<Transaction> transactions = bankController.getAllTransactions();
        historyFrame.setHistoryText(generateText(transactions));
    }

    /**
     * closes the transaction history window and
     * returns to the dashboard.
     */
    private void closeWindow() {
        historyFrame.dispose();
        dashboard.showDashboard(true);
    }
}
