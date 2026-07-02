/**
 * This class is to search for accounts based on account number or name.
 * 
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import java.awt.*;

public class FindAccountFrame extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JButton cancelButton;
    private JComboBox<String> searchTypeBox;

    public FindAccountFrame() {

        setTitle("Search Account");
        setSize(420, 290);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // -------- TITLE --------
        JLabel title = new JLabel("Search Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // -------- CENTER PANEL --------
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        searchTypeBox = new JComboBox<>(new String[]{"Account Number", "Name"});
        searchField = new JTextField();

        centerPanel.add(new JLabel("Search By:"));
        centerPanel.add(searchTypeBox);
        centerPanel.add(new JLabel("Search Value:"));
        centerPanel.add(searchField);

        // Add the center panel to the frame
        add(centerPanel, BorderLayout.CENTER);

        // -------- BUTTON PANEL --------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        searchButton = new JButton("Search");
        buttonAppearance(searchButton, new Color(41, 112, 204));

        cancelButton = new JButton("Cancel");
        buttonAppearance(cancelButton, Color.GRAY);

        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the frame
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
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    // -------- GETTERS --------

    /**
     * Returns the search field.
     *
     * @return The JTextField for search input.
     */
    public JTextField getSearchField() {
        return searchField;
    }

    /**
     * Returns the search button so that action listeners can be added to it.
     *
     * @return The JButton to search the accounts.
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Returns the cancel button so that action listeners can be added to it.
     *
     * @return The JButton for canceling the the search operation.
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Returns the search type combo box so that action listeners can be added to it.
     *
     * @return The JComboBox for selecting the search type.
     */
    public JComboBox<String> getSearchTypeBox() {
        return searchTypeBox;
    }

    /**
     * Returns the selected seaarch type.
     *
     * @return The string value of the selected search type.
     */
    public String getSearchType() {
        return (String) searchTypeBox.getSelectedItem();
    }

    /**
     * Returns the value entered in the search field.
     *
     * @return The string value entered in the search field (either account number or name).
     */
    public String getSearchValue() {
        return searchField.getText().trim();
    }
}