/**
 * Displays employee management options.
 *
 * Features:
 * - Search employees
 * - Display multiple employee search results
 * - Display selected employee details
 * - Update employee information
 * - Remove employees
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class EmployeeManagementFrame extends JFrame {

    private JTextField searchField;

    // Table
    private DefaultTableModel table;
    private JTable employeeTable;

    private JLabel employeeIdLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel emailLabel;

    private JButton removeButton;
    private JButton updateButton;
    private JButton backButton;


    /**
     * Creates the Employee Manage window.
     */
    public EmployeeManagementFrame() {

        setTitle("Employee Management");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setResizable(false);
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
        
        updateButton = new JButton("Update");
        buttonAppearance(updateButton, new Color(55, 65, 81));
        updateButton.setEnabled(false);

        panel.add(new JLabel("Search Employees:"));
        panel.add(searchField);
        panel.add(updateButton);

        return panel;
    }

    /**
     * Creates the center section which contains table and details.
     *
     * @return center panel
     */
    private JPanel createCenterPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        // -------- Employee Table --------

        table = new DefaultTableModel(new String[]{"Employee ID", "Name", "Role", "Email"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(table);

        employeeTable.setRowHeight(25);
        // disable relocation from the table
        employeeTable.getTableHeader().setReorderingAllowed(false);

        // Employee ID
        employeeTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        // Name
        employeeTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        // Role
        employeeTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        // Email
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(180);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results"));

        panel.add(scrollPane, BorderLayout.CENTER);

        // -------- Details Panel --------

        JPanel detailsPanel = new JPanel(new GridLayout(6,2,10,10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        detailsPanel.add(new JLabel("Employee ID:"));
        employeeIdLabel = new JLabel("-");
        detailsPanel.add(employeeIdLabel);

        detailsPanel.add(new JLabel("Name:"));
        nameLabel = new JLabel("-");
        detailsPanel.add(nameLabel);

        detailsPanel.add(new JLabel("Role:"));
        roleLabel = new JLabel("-");
        detailsPanel.add(roleLabel);

        detailsPanel.add(new JLabel("Email:"));
        emailLabel = new JLabel("-");
        detailsPanel.add(emailLabel);

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

        removeButton = new JButton("Remove Employee");
        backButton = new JButton("Back");

        buttonAppearance(removeButton, new Color(55, 65, 81));
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
     * Returns Update button.
     *
     * @return update button
     */
    public JButton getUpdateButton() {
        return updateButton;
    }

    /**
     * Returns employee table.
     *
     * @return employee table
     */
    public JTable getEmployeeTable() {
        return employeeTable;
    }

    // ---------------- TABLE METHODS ----------------

    /**
     * Clears all table records.
     */
    public void clearTable() {
        table.setRowCount(0);
    }

    /**
     * Adds employee information into table.
     *
     * @param row employee details
     */
    public void addEmployee(Object[] row) {
        table.addRow(row);
    }

    // ---------------- DISPLAY METHODS ----------------

    /**
     * Displays selected employee details.
     *
     * @param employeeId employee ID
     * @param name employee name
     * @param role employee role
     * @param email employee email
     */
    public void displayEmployee(String employeeId, String name, String role, String email) {

        employeeIdLabel.setText(employeeId);
        nameLabel.setText(name);
        roleLabel.setText(role);
        emailLabel.setText(email);

        updateButton.setEnabled(true);
        buttonAppearance(updateButton, new Color(41,112,204));

        removeButton.setEnabled(true);
        removeButton.setBackground(new Color(204, 60, 60));
    }

    /**
     * Clears displayed employee information.
     */
    public void clearDetails() {

        employeeIdLabel.setText("-");
        nameLabel.setText("-");
        roleLabel.setText("-");
        emailLabel.setText("-");
        searchField.setText("");

        updateButton.setEnabled(false);
        buttonAppearance(updateButton, new Color(55, 65, 81));
        
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
     * Confirmation Dialog before actually removing an employee
     * 
     * @return true if confirmed
     */
    public boolean confirmRemove() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove this Employee?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }
}