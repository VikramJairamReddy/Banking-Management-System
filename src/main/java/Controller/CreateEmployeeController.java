/**
 * Controls employee creation operations.
 *
 * Responsibilities:
 * - Displays employee creating window.
 * - Validates employee information.
 * - Creates new employee records.
 * - Refreshes user management window.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import Model.EmployeeManager;
import Model.Validate;
import View.CreateEmployeeFrame;

public class CreateEmployeeController {

    private final CreateEmployeeFrame frame;
    private final UserManagementController userManagementController;

    /**
     * Creates create employee controller.
     *
     * @param userManagementController user management controller
     */
    public CreateEmployeeController(UserManagementController userManagementController) {

        this.userManagementController = userManagementController;
        userManagementController.showUserManagement(false);

        frame = new CreateEmployeeFrame();

        frame.getCreateButton().addActionListener(e -> createEmployee());
        frame.getCancelButton().addActionListener(e -> closeWindow());

        frame.setVisible(true);
    }

    /**
     * Creates a new employee after validating input.
     */
    private void createEmployee() {

        String name = frame.getEmployeeName();
        String username = frame.getUsername();
        String password = frame.getEmployeePassword();
        String email = frame.getEmployeeEmail();

        if(name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            frame.showMessage("All fields are required");
            return;
        }

        if(!Validate.isValidEmail(email)) {
            frame.showMessage("Enter a valid email address");
            return;
        }

        if(!frame.confirmCreate()) {
            return;
        }

        try {

            EmployeeManager.getInstance().addEmployee(name, username, password,
                        frame.getSelectedRole(), email);

            frame.showMessage("Employee created successfully");
            closeWindow();

        }
        catch(IllegalArgumentException e) {
            frame.showMessage(e.getMessage());
        }
    }

    /**
     * Closes create employee window and returns
     * to user management.
     */
    private void closeWindow() {
        frame.dispose();
        userManagementController.showUserManagement(true);
    }
}