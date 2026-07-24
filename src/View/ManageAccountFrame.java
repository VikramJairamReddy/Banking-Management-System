/**
 * Displays account management options.
 *
 * Features:
 * - Search accounts
 * - Display multiple account search results
 * - Displays selected account details
 * - Remove account
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ManageAccountFrame extends JFrame {

    private JTextField searchField;

    // Table
    private DefaultTableModel table;
    private JTable accountTable;

    private JLabel accountNumberLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel balanceLabel;
    private JLabel phoneLabel;

    private JButton searchButton;
    private JButton removeButton;
    private JButton backButton;


    /**
     * Creates the Manage Account window.
     */
    public ManageAccountFrame() {

        setTitle("Manage Account");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSearchPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }


    /**
     * Creates search section.
     *
     * @return search panel
     */
    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panel.setBorder(new EmptyBorder(10, 10, 10,10));

        searchField = new JTextField(25);

        searchButton = new JButton("Search");
        buttonAppearance(searchButton, new Color(41,112,204));

        panel.add(new JLabel("Search Account:"));
        panel.add(searchField);
        panel.add(searchButton);

        return panel;
    }

    /**
     * Creates the center section which contains table and details.
     *
     * @return center panel
     */
    private JPanel createCenterPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        // -------- Account Table --------

        table = new DefaultTableModel(new String[]{"Account Number", "Name", "Type", "Balance"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        accountTable = new JTable(table);

        accountTable.setRowHeight(25);
        // disable relocation from the table
        accountTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results"));

        panel.add(scrollPane, BorderLayout.CENTER);

        // -------- Details Panel --------

        JPanel detailsPanel = new JPanel(new GridLayout(5,2,10,10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Account Details"));

        detailsPanel.add(new JLabel("Account Number:"));
        accountNumberLabel = new JLabel("-");
        detailsPanel.add(accountNumberLabel);

        detailsPanel.add(new JLabel("Name:"));
        nameLabel = new JLabel("-");
        detailsPanel.add(nameLabel);

        detailsPanel.add(new JLabel("Account Type:"));
        typeLabel = new JLabel("-");
        detailsPanel.add(typeLabel);

        detailsPanel.add(new JLabel("Balance:"));
        balanceLabel = new JLabel("-");
        detailsPanel.add(balanceLabel);

        detailsPanel.add(new JLabel("Phone:"));
        phoneLabel = new JLabel("-");
        detailsPanel.add(phoneLabel);

        panel.add(detailsPanel, BorderLayout.SOUTH);


        return panel;
    }

    /**
     * Creates bottom action buttons.
     *
     * @return button panel
     */
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,15));

        removeButton = new JButton("Remove Account");
        backButton = new JButton("Back");

        buttonAppearance(removeButton, new Color(204,60,60));
        buttonAppearance(backButton, Color.DARK_GRAY);

        removeButton.setEnabled(false);

        panel.add(removeButton);
        panel.add(backButton);

        return panel;
    }

    /**
     * Sets button appearance.
     *
     * @param button button to style
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
     * Returns search field.
     *
     * @return search field
     */
    public JTextField getSearchField() {
        return searchField;
    }

    /**
     * Returns search button.
     *
     * @return search button
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Returns remove button.
     *
     * @return remove button
     */
    public JButton getRemoveButton() {
        return removeButton;
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
     * Returns account table.
     *
     * @return account table
     */
    public JTable getAccountTable() {
        return accountTable;
    }

    // ---------------- TABLE METHODS ----------------

    /**
     * Clears all table records.
     */
    public void clearTable() {
        table.setRowCount(0);
    }

    /**
     * Adds account information into table.
     *
     * @param row account details
     */
    public void addAccount(Object[] row) {
        table.addRow(row);
    }

    // ---------------- DISPLAY METHODS ----------------

    /**
     * Displays selected account details.
     *
     * @param accountNumber account number
     * @param name account holder name
     * @param type account type
     * @param balance account balance
     * @param phone account holder phone
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

        removeButton.setEnabled(false);
        removeButton.setBackground(new Color(55, 65, 81));
    }

    /**
     * Displays message dialog.
     *
     * @param message message to display
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Confirmation Dialog before actually removing an account
     * 
     * @return true if confirmed
     */
    public boolean confirmRemove() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove this account?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }
}