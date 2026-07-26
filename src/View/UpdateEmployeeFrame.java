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
     * @param employeeId employee ID
     * @param name employee name
     * @param role current employee role
     * @param email current employee email
     */
    public UpdateEmployeeFrame(String employeeId, String name, Role role, String email) {

        setTitle("Update Employee");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(employeeId, name, role, email), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Creates employee information panel.
     *
     * @param employeeId employee ID
     * @param name employee name
     * @param role employee role
     * @param email employee email
     * @return the form panel
     */
    private JPanel createFormPanel(String employeeId, String name, Role role, String email) {

        JPanel panel = new JPanel(new GridLayout(4,2,10,15));
        panel.setBorder(new EmptyBorder(30,30,20,30));

        panel.add(new JLabel("Employee ID:"));
        employeeIdLabel = new JLabel(employeeId);
        panel.add(employeeIdLabel);

        panel.add(new JLabel("Name:"));
        nameLabel = new JLabel(name);
        panel.add(nameLabel);

        panel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(Role.values());
        roleBox.setSelectedItem(role);
        panel.add(roleBox);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(email);
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
     * @return selected role
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
     * Returns employee ID.
     *
     * @return employee ID
     */
    public String getEmployeeId() {
        return employeeIdLabel.getText();
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
}