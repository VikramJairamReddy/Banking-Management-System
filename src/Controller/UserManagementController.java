/**
 * Controls user management operations.
 *
 * Responsibilities:
 * - Opens user management options.
 * - Navigates to account management.
 * - Handles navigation back to dashboard.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import View.UserManagementFrame;

public class UserManagementController {

    private final UserManagementFrame frame;
    private final DashboardController dashboard;

    public UserManagementController(String username, String role, DashboardController dashboard) {

        this.dashboard = dashboard;
        dashboard.showDashboard(false);

        frame = new UserManagementFrame(username, role);

        frame.getManageAccountButton().addActionListener(e -> manageAccount());
        frame.getEmployeeManagementButton().addActionListener(e -> employeeManagement());
        frame.getBackButton().addActionListener(e -> closeWindow());

        frame.setVisible(true);
    }

    /**
     * Opens customer account management.
     */
    private void manageAccount() {

        frame.dispose();
        dashboard.openManageAccount();
    }

    /**
     * Opens employee management.
     */
    private void employeeManagement() {
        new EmployeeManagementController(dashboard);
        frame.dispose();
    }

    /**
     * Closes window and returns to dashboard.
     */
    private void closeWindow() {
        frame.dispose();
        dashboard.showDashboard(true);
    }
}