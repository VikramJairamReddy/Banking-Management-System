/**
 * LoginController handles login button actions and connects the Login view with the Login model.
 * It gets the input from the frame and sends it to the model for validation.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.LoginModel;
import View.*;

import javax.swing.*;

public class LoginController {

    private LoginFrame frame;
    private LoginModel model;
    private DashboardFrame dashboard;

    public LoginController() {

        frame = new LoginFrame();
        model = new LoginModel();

        frame.setVisible(true);
        // Adding listener to the Login button
        frame.getLoginButton().addActionListener(e -> login());
    }

    /**
     * Checks username and password and displays the result
     */
    private void login() {

        // getting the inputs from frame
        String username = frame.getUsername();
        String password = frame.getPassword();

        try{
            if(model.isValidLogin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                this.frame.dispose(); // close login window
                
                dashboard = new DashboardFrame(model.getUsername());
                dashboard.setVisible(true);
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