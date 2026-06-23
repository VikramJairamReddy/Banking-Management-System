/**
 * Main class to launch the Bank Management System application.
 * It initializes the GUI frame, by passing it in Controller instance.
 * 
 * SwingUtilities.invokeLater is used to ensure that the 
 * GUI is created and updated on the Event Dispatch Thread, 
 * so that the application does not freeze and remains responsive.
 * 
 * @author Ganta Vikram Jairam Reddy 
 */

import Controller.LoginController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginController();
        });
    }
}
