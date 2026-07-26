/**
 * Displays employee creation options.
 *
 * Features:
 * - Enter employee information.
 * - Assign employee role.
 * - Create a new employee.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Model.Role;

public class CreateEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JComboBox<Role> roleBox;

    private JButton createButton;
    private JButton cancelButton;

    /**
     * Creates the Create Employee window.
     */
    public CreateEmployeeFrame() {

        setTitle("Create Employee");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Creates the employee information form.
     *
     * @return employee form panel
     */
    private JPanel createFormPanel() {

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 15));
        panel.setBorder(new EmptyBorder(30, 30, 20, 30));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(Role.values());
        roleBox.setSelectedItem(Role.EMPLOYEE);
        panel.add(roleBox);

        return panel;
    }

    /**
     * Creates bottom action buttons.
     *
     * @return button panel
     */
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));

        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");

        buttonAppearance(createButton, new Color(34, 139, 34));
        buttonAppearance(cancelButton, Color.DARK_GRAY);

        panel.add(createButton);
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

        button.setPreferredSize(new Dimension(120, 35));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    // ---------------- GETTERS ----------------

    /**
     * Returns employee name.
     *
     * @return employee name
     */
    public String getEmployeeName() {
        return nameField.getText().trim();
    }

    /**
     * Returns employee username.
     *
     * @return employee username
     */
    public String getUsername() {
        return usernameField.getText().trim();
    }

    /**
     * Returns employee password.
     *
     * @return employee password
     */
    public String getEmployeePassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Returns employee email.
     *
     * @return employee email
     */
    public String getEmployeeEmail() {
        return emailField.getText().trim();
    }

    /**
     * Returns selected employee role.
     *
     * @return selected role
     */
    public Role getSelectedRole() {
        return (Role) roleBox.getSelectedItem();
    }

    /**
     * Returns create button.
     *
     * @return create button
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Returns cancel button.
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
     * Displays a confirmation dialog before creating an employee.
     *
     * @return true if confirmed, otherwise false
     */
    public boolean confirmCreate() {

        int result = JOptionPane.showConfirmDialog(
                this,
                "Create this employee?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }
}