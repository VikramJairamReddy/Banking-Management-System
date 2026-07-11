/**
 * Controls the Transaction History.
 *
 * Responsibilities:
 * - Gets all transactions from the bank.
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

public class TransactionHistoryController {
    
    private TransactionHistoryFrame historyFrame;
    private DashboardController dashboard;
    private BankController bankController;

    public TransactionHistoryController(BankController bankController, DashboardController dashboard) {
        this.dashboard = dashboard;
        this.bankController = bankController;

        historyFrame = new  TransactionHistoryFrame();

        dashboard.showDashboard(false);

        historyFrame.setHistoryText(generateText());
        historyFrame.getBackButton().addActionListener(e -> closeWindow());

        historyFrame.setVisible(true);
    }

    /**
     * generates a formatted string containing all transactions details.
     *
     * @return formatted transaction history, or a message if no
     *         transactions are available
     */
    private String generateText() {
        List<Transaction> transactions = bankController.getAllTransactions();
        if(transactions.isEmpty()) {
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
     * closes the transaction history window and
     * returns to the dashboard.
     */
    private void closeWindow() {
        historyFrame.dispose();
        dashboard.showDashboard(true);
    }
}
