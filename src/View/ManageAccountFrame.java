/**
 * Displays account management options.
 *
 * Features:
 * - Search account
 * - Display account details
 * - Remove account
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ManageAccountFrame extends JFrame {

    private JTextField accountNumberField;

    private JLabel accountNumberLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel balanceLabel;
    private JLabel phoneLabel;

    private JButton searchButton;
    private JButton removeButton;
    private JButton backButton;


    public ManageAccountFrame() {

        setTitle("Manage Account");
        setSize(500,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSearchPanel(), BorderLayout.NORTH);
        add(createDetailsPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }


    /**
     * Creates search section.
     */
    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(15,15,10,15));

        accountNumberField = new JTextField(15);

        searchButton = new JButton("Search");
        buttonAppearance(searchButton, new Color(41,112,204));

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumberField);
        panel.add(searchButton);


        return panel;
    }

    /**
     * Creates account details section.
     */
    private JPanel createDetailsPanel() {

        JPanel panel = new JPanel(new GridLayout(5,2,5,10));
        panel.setBorder(new EmptyBorder(20,60,20,60));

        panel.add(new JLabel("Account Number:"));
        accountNumberLabel = new JLabel("-");
        panel.add(accountNumberLabel);

        panel.add(new JLabel("Name:"));
        nameLabel = new JLabel("-");
        panel.add(nameLabel);

        panel.add(new JLabel("Account Type:"));
        typeLabel = new JLabel("-");
        panel.add(typeLabel);

        panel.add(new JLabel("Balance:"));
        balanceLabel = new JLabel("-");
        panel.add(balanceLabel);

        panel.add(new JLabel("Phone:"));
        phoneLabel = new JLabel("-");
        panel.add(phoneLabel);

        return panel;
    }


    /**
     * Creates bottom action buttons.
     */
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 10));

        removeButton = new JButton("Remove Account");
        backButton = new JButton("Back");

        buttonAppearance(removeButton, new Color(55, 65, 81));
        buttonAppearance(backButton, Color.DARK_GRAY);

        removeButton.setEnabled(false);

        panel.add(removeButton);
        panel.add(backButton);

        return panel;
    }

    /**
     * Sets the appearance of buttons.
     *
     * @param button button whose appearance will be modified
     * @param color background color
     */
    private void buttonAppearance(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setFocusPainted(false);

        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBorderPainted(false);
    }

    // ---------------- GETTERS ----------------

    public JTextField getAccountNumberField() {
        return accountNumberField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getBackButton() {
        return backButton;
    }


    // ---------------- DISPLAY METHODS ----------------

    /**
     * Displays account information.
     */
    public void displayAccount(String accountNumber, String name, String type, String balance,
                                    String phone) {

        accountNumberLabel.setText(accountNumber);
        nameLabel.setText(name);
        typeLabel.setText(type);
        balanceLabel.setText(balance);
        phoneLabel.setText(phone);

        removeButton.setEnabled(true);
        removeButton.setBackground(new Color(204, 60, 60));
    }


    /**
     * Clears displayed account information.
     */
    public void clearDetails() {

        accountNumberLabel.setText("-");
        nameLabel.setText("-");
        typeLabel.setText("-");
        balanceLabel.setText("-");
        phoneLabel.setText("-");
        accountNumberField.setText("");

        removeButton.setEnabled(false);
        removeButton.setBackground(new Color(55, 65, 81));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);

    }

    /**
     * Confirmation Dialog before actually deleteing an account
     */
    public boolean confirmRemove() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove this account?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }
}