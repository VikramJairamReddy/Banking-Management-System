/**
 * Displays transaction history of allaccount.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;

public class TransactionHistoryFrame extends JFrame {

    private JTextArea history;
    private JButton backButton;

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

        // Adding title label to the frame
        add(title, BorderLayout.NORTH);

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
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(130, 35));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.add(backButton);

        // Adding button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
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
}