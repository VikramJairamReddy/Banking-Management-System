/**
 * Controls the Transaction History.
 *
 * Responsibilities:
 * - Gets all transactions from the bank.
 * - Searches transactions based on user-selected options: Account number, transaction id, and date
 * - Formats transaction data for display.
 * - Applies transaction type filters and sorting options.
 * - Displays the transaction history in the view.
 * - Returns to the dashboard when the Back button is pressed.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import View.TransactionHistoryFrame;
import Model.Transaction;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

public class TransactionHistoryController {
    
    private final TransactionHistoryFrame historyFrame;
    private final DashboardController dashboard;
    private final BankController bankController;

    // Default values
    private String selectedTransactionType = "All";
    private String selectedSort = "Newest First";

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

        addFilterListener();
        historyFrame.getSearchTypeBox().addActionListener(e -> searchTransaction());
        historyFrame.getBackButton().addActionListener(e -> closeWindow());

        addSearchListener();
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
    private void displayRecord(List<Transaction> transactions) {

        historyFrame.clearTable(); // clearing all rows

        if(transactions == null || transactions.isEmpty()) {
            historyFrame.updateResultLabel(0);
            return;
        }

        for(Transaction transaction : transactions) {
            historyFrame.addTransaction(new Object[] {
                    transaction.getTransactionId(),
                    transaction.getTransactionType(),
                    transaction.getAccountNumber(),
                    transaction.getSecondAccountNumber(),
                    transaction.getAmount(),
                    transaction.getTime() 
                });
        }

        historyFrame.updateResultLabel(transactions.size());
    }

    /**
     * Searches transactions based on the selected search type.
     * Applies active filters and sorting before displaying results.
     */
    private void searchTransaction() {
        try {
            String searchTerm = historyFrame.getSearchField().getText().trim();
        
            List<Transaction> transactions;
        
            if(searchTerm.isEmpty()){
                transactions = bankController.getAllTransactions();
            }
            else {
                transactions = bankController.searchTransactions(searchTerm);
            }
        
            transactions = applyFilters(transactions);
        
            displayRecord(transactions);
        }
        catch(Exception e) {
                JOptionPane.showMessageDialog(historyFrame, e.getMessage(), "Search Error",
                                        JOptionPane.ERROR_MESSAGE);
        }
    
    }

    /**
     * Displays all transactions in the frame.
     *
     * @param transactions list of transactions
     */
    private void loadAllTransactions() {
        List<Transaction> transactions = bankController.getAllTransactions();
        displayRecord(applyFilters(transactions));
    }

    /**
     * Adds a DocumentListener to the search field for
     * live searching whenever the user types or removes text.
     */
    private void addSearchListener() {
        historyFrame.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
    
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTransaction();
            }
    
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTransaction();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    /**
     * Adds an action listener to the filter button.
     * Opens the filter dialog, stores the selected options, and refreshes the transaction table.
     */
    private void addFilterListener() {
        historyFrame.getFilterButton().addActionListener(e -> {

            if(historyFrame.showFilter()) {
                selectedTransactionType = historyFrame.getSelectedTransactionType();
                selectedSort = historyFrame.getSelectedSortOption();
                searchTransaction();
            }
        });
    }

    /**
     * Applies transaction type filtering and sorting options.
     *
     * @param transactions list of transactions to filter and sort
     * @return filtered and sorted transaction list
     */
    private List<Transaction> applyFilters(List<Transaction> transactions) {
        if(transactions == null || transactions.isEmpty()) {
            return transactions;
        }

        // Create a new list so the original transaction list is not modified
        List<Transaction> filteredTransactions = new ArrayList<>(transactions);

        // ---------- Transaction Type Filter ----------

        if(!selectedTransactionType.equals("All")) {
            filteredTransactions.removeIf(
                    transaction -> !transaction.getTransactionType().equals(selectedTransactionType));
        }

        // ---------- Sorting ----------

        switch(selectedSort) {

            // Sort transactions by time in descending order (newest to oldest)
            case "Newest First":
                filteredTransactions.sort(Comparator.comparing(
                                (Transaction transaction) -> transaction.getTime()).reversed());
                break;
            
            // Sort transactions by time in ascending order (oldest to newest)
            case "Oldest First":
                filteredTransactions.sort(Comparator.comparing(
                                (Transaction transaction) -> transaction.getTime()));
                break;

            default:
                break;
        }

        return filteredTransactions;
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
