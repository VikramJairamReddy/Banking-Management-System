/**
 * This class is for performing transactions such as Deposit, Withdraw, and Transfer.
 * It provides input fields for account number, recipient account (for transfers), and amount.
 * 
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;

public class TransactionFrame extends JFrame {

    private JTextField accountNumberField;
    private JTextField receiverAccountField;
    private JTextField amountField;

    private JLabel accountLabel;
    private JLabel receiverLabel;
    private JLabel amountLabel;

    private JButton transactionButton;
    private JButton cancelButton;

    private JPanel centerPanel;

    public TransactionFrame(String transactionType) {

        setTitle(transactionType);
        setSize(475, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // ---------- Title ----------
        JLabel title = new JLabel(transactionType, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        // Add the title label to the NORTH region of the frame
        add(title, BorderLayout.NORTH);

        // ---------- Center ----------
        int rows = transactionType.equals("Transfer")? 3 : 2;
        int bottomGap = transactionType.equals("Transfer")? 30 : 90;

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(rows, 2, 20, 10)); 
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, bottomGap, 20));

        accountLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField(20);

        centerPanel.add(accountLabel);
        centerPanel.add(accountNumberField);

        if(transactionType.equals("Transfer")) {
            receiverLabel = new JLabel("Recipient Account Number:");
            receiverAccountField = new JTextField(20);

            centerPanel.add(receiverLabel);
            centerPanel.add(receiverAccountField);
        }

        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);

        centerPanel.add(amountLabel);
        centerPanel.add(amountField);

        // Adding the center panel to the CENTER region of the frame
        add(centerPanel, BorderLayout.CENTER);

        // ---------- Buttons ----------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        transactionButton = new JButton(transactionType);
        buttonAppearance(transactionButton, new Color(41, 112, 204));

        cancelButton = new JButton("Cancel");
        buttonAppearance(cancelButton, Color.GRAY);

        buttonPanel.add(transactionButton);
        buttonPanel.add(cancelButton);

        // Adding the button panel to the SOUTH region of the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the appearance of a button.
     *
     * @param button The button to style.
     * @param color  The background color for the button.
     */
    private void buttonAppearance(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(130, 35));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // ---------- Getters ----------

    /**
     * Returns the account number entered by the user.
     *
     * @return The account number as a String.
     */
    public String getAccountNumber() {
        return accountNumberField.getText().trim();
    }

    /**
     * Returns the recipient account number entered by the user (for transfers).
     *
     * @return The recipient account number as a String.
     */
    public String getRecipientAccountNumber() {
        return receiverAccountField.getText().trim();
    }

    /**
     * Returns the amount entered by the user.
     *
     * @return The amount as a String.
     */
    public String getAmount() {
        return amountField.getText().trim();
    }

    /**
     * Returns the transaction button so that action listeners can be added to it.
     *
     * @return The JButton for executing the transaction.
     */
    public JButton getTransactionButton() {
        return transactionButton;
    }

    /**
     * Returns the cancel button so that action listeners can be added to it.
     *
     * @return The JButton for canceling the the search operation.
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
}