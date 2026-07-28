/**
 * CreateAccount frame allows the user to create a new bank account for a customer.
 *
 * It collects customer details such as:
 * - Name
 * - Phone number
 * - Account type (Savings or Checking)
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import java.awt.*;
import javax.swing.*;

public class CreateAccountFrame extends JFrame {
    
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> accTypeBox;

    private JButton createButton;
    private JButton cancelButton;

    /**
     * Constructs the CreateAccount window
     */
    public CreateAccountFrame() {
        setTitle("Create Account");
        setSize(400, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        setLayout(new BorderLayout());
        
        // -------- TITLE --------
        JLabel title = new JLabel("Create Customer Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(title, BorderLayout.NORTH);

        // -------- FORM PANEL --------
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            // -------- NAME PANEL --------
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Adding the name panel to the form Panel
        formPanel.add(namePanel);

            // -------- PHONE NUMBER PANEL --------
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(15);
        
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        // Adding the phone number panel to the form Panel
        formPanel.add(phonePanel);

            // -------- ACCOUNT TYPE PANEL --------
        JPanel accTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel accTypeLabel = new JLabel("Account Type:");
        accTypeBox = new JComboBox<>(new String[]{"Savings", "Checking"});

        accTypePanel.add(accTypeLabel);
        accTypePanel.add(accTypeBox);
    
        // Adding the combo box to the form Panel
        formPanel.add(accTypePanel);

        // Adding form Panel into the frame in the centre
        add(formPanel, BorderLayout.CENTER);

        // -------- BUTTONS PANEL --------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        createButton = new JButton("Create Account");
        createButton.setBackground(new Color(41, 112, 204));
        createButton.setForeground(Color.WHITE);
        
        createButton.setPreferredSize(new Dimension(160, 40));
        createButton.setFocusPainted(false);
        createButton.setBorderPainted(false);
        createButton.setOpaque(true);

        cancelButton = new JButton("Cancel"); 
        cancelButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.setFocusPainted(false);
        cancelButton.setBackground(Color.GRAY);
        
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        // Adding the buttons panel into the frame at the bottom
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // -------- GETTERS FOR CONTROLLER --------

    /**
     * Returns the name input field so that listener can be added.
     * 
     * @return the name field
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Returns the phone number input field so that listener can be added.
     * 
     * @return the phone number field
     */
    public JTextField getPhoneField() {
        return phoneField;
    }

    /**
     * Returns the account type combobox so that listeners can be added.
     * 
     * @return account type combobox
     */
    public JComboBox<String> getAccountTypeBox() {
        return accTypeBox;
    }

    /**
     * Returns the entered customer name.
     * 
     * @return entered customer name
     */
    public String getEnteredName() {
        return nameField.getText();
    }

    /**
     * Returns the entered customer phone number.
     * 
     * @return entered customer phone number
     */
    public String getEnteredPhoneNumber() {
        return phoneField.getText();
    }

    /**
     * Returns the type of customer account.
     * 
     * @return the selected type of account: Savings or Checkings
     */
    public String getAccountTypeSelected() {
        return (String) accTypeBox.getSelectedItem();
    }

    /**
     * Returns the create button so that listener can be added.
     * 
     * @return the create button
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Returns the cancel button so that listener can be added.
     * 
     * @return the cancel button
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
}
