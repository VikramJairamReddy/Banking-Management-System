/**
 * LoginFrame creates the login window for the Banking Management System.
 * It contains username and password fields, login button and its layout
 * 
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Background colour (dark blue) for main panel
    private static final Color BACKGROUND = new Color(3, 74, 133);

    /**
     * Constructor that builds and displays the login frame.
     */
    public LoginFrame() {

        setTitle("Banking Management System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel with grid bag to centre the login panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND);

        // white Login panel 
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(400, 320));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Displaying Bank Icon on the top
        JLabel bankIcon = new JLabel("🏦", SwingConstants.CENTER);
        bankIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        bankIcon.setFont(new Font("Arial", Font.PLAIN, 45));

        // Main title label
        JLabel titleLabel = new JLabel("Banking Management System");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Subtitle label
        JLabel subtitleLabel = new JLabel("Employee Login");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);

        // Adding components into login panel
        loginPanel.add(bankIcon);
        loginPanel.add(Box.createVerticalStrut(5));// for empty space of 5 pixels
        loginPanel.add(titleLabel);
        loginPanel.add(subtitleLabel);
        loginPanel.add(Box.createVerticalStrut(25));// for empty space of 25 pixels

        // Form panel for username and password fields
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBackground(Color.WHITE);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 13));

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
  
        // Username input field
        usernameField = new JTextField();
        usernameField.setColumns(20);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        // Password input field
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        // Add labels and fields to the form panel
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Adding Form panel to Login panel
        loginPanel.add(formPanel);
        loginPanel.add(Box.createVerticalStrut(25));

        // Login button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(140, 40));
        loginButton.setMaximumSize(new Dimension(140, 40));
        loginButton.setBackground(new Color(41, 112, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginPanel.add(loginButton); // Adding Login button to Login panel
        mainPanel.add(loginPanel); // Adding Login panel to Main panel

        //Add main panel to frame
        add(mainPanel);

        // Making "Enter" key the default button for login button
        getRootPane().setDefaultButton(loginButton);
    }

    // getters for user entered text
    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void resetPasswordField() {
        passwordField.setText("");
        repaint();
    }
    

    /**
     * Returns the login button so that a listener can be added
     *
     * @return the login button
     */
    public JButton getLoginButton() {
        return loginButton;
    }
}