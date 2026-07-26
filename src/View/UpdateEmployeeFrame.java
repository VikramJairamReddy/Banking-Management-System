/**
 * Displays employee update options.
 *
 * Features:
 * - Displays selected employee information.
 * - Updates employee role.
 * - Updates employee email.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Model.Employee;
import Model.Role;

public class UpdateEmployeeFrame extends JFrame {

    private JLabel employeeIdLabel;
    private JLabel nameLabel;

    private JTextField emailField;
    private JComboBox<Role> roleBox;

    private JButton updateButton;
    private JButton cancelButton;

    /**
     * Creates the Update Employee window.
     *
     * @param employee selected employee
     */
    public UpdateEmployeeFrame(Employee employee) {

        setTitle("Update Employee");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(employee), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Creates employee information panel.
     *
     * @param employee selected employee
     * @return the form panel
     */
    private JPanel createFormPanel(Employee employee) {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(new EmptyBorder(30, 30, 20, 30));

        panel.add(new JLabel("Employee ID:"));
        employeeIdLabel = new JLabel(employee.getEmployeeId());
        panel.add(employeeIdLabel);

        panel.add(new JLabel("Name:"));
        nameLabel = new JLabel(employee.getName());
        panel.add(nameLabel);

        panel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(Role.values());
        roleBox.setSelectedItem(employee.getRole());
        panel.add(roleBox);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(employee.getEmployeeEmail());
        panel.add(emailField);

        return panel;
    }

    /**
     * Creates bottom action buttons.
     *
     * @return button panel
     */
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,15));

        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");

        buttonAppearance(updateButton, new Color(41,112,204));
        buttonAppearance(cancelButton, Color.DARK_GRAY);

        panel.add(updateButton);
        panel.add(cancelButton);

        return panel;
    }


    /**
     * Sets button appearance.
     *
     * @param button button to style
     * @param color button color
     */
    private void buttonAppearance(JButton button, Color color) {

        button.setPreferredSize(new Dimension(120,35));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD,14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // ---------------- GETTERS ----------------

    /**
     * Returns the selected role of the employee.
     *
     * @return selected employee role
     */
    public Role getSelectedRole() {
        return (Role) roleBox.getSelectedItem();
    }

    /**
     * Returns the entered email.
     *
     * @return employee email
     */
    public String getEmail() {
        return emailField.getText().trim();
    }

    /**
     * Returns the update button.
     *
     * @return update button
     */
    public JButton getUpdateButton() {
        return updateButton;
    }

    /**
     * Returns the cancel button.
     *
     * @return cancel button
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Displays a message dialog.
     *
     * @param message message to display
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Displays a confirmation dialog before updating an employee.
     *
     * Asks the user to confirm the update operation before
     * applying changes to employee information.
     *
     * @return true if the user confirms the update, false otherwise
     */
    public boolean confirmUpdate() {
        int result = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to Update?",
            "Confirmation", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }
}