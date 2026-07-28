/**
 * This class represents the frame that displays the results of a search 
 * for an account based on the account number or name.
 * It contains a text area to show the results and a back button to return to the previous screen.
 * 
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;

public class AccountResultFrame extends JFrame {

    private JTextArea resultArea;
    private JButton backButton;

    public AccountResultFrame(String searchValue) {

        setTitle("Account Details");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // -------- TITLE --------
        JLabel title = new JLabel("Results For  \"" + searchValue + "\"", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(title, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setText("No account found for: " + searchValue);
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        //Enable line wrapping so that the text flows to the next line if it
        // exceeds the width of the text area
        resultArea.setLineWrap(true);

        // Ensure that words are not split in the middle when wrapping
        resultArea.setWrapStyleWord(true);

        // Adding the result area to a scroll pane and then add it to the center of the frame
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // -------- BOTTOM PANEL --------
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        bottom.add(backButton);
        // Add the bottom panel to the frame
        add(bottom, BorderLayout.SOUTH);
    }

    /**
     * Sets the result text in the text area.
     *
     * @param text the result text to display
     */
    public void setResult(String text) {
        resultArea.setText(text);
    }

    /**
     * Returns the back button so that the controller can add an action listener to it.
     *
     * @return the back button
     */
    public JButton getBackButton() {
        return backButton;
    }
}