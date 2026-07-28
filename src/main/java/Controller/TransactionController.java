/**
 * Controller for Deposit, Withdraw and Transfer operations.
 *
 * Responsibilities:
 * - Reads user input from TransactionFrame.
 * - Validates entered data.
 * - Calls BankController transaction methods
 * - Updates the dashboard after successful transactions.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import View.TransactionFrame;

import javax.swing.*;

public class TransactionController {

    private static final String DEPOSIT = "Deposit";
    private static final String WITHDRAW = "Withdraw";
    private static final String TRANSFER = "Transfer";

    private final TransactionFrame transactionFrame;
    private final BankController bankController;
    private final DashboardController dashboardController;
    private final String transactionType;

    public TransactionController(String transactionType, BankController bankController,
                                 DashboardController dashboardController) {

        this.transactionType = transactionType;
        this.bankController = bankController;
        this.dashboardController = dashboardController;

        transactionFrame = new TransactionFrame(transactionType);

        dashboardController.showDashboard(false);

        // Adding action listeners to the buttons
        transactionFrame.getTransactionButton().addActionListener(e -> transaction());
        transactionFrame.getCancelButton().addActionListener(e -> closeWindow());

        transactionFrame.setVisible(true);
    }

    /**
     * This is a method that handles the transaction based on the 
     * selected transaction type (Deposit, Withdraw, or Transfer).
     */
    private void transaction() {
        String accountNumber = transactionFrame.getAccountNumber();
    
        if(accountNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(transactionFrame,
                    "Please enter a valid account number.");
            return;
        }

        double amount;
    
        try {
            amount = Double.parseDouble(transactionFrame.getAmount());
        }
        catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(transactionFrame,"Please enter a valid amount.");
            return;
        }
    
        try {
            boolean isSuccess = false;
    
            switch(transactionType) {
                case DEPOSIT:
                    isSuccess = bankController.deposit(accountNumber, amount);
                    break;
    
                case WITHDRAW:
                    isSuccess = bankController.withdraw(accountNumber, amount);
                    break;
    
                case TRANSFER:
                    String receiverAccount = transactionFrame.getRecipientAccountNumber();
    
                    if(receiverAccount.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(transactionFrame,
                                "Please enter the recipient account number.");
                        return;
                    }
    
                    isSuccess = bankController.transfer(accountNumber, receiverAccount, amount);
                    break;
            }
    
    
            if(isSuccess) {
                JOptionPane.showMessageDialog(transactionFrame, transactionType + " successful.");
                dashboardController.refreshDashboard();

                closeWindow();
            }
        } 
        catch(Exception e) {
            JOptionPane.showMessageDialog(transactionFrame, e.getMessage(),"Error",
                                            JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Closes the transaction frane and returns to the dashboard.
     */
    private void closeWindow() {
        transactionFrame.dispose();
        dashboardController.showDashboard(true);
    }
}