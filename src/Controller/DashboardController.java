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

        update();

        // adding listeners to the action buttons
        dashboard.getCreateAccountButton().addActionListener(e -> createAccount());
        dashboard.getFindAccountButton().addActionListener(e -> findAccount());
        dashboard.getDepositButton().addActionListener(e -> deposit());
        dashboard.getWithdrawButton().addActionListener(e -> withdraw());
        dashboard.getTransferButton().addActionListener(e -> transfer());
        dashboard.getTransactionButton().addActionListener(e -> transactions());
        dashboard.getManageButton().addActionListener(e -> manageAccount());
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
        new CreateAccountController(bankController, this);
    }

    private void findAccount() {
        new FindAccountController(bankController, this);
    }

    private void deposit() {
        new TransactionController(DEPOSIT, bankController, this);
    }

    private void withdraw() {
       new TransactionController(WITHDRAW, bankController, this);
    }

    private void transfer() {
        new TransactionController(TRANSFER, bankController, this);
    }

    private void transactions() {
        new TransactionHistoryController(bankController, this);
    }

    private void manageAccount() {
        new ManageAccountController(bankController, this);
    }

    /**
     * Logs out of the current employee and returns to the login window.
     */
    private void logout() {
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
}