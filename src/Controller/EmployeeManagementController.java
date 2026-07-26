/**
 * Controls employee management operations.
 *
 * Responsibilities:
 * - Search employees.
 * - Display employee information.
 * - Update employee details.
 * - Remove employees.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Employee;
import Model.EmployeeManager;
import View.EmployeeManagementFrame;

public class EmployeeManagementController {

    private final EmployeeManagementFrame frame;
    private final DashboardController dashboard;
    private final EmployeeManager employeeManager;

    private String selectedEmployeeId;

    /**
     * Creates employee management controller.
     *
     * @param dashboard dashboard controller
     */
    public EmployeeManagementController(DashboardController dashboard) {

        this.dashboard = dashboard;
        this.employeeManager = EmployeeManager.getInstance();

        frame = new EmployeeManagementFrame();

        dashboard.showDashboard(false);

        addSearchListener();
        frame.getRemoveButton().addActionListener(e -> removeEmployee());
        frame.getUpdateButton().addActionListener(e -> updateEmployee());
        frame.getBackButton().addActionListener(e -> closeWindow());

        frame.getEmployeeTable().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int row = frame.getEmployeeTable().getSelectedRow();
                    if(row != -1) {
                        showSelectedEmployee(row);
                    }
                }
            }
        });


        frame.setVisible(true);
    }


    /**
     * Adds document listener to search field.
     *
     * Updates employee search results while typing.
     */
    private void addSearchListener() {
        frame.getSearchField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchEmployee();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchEmployee();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchEmployee();
            }
        });
    }

    /**
     * Searches employees based on entered text.
     */
    private void searchEmployee() {

        String search = frame.getSearchField().getText().trim();

        selectedEmployeeId = null;
        frame.clearDetails();
        frame.clearTable();

        if(search.isEmpty()) {
            return;
        }

        Collection<Employee> employees = employeeManager.searchEmployees(search);

        loadEmployees(employees);
    }

    /**
     * Displays employees in the table.
     *
     * @param employees employees to display
     */
    private void loadEmployees(Collection<Employee> employees) {

        frame.clearTable();

        for(Employee employee : employees) {
            frame.addEmployee(new Object[] {
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getRole(),
                    employee.getEmployeeEmail()
            });
        }
    }

    /**
     * Displays selected employee details.
     *
     * @param row selected table row
     */
    private void showSelectedEmployee(int row) {

        selectedEmployeeId = (String) frame.getEmployeeTable().getValueAt(row, 0);
        Employee employee = employeeManager.findEmployeeById(selectedEmployeeId);

        if(employee == null) {
            return;
        }

        frame.displayEmployee(employee.getEmployeeId(), employee.getName(),
                employee.getRole().toString(), employee.getEmployeeEmail());
    }

    /**
     * Opens the update employee window for the selected employee
     * 
     * Validates selection and permission before opening the update view.
     */
    private void updateEmployee() {

        if(selectedEmployeeId == null) {
            frame.showMessage("Select an employee first");
            return;
        }

        if(!PermissionManager.canAssignRoles()) {
            frame.showMessage("You cannot Update Employees");
            return;
        }
    
        Employee employee = employeeManager.findEmployeeById(selectedEmployeeId);
    
        if(employee == null) {
            frame.showMessage("Employee not found");
            return;
        }
    
        //new UpdateEmployeeController();
    }

    /**
     * Removes the selected employee after confirmation.
     *
     * Deletes the employee record from EmployeeManager and refreshes the employee table.
     */
    private void removeEmployee() {

        if(selectedEmployeeId == null) {
            frame.showMessage("Select an employee first");
            return;
        }
    
        if(!frame.confirmRemove()) {
            return;
        }
    
        boolean removed = employeeManager.removeEmployeeById(selectedEmployeeId);
    
        if(removed) {
            frame.showMessage("Employee removed successfully");
            selectedEmployeeId = null;
            frame.clearDetails();
            searchEmployee();
        }
        else {
            frame.showMessage("Unable to remove employee");
        }
    }

    /**
     * Refreshes employee table after update.
     */
    public void refreshEmployees() {
        searchEmployee();
    }

    /**
     * Closes employee management window
     * 
     * Returns the user to User Management for administrators or the dashboard for other users.
     */
    private void closeWindow() {
        frame.dispose();
        if(PermissionManager.canAssignRoles()) {
            dashboard.openUserManagement();
        }
        else {
            dashboard.showDashboard(true);
        }
    }
}