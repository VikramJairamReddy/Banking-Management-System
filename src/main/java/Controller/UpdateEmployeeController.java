/**
 * Controls employee update operations.
 *
 * Responsibilities:
 * - Displays selected employee information.
 * - Updates employee role and email.
 * - Refreshes employee management window.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import Model.Employee;
import Model.EmployeeManager;
import View.UpdateEmployeeFrame;

public class UpdateEmployeeController {

    private final UpdateEmployeeFrame frame;
    private final Employee employee;
    private final EmployeeManagementController employeeController;

    /**
     * Creates update employee controller.
     *
     * @param employee selected employee
     * @param employeeController employee management controller
     */
    public UpdateEmployeeController(Employee employee,
                                    EmployeeManagementController employeeController) {

        this.employee = employee;
        this.employeeController = employeeController;
        this.employeeController.showEmployeeWindow(false);

        frame = new UpdateEmployeeFrame(employee);

        frame.getUpdateButton().addActionListener(e -> updateEmployee());
        frame.getCancelButton().addActionListener(e -> closeWindow());

        frame.setVisible(true);
    }

    /**
     * Updates selected employee role and email using EmployeeManager.
     */
    private void updateEmployee() {

        String email = frame.getEmail();

        if(email.isEmpty()) {
            frame.showMessage("Email cannot be empty");
            return;
        }

        if(!frame.confirmUpdate()) {
            return;
        }

        boolean updated = EmployeeManager.getInstance().updateEmployee(
                        employee.getEmployeeId(), frame.getSelectedRole(), email);

        if(updated) {
            frame.showMessage("Employee updated successfully");
            employeeController.refreshEmployees();
            closeWindow();
        }
        else {
            frame.showMessage("Unable to update employee");
        }
    }

    /**
     * Closes update employee window.
     */
    private void closeWindow() {
        frame.dispose();
        employeeController.showEmployeeWindow(true);
    }
}