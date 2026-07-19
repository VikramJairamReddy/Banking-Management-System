/**
 * DashboardFrame represents the main dashboard window for the Banking Management System.
 *
 * Displays employee information, action buttons, and 
 * summary counts for total accounts and today's transactions.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class DashboardFrame extends JFrame {

    // Buttons for action buttons
    private JButton createAccountButton;
    private JButton manageButton;
    private JButton findAccountButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton transactionButton;
    private JButton logoutButton;

    // Labels for dashboard statistics
    private JLabel totalAccountsLabel;
    private JLabel totalAccountsCount;
    private JLabel totalTransactionsLabel;
    private JLabel totalTransactionsCount;

    /**
     * Constructor that builds and displays the dashboard frame.
     *
     * @param employeeName the name of the logged in employee
     */
    public DashboardFrame(String employeeName) {

        setTitle("Banking Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // Adding dashboard sections
        add(createTopPanel(employeeName), BorderLayout.NORTH);
        add(createActionPanel(), BorderLayout.WEST);
        add(createStatisticsPanel(), BorderLayout.CENTER);
        add(createAdminPanel(), BorderLayout.SOUTH);
    }


    /**
     * Creates the top panel containing the application title,
     * employee information, and logout button.
     *
     * @param employeeName logged-in employee name
     * @return top panel
     */
    private JPanel createTopPanel(String employeeName) {

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Main title label
        JLabel titleLabel = new JLabel("Banking Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Employee information panel
        JPanel employeePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JLabel employeeLabel = new JLabel("Employee:");
        employeeLabel.setFont(new Font("Arial", Font.ITALIC, 18));

        JLabel usernameLabel = new JLabel(employeeName);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        logoutButton = new JButton("Logout");

        //Add employee components into the employee panel
        employeePanel.add(employeeLabel);
        employeePanel.add(usernameLabel);
        employeePanel.add(logoutButton);

        // Adding title and employee panel to the top panel
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(employeePanel, BorderLayout.EAST);

        return topPanel;
    }

    /**
     * Creates the panel containing normal banking actions.
     *
     * @return action panel
     */
    private JPanel createActionPanel() {

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(6, 1, 10, 15));
        actionPanel.setBorder(new EmptyBorder(20, 40, 10, 20));

        createAccountButton = new JButton("Create Account");
        findAccountButton = new JButton("Find Account");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        transactionButton = new JButton("Transactions");

        buttonAppearance(createAccountButton, new Color(41, 112, 204));
        buttonAppearance(findAccountButton, new Color(41, 112, 204));
        buttonAppearance(depositButton, new Color(41, 112, 204));
        buttonAppearance(withdrawButton, new Color(41, 112, 204));
        buttonAppearance(transferButton, new Color(41, 112, 204));
        buttonAppearance(transactionButton, new Color(41, 112, 204));

        //Add buttons to the action panel 
        actionPanel.add(createAccountButton);
        actionPanel.add(findAccountButton);
        actionPanel.add(depositButton);
        actionPanel.add(withdrawButton);
        actionPanel.add(transferButton);
        actionPanel.add(transactionButton);

        return actionPanel;
    }

    /**
     * Creates the management panel.
     *
     * This panel contains account management actions.
     * This is placed separately from normal banking operations 
     * to prevent accidental clicks.
     *
     * @return admin panel
     */
    private JPanel createAdminPanel() {

        JPanel adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBorder(new EmptyBorder(10, 20, 15, 20));

        manageButton = new JButton("Manage Account");
        manageButton.setPreferredSize(new Dimension(160, 40));
        buttonAppearance(manageButton, new Color(12, 163, 159));

        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        removePanel.add(manageButton);

        adminPanel.add(removePanel, BorderLayout.EAST);

        return adminPanel;
    }

    /**
     * Creates the center panel containing dashboard statistics.
     *
     * @return statistics panel
     */
    private JPanel createStatisticsPanel() {

        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new GridLayout(5, 1, 20, 20));
        statisticsPanel.setBorder(new EmptyBorder(50, 150, 50, 50));

        // Total accounts label and value
        totalAccountsLabel = new JLabel("Total Accounts:");
        totalAccountsCount = new JLabel("0");
        totalAccountsLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        totalAccountsCount.setFont(new Font("Arial", Font.PLAIN, 18));

        // Total transactions label and value
        totalTransactionsLabel = new JLabel("Transactions Today:");
        totalTransactionsCount = new JLabel("0");

        totalTransactionsLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        totalTransactionsCount.setFont(new Font("Arial", Font.PLAIN, 18));

        statisticsPanel.add(totalAccountsLabel);
        statisticsPanel.add(totalAccountsCount);
        statisticsPanel.add(new JLabel());
        statisticsPanel.add(totalTransactionsLabel);
        statisticsPanel.add(totalTransactionsCount);

        return statisticsPanel;
    }

    /**
     * Sets the appearance of buttons used in the dashboard.
     *
     * @param button button whose appearance will be modified
     * @param color background color of the button
     */
    private void buttonAppearance(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 35));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // ---------------- GETTERS ----------------

    /**
     * Returns the create account button so that listener can be added.
     *
     * @return the create account button
     */
    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    /**
     * Returns the manage account button.
     * 
     * This button opens account management features and is restricted
     * using role-based permissions.
     *
     * @return manage account button
     */
    public JButton getManageButton() {
        return manageButton;
    }

    /**
     * Returns the find account button.
     *
     * @return the find account button
     */
    public JButton getFindAccountButton() {
        return findAccountButton;
    }

    /**
     * Returns the deposit button so that a listener can be added.
     *
     * @return the deposit button
     */
    public JButton getDepositButton() {
        return depositButton;
    }

    /**
     * Returns the withdraw button so that a listener can be added.
     *
     * @return the withdraw button
     */
    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    /**
     * Returns the transfer button so that a listener can be added.
     * 
     * @return the transfer button
     */
    public JButton getTransferButton() {
        return transferButton;
    }

    /**
     * Returns the transactions button so that a listener can be added.
     *
     * @return the transactions button
     */
    public JButton getTransactionButton() {
        return transactionButton;
    }

    /**
     * Returns the Logout button so that a listener can be added.
     *
     * @return the logout button
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }

    // SETTERS for displaying the correct number of accounts and transaction history

    /**
     * Updates the total number of accounts shown on the dashboard.
     *
     * @param total the total number of accounts
     */
    public void setTotalAccounts(int total) {
        totalAccountsCount.setText(String.valueOf(total));
    }

    /**
     * Updates the total number of transactions shown on the dashboard.
     *
     * @param total the total number of transactions
     */
    public void setTotalTransactions(int total) {
        totalTransactionsCount.setText(String.valueOf(total));
    }
}