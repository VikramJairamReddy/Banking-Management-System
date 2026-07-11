/**
 * LoginController handles login button actions and connects 
 * the Login view with the Login model.
 * It validates credentials and opens the dashboard on success.
 * 
 * @author Ganta Vikram Jairam Reddy
 */
package Controller;

import Model.CurrentUser;
import Model.LoginModel;
import Model.User;
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
            User user = model.validateLogin(username, password);

            if(user != null) {
                CurrentUser.login(user);
                new DashboardController(user.getUsername(), bankController);
                frame.dispose();

            }
            else{

                JOptionPane.showMessageDialog(frame,"Invalid username or password");
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}