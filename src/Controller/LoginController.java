/**
 * LoginController handles login button actions and connects 
 * the Login view with the Login model.
 * It validates credentials and opens the dashboard on success.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.CurrentUser;
import Model.Employee;
import Model.LoginModel;
import View.LoginFrame;

import javax.swing.*;

public class LoginController {

    private final LoginFrame frame;
    private final LoginModel model;
    private final BankController bankController;

    public LoginController(LoginFrame frame, BankController bankController) {

        this.frame = frame;
        model = new LoginModel();
        this.bankController = bankController;

        // Adding listener to the Login button
        frame.getLoginButton().addActionListener(e -> login());
        
        frame.setVisible(true);
    }

    /**
     * Validates login credentials and opens dashboard if successful
     */
    private void login() {

        // getting the inputs from frame
        String username = frame.getUsername();
        String password = frame.getPassword();

        try{
            // If invalid username or password the Login Model throws an exception,
            // Which is catched in the catch block
            Employee employee = model.validateLogin(username, password);
            CurrentUser.login(employee);
            new DashboardController(employee.getName(), bankController);
            frame.dispose();
        }
        catch(Exception e) {
            frame.resetPasswordField();
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}