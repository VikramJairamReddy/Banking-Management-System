/**
 * Connects the dashboard view with the bank model by handling
 * action buttons and updating the dashboard statistics.
 *
 * Responsibilities:
 * - Handles events of dashboard buttons.
 * - Updates account and transaction statistics.
 * - Opens the login window after logout.
 * 
 * @author Ganta Vikram Jairam Reddy
 */ 

package Controller;

import javax.swing.JOptionPane;

import Model.CurrentUser;
import View.DashboardFrame;
import View.LoginFrame;

public class DashboardController {

    private static final String DEPOSIT = "Deposit";
    private static final String WITHDRAW = "Withdraw";
    private static final String TRANSFER = "Transfer";

    private final DashboardFrame dashboard;
    private final BankController bankController;

    /**
     * Creates dashboard controller
     *
     * @param employeeName logged-in employee name
     * @param bank shared Bank object
     */
    public DashboardController(String employeeName, BankController bankController) {

        this.dashboard = new DashboardFrame(employeeName);
        this.bankController = bankController;

        applyPermissions();
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
        if(!PermissionManager.canCreateAccount()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }

        new CreateAccountController(bankController, this);
    }

    /**
     * Opens the Find Account window for searching an account
     * by account number or account holder name.
     *
     * This method launches the FindAccountController, which handles
     * account search logic and displays account details.
     */
    private void findAccount() {
        if(!PermissionManager.canViewAccounts()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }

        new FindAccountController(bankController, this);
    }

    /**
     * Opens the transaction window for depositing money.
     * 
     * This method launches the TransactionController, which handles
     * user input, exceptional handling and updates the bank model.
     */
    private void deposit() {
        if(!PermissionManager.canDeposit()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }
        new TransactionController(DEPOSIT, bankController, this);
    }

    /**
     * Opens the transaction window for withdrawing money.
     * 
     * This method launches the TransactionController, which handles
     * user input, exceptional handling and updates the bank model.
     */
    private void withdraw() {
        if(!PermissionManager.canWithdraw()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }
       new TransactionController(WITHDRAW, bankController, this);
    }

    /**
     * Opens the transaction window for transferring money.
     * 
     * This method launches the TransactionController, which handles
     * user input, exceptional handling and updates the bank model.
     */
    private void transfer() {
        if(!PermissionManager.canTransfer()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }
        new TransactionController(TRANSFER, bankController, this);
    }

    /**
     * Opens the transaction history window for displaying all transactions.
     * 
     * This method launches the TransactionHistoryController, which handles
     * displaying all the data
     */
    private void transactions() {
        if(!PermissionManager.canViewTransactions()) {
            JOptionPane.showMessageDialog(dashboard,"Access denied");
            return;
        }
        new TransactionHistoryController(bankController, this);
    }

    /**
     * Logs out of the current employee and returns to the login window.
     */
    private void logout() {
        CurrentUser.logout();
        dashboard.dispose();
        LoginFrame loginFrame = new LoginFrame();
        new LoginController(loginFrame, bankController);
    }

    /**
     * Updates the dashboard statistics.
     *
     * Refreshes the total number of accounts and today's transaction count 
     * which are displayed on the dashboard.
     */
    private void update() {
        dashboard.setTotalAccounts(bankController.getNumberOfAccounts());
        dashboard.setTotalTransactions(bankController.getTodayTransactionCount());
    }

    /**
     * Refreshes the dashboard to reflect any changes in the bank data.
     */
    public void refreshDashboard() {
        update();
    }

    /**
     * Shows or hides the dashboard window.
     *
     * @param value true to show, false to hide
     */
    public void showDashboard(boolean value) {
        dashboard.setVisible(value);
    }

    /**
     * Controls dashboard features based on the user's role.
     *
     * Buttons are visible or hidden depending on the permissions of the logged-in user.
     */
    private void applyPermissions() {

        dashboard.getCreateAccountButton().setEnabled(PermissionManager.canCreateAccount());
        dashboard.getFindAccountButton().setEnabled(PermissionManager.canViewAccounts());
        dashboard.getDepositButton().setEnabled(PermissionManager.canDeposit());
        dashboard.getWithdrawButton().setEnabled(PermissionManager.canWithdraw());
        dashboard.getTransferButton().setEnabled(PermissionManager.canTransfer());
        dashboard.getTransactionButton().setEnabled(PermissionManager.canViewTransactions());
    }
}