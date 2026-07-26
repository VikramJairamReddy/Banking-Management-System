/**
 * Displays user management options.
 *
 * Features:
 * - Manage customer accounts
 * - Manage employees
 * - Display current user information
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserManagementFrame extends JFrame {

    private JButton manageAccountButton;
    private JButton employeeManagementButton;
    private JButton createEmployeeButton;
    private JButton backButton;

    /**
     * Creates the User Management window.
     *
     * @param username logged-in username
     * @param role current user role
     */
    public UserManagementFrame(String username, String role) {

        setTitle("User Management");
        setSize(470, 490);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createTitlePanel(username, role), BorderLayout.NORTH);
        add(createButtonsPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }


    /**
     * Creates the title section which contains frame title and current user information.
     *
     * @param username logged-in username
     * @param role current user role
     * @return title panel
     */
    private JPanel createTitlePanel(String username, String role) {

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("User Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel userInfoPanel = new JPanel(new GridLayout(2,1,5,5));
        userInfoPanel.setBorder(new EmptyBorder(20, 10, 5, 15));

        JLabel usernameLabel = new JLabel("Logged in as: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel roleLabel = new JLabel("Role: " + role);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Adding User name and role to the userInfoPanel
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(roleLabel);

        // Adding the title and the userInfoPanel to the titlePanle
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(userInfoPanel, BorderLayout.CENTER);

        return titlePanel;
    }


    /**
     * Creates the main management buttons.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2,1,20,20));
        buttonsPanel.setBorder(new EmptyBorder(40,50,80,50));

        manageAccountButton = new JButton("Account Management");
        buttonAppearance(manageAccountButton, new Color(41,112,204));

        employeeManagementButton = new JButton("Employee Management");
        buttonAppearance(employeeManagementButton, new Color(12,163,159));

        buttonsPanel.add(manageAccountButton);
        buttonsPanel.add(employeeManagementButton);

        return buttonsPanel;
    }


    /**
     * Creates bottom panel containing back button.
     *
     * @return bottom panel
     */
    private JPanel createBottomPanel() {

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(15,10,15,15));

        createEmployeeButton = new JButton("Add Employee");
        buttonAppearance(createEmployeeButton, new Color(34, 139, 34));

        backButton = new JButton("Back");
        buttonAppearance(backButton, Color.DARK_GRAY);
        backButton.setSize(new Dimension(80, 40));

        bottomPanel.add(createEmployeeButton);
        bottomPanel.add(backButton);

        return bottomPanel;
    }


    /**
     * Sets button appearance.
     *
     * @param button button to modify
     * @param color button color
     */
    private void buttonAppearance(JButton button, Color color) {

        button.setPreferredSize(new Dimension(150,35));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD,14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // ---------------- GETTERS ----------------

    /**
     * Returns account management button.
     *
     * @return account management button
     */
    public JButton getManageAccountButton() {
        return manageAccountButton;
    }

    /**
     * Returns employee management button.
     *
     * @return employee management button
     */
    public JButton getEmployeeManagementButton() {
        return employeeManagementButton;
    }

    /**
     * Returns back button.
     *
     * @return back button
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Returns create employee account button.
     *
     * @return create account button
     */
    public JButton getCreateAccountButton() {
        return createEmployeeButton;
    }
}