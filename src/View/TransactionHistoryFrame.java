/**
 * Displays transaction history of accounts.
 *
 * Allows users to search transactions by:
 * - Account Number
 * - Transaction ID
 * - Date
 *
 * Provides options for filtering and sorting transactions.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionHistoryFrame extends JFrame {

    // This is a template which holds data in rows and columns for a Table
    private DefaultTableModel table;

    private JTable historyTable;
    private JTextField searchField;

    private JButton backButton;
    private JButton filterButton;

    private JComboBox<String> searchTypeBox;

    private JLabel resultLabel;

    private String selectedTransactionType;
    private String selectedSortOption;

    public TransactionHistoryFrame() {

        setTitle("Transactions");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // ---------- Title ----------

        JLabel title = new JLabel("Transaction History" ,SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // ---------- Search Label ----------

        JLabel searchLabel = new JLabel("Search Transactions",SwingConstants.CENTER);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // ---------- Search Panel (Search Label + Form Panel ----------

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

        searchTypeBox = new JComboBox<>(new String[]{"Account Number", "Transaction ID", "Date"});

        searchField = new JTextField(20);

        filterButton = new JButton("Filter");
        buttonAppearance(filterButton,Color.DARK_GRAY);

        formPanel.add(searchTypeBox);
        formPanel.add(searchField);
        formPanel.add(filterButton);

        JPanel searchPanel = new JPanel(new BorderLayout());

        searchPanel.add(searchLabel,BorderLayout.NORTH);
        searchPanel.add(formPanel, BorderLayout.SOUTH);

        // ---------- Top Panel ----------

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        // Adding title and search section to the frame
        add(topPanel, BorderLayout.NORTH);

        // ---------- JTable ----------

        table = new DefaultTableModel(new String[]{"Transaction ID", "Type", "Account",
                        "Target", "Amount", "Date"}, 0) { 
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // This makes all cells non-editable
        }};

        historyTable = new JTable(table);
        historyTable.setRowHeight(25);
        historyTable.getTableHeader().setReorderingAllowed(false);

        //Transaction ID
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        //Type
        historyTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        // Account
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        //Target
        historyTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        // Amount
        historyTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        // Date
        historyTable.getColumnModel().getColumn(5).setPreferredWidth(170);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        // Addind a scroll pane which holds the JTable to the frame
        add(scrollPane, BorderLayout.CENTER);

        // ---------- Button Panel ----------

        JPanel buttonPanel = new JPanel(new BorderLayout());

        resultLabel = new JLabel("Showing 0 transactions", SwingConstants.CENTER);
        buttonPanel.add(resultLabel, BorderLayout.CENTER);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,10));
        backButton = new JButton("Back");
        buttonAppearance(backButton, Color.GRAY);
        backPanel.add(backButton);

        buttonPanel.add(backPanel, BorderLayout.EAST);

        // Adding button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets button appearance.
     */
    private void buttonAppearance(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(130,35));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    /**
     * Allows users to select transaction type and sorting options.
     *
     * The selected filter values are stored and can be accessed using getter methods.
     *
     * @return true if the user applies the filter, if this is cancelled it returns false
     */
    public boolean showFilter() {

        JComboBox<String> typeBox = new JComboBox<>(new String[]{"All", "Deposit","Withdraw","Transfer"});
        JComboBox<String> sortBox = new JComboBox<>(new String[]{"Newest First", "Oldest First"});
    
        JPanel panel = new JPanel(new GridLayout(2,2,10,10));

        panel.add(new JLabel("Transaction Type"));
        panel.add(typeBox);

        panel.add(new JLabel("Sort By"));
        panel.add(sortBox);

        int result = JOptionPane.showConfirmDialog(this, panel,"Filters", 
                            JOptionPane.OK_CANCEL_OPTION);
    
        if(result == JOptionPane.OK_OPTION) {
            selectedTransactionType = (String) typeBox.getSelectedItem();
            selectedSortOption = (String) sortBox.getSelectedItem();
    
            return true;
        }
        return false;
    }

    // ---------- Getters ----------

    /**
     * Returns the Back Button so that the controller can add an action listener to it.
     *
     * @return the Back Button
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Returns the Filter Button so that the controller can add an action listener to it.
     *
     * @return the Filter Button
     */
    public JButton getFilterButton() {
        return filterButton;
    }

    /**
     * Returns the Search text field so that the controller can add an Document listener 
     * to handle live searching..
     *
     * @return the Search text field
     */
    public JTextField getSearchField() {
        return searchField;
    }

    /**
     * Returns the Search type Combo Box so that the controller 
     * can handle changes in search.
     *
     * @return the Search type Combo Box
     */
    public JComboBox<String> getSearchTypeBox() {
        return searchTypeBox;
    }

    /**
     * Returns the type of transaction filter selected by the user
     *
     * @return the selected transaction type
     */
    public String getSelectedTransactionType() {
        return selectedTransactionType;
    }
    
    /**
     * Returns the sort option selected by the user
     *
     * @return the selected sort option
     */
    public String getSelectedSortOption() {
        return selectedSortOption;
    }

    /**
     * It updates the Result Label
     * 
     * @param count the current number of results displaying in JTable
     */
    public void updateResultLabel(int count) {
        resultLabel.setText("Showing " + count + " transaction" + ((count == 1)? "" : "s"));
    }

    /**
     * Returns the JTable used to display transaction records.
     *
     * @return the transaction history table
     */
    public JTable getHistoryTable() {
        return historyTable;
    }

    /**
     * Removes all transaction records currently displayed in the JTable.
     */
    public void clearTable() {
        table.setRowCount(0);
    }

    /**
     * Adds a transaction record as a new row in the JTable.
     *
     * The row should contain transaction details such as:
     * transaction ID, transaction type, account number,
     * target account, amount, and transaction date.
     *
     * @param transactionData array containing transaction details to be displayed in the table
     */
    public void addTransaction(Object[] row) {
        table.addRow(row);
    }
}