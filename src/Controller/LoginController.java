/**
 * LoginController handles login button actions and connects 
 * the Login view with the Login model.
 * It validates credentials and opens the dashboard on success.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.LoginModel;
import View.LoginFrame;

import javax.swing.*;

public class LoginController {

    private LoginFrame frame;
    private LoginModel model;
    private BankController bankController;

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
            if(model.isValidLogin(username, password)) {

                this.frame.dispose(); // close login windsssow

                // Open dashboard controller
                new DashboardController(model.getUsername(), bankController);
            } 
            else {
                frame.resetPasswordField();
                throw new IllegalArgumentException("Invalid Username or Password");
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}