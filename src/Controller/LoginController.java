/**
 * LoginController handles login button actions and connects the Login view with the Login model.
 * It gets the input from the frame and sends it to the model for validation.
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

    public LoginController(LoginFrame frame, LoginModel model) {

        this.frame = frame;
        this.model = model;

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

        if(model.isValidLogin(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login Successful");
            frame.dispose(); // close login window
        } 
        else {
            JOptionPane.showMessageDialog(frame,
                    "Invalid Username or Password", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}