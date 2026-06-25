/**
 * Connects the dashboard view with the bank model by handling
 * action buttons and updating the dashboard statistics.
 *
 * Responsibilities:
 * - Handles events of dashboard button.
 * - Updates account and transaction statistics.
 * - Opens the login window after logout.
 * 
 * @author Ganta Vikram Jairam Reddy
 */ 

package Controller;

import Model.Bank;
import View.DashboardFrame;

import javax.swing.*;

public class DashboardController {

    private final DashboardFrame dashboard;
    private final BankController bankController;
    private final Bank bank;

    /**
     * Creates dashboard controller
     *
     * @param employeeName logged-in employee name
     * @param bank shared Bank object
     */
    public DashboardController(String employeeName, Bank bank) {

        this.bank = bank;
        this.dashboard = new DashboardFrame(employeeName);
        this.bankController = new BankController(bank);

        update();

        // adding listeners to the action buttons
        dashboard.getCreateAccountButton().addActionListener(e -> createAccount());
        dashboard.getFindAccountButton().addActionListener(e -> findAccount());
        dashboard.getDepositButton().addActionListener(e -> deposit());
        dashboard.getWithdrawButton().addActionListener(e -> withdraw());
        dashboard.getTransferButton().addActionListener(e -> transfer());
        dashboard.getTransactionButton().addActionListener(e -> transactions());
        dashboard.getLogoutButton().addActionListener(e -> logout());

        dashboard.setVisible(true);
    }

    /**
     * Opens the Create Account screen for creating a new bank account for the customer.
     *
     * This method launches the CreateAccountController, which handles
     * user input, account creation logic, and updates the bank model.
     */
    private void createAccount() {
        new CreateAccountController(bankController, dashboard);
        update();
    }

    private void findAccount() {
        JOptionPane.showMessageDialog(dashboard, "Find Account Clicked");
    }

    private void deposit() {
        JOptionPane.showMessageDialog(dashboard, "Deposit clicked");
        update();
    }

    private void withdraw() {
        JOptionPane.showMessageDialog(dashboard, "Withdraw clicked");
        update();
    }

    private void transfer() {
        JOptionPane.showMessageDialog(dashboard, "Transfer clicked");
        update();
    }

    private void transactions() {
        JOptionPane.showMessageDialog(dashboard, "Transaction history clicked");
    }

    /**
     * Logs out of the current employee and returns to the login window.
     */
    private void logout() {
        dashboard.dispose();
        new LoginController();
    }

    /**
     * Updates the dashboard statistics.
     *
     * Refreshes the total number of accounts and today's transaction count 
     * which are displayed on the dashboard.
     */
    private void update() {
        dashboard.setTotalAccounts(bank.getNumberOfAccounts());
        dashboard.setTotalTransactions(bank.getTodayTransactionCount());
    }
}