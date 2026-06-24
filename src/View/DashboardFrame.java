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

        // -------- TOP PANEL --------

        // Top panel that displays the title and employee information
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Main title label
        JLabel titleLabel = new JLabel("Banking Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // small panel to hold employee name and logout button
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.X_AXIS));

        JLabel employeeLabel = new JLabel("Employee: ");
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

        // Add top panel to the frame
        add(topPanel, BorderLayout.NORTH);

        // -------- LEFT PANEL --------

        //Left panel that contains action buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 20));
        leftPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        createAccountButton = new JButton("Create Account");
        findAccountButton = new JButton("Find Account");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        transactionButton = new JButton("Transactions");

        //Add buttons to the left panel
        leftPanel.add(createAccountButton);
        leftPanel.add(findAccountButton);
        leftPanel.add(depositButton);
        leftPanel.add(withdrawButton);
        leftPanel.add(transferButton);
        leftPanel.add(transactionButton);

        // Add left panel to the frame
        add(leftPanel, BorderLayout.WEST);

        // -------- CENTER PANEL --------

        // Center panel that shows dashboard statistics
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new GridLayout(5, 1, 40, 20));
        centrePanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        // Total number of accounts label and value
        totalAccountsLabel = new JLabel("Total Accounts: ");
        totalAccountsCount = new JLabel("0");
        totalAccountsLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        totalAccountsCount.setFont(new Font("Arial", Font.PLAIN, 16));

        //Total transactions happened today, label and value
        totalTransactionsLabel = new JLabel("Transactions Today:");
        totalTransactionsCount = new JLabel("0");
        totalTransactionsLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        totalTransactionsCount.setFont(new Font("Arial", Font.PLAIN, 16));

        //Adding components to the center panel
        centrePanel.add(totalAccountsLabel);
        centrePanel.add(totalAccountsCount);
        centrePanel.add(new JLabel());
        centrePanel.add(totalTransactionsLabel);
        centrePanel.add(totalTransactionsCount);

        // Add center panel to the frame
        add(centrePanel, BorderLayout.CENTER);
    }

    // GETTERS of the Action Buttons so that the listener can be added

    /**
     * Returns the create account button so that listener can be added.
     *
     * @return the create account button
     */
    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    /**
     * Returns the find account button so that a listener can be added.
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

    // SETTERS for displaying the correct number of accounts anf transaction history

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