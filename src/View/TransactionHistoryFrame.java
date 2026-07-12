/**
 * Displays transaction history of a specific account.
 *
 * Allows users to search transactions by account number
 * and view the transaction history.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;

public class TransactionHistoryFrame extends JFrame {

    private JTextArea history;
    private JTextField accountNumberField;

    private JButton backButton;
    private JButton searchButton;

    public TransactionHistoryFrame() {

        setTitle("Transactions");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // ---------- Title ----------

        JLabel title = new JLabel("Transaction History" ,SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // ---------- Search Panel ----------

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel accountLabel = new JLabel("Account Number:");

        accountNumberField = new JTextField(20);
        searchButton = new JButton("Search");
        buttonAppearance(searchButton, new Color(41, 112, 204));


        searchPanel.add(accountLabel);
        searchPanel.add(accountNumberField);
        searchPanel.add(searchButton);

        // ---------- Top Panel (Title + Search) ----------

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Adding title and search section
        add(topPanel, BorderLayout.NORTH);

        // ---------- Centre - Text Area ----------
        history = new JTextArea();
        history.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        history.setEditable(false);
        history.setFont(new Font("Monospaced", Font.PLAIN, 14));

        //Enable line wrapping so that the text flows to the next line if it
        // exceeds the width of the text area
        history.setLineWrap(true);

        // Ensure that words are not split in the middle when wrapping
        history.setWrapStyleWord(true);

        // Adding new text area with scroll bar to the frame
        add(new JScrollPane(history), BorderLayout.CENTER);

        // ---------- Buttons ----------
        
        backButton = new JButton("Back");
        buttonAppearance(backButton, Color.GRAY);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.add(backButton);

        // Adding button panel to the frame
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

    /**
     * Returns the Back button so that action listeners can be added to it.
     *
     * @return The JButton for returning to the dashboard.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Sets the transaction history text which is displayed in the text area.
     *
     * @param text formatted transaction history to display
     */
    public void setHistoryText(String text) {
        history.setText(text);
    }

    /**
     * Returns the search button so that action listeners can be added by the controller.
     *
     * @return The search button for returning to the dashboard.
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Returns the entered account number.
     *
     * @return account number entered by the user
     */
    public String getAccountNumber() {
        return accountNumberField.getText().trim();
    }
}