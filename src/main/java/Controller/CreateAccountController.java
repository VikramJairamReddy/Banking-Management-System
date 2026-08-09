/**
 * Handles the Create Account screen logic.
 *
 * Responsibilities:
 * - Collect user input from the CreateAccountFrame
 * - Create customer objects
 * - Create Savings or Checking account objects
 * - Send account data to the BankController
 * - Display success or error messages
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import Model.Account;
import Model.Validate;
import View.CreateAccountFrame;

import javax.swing.*;

public class CreateAccountController {

    private final CreateAccountFrame view;
    private final DashboardController dashboard;
    private final BankController bankController;

    /**
     * Initializes the CreateAccountController and opens the UI.
     *
     * @param bankController shared controller for bank operations
     * @param dashboard dashboard frame
     */
    public CreateAccountController(BankController bankController, DashboardController dashboard) {

        this.bankController = bankController;
        this.dashboard = dashboard;

        dashboard.showDashboard(false);
        view = new CreateAccountFrame();

        // Button listeners
        view.getCreateButton().addActionListener(e -> createAccount());
        view.getCancelButton().addActionListener(e -> closeView());

        view.setVisible(true);
    }

    /**
     * Creates a new bank account.
     *
     * Creates a customer first, then creates the account using that customer object.
     */
    private void createAccount() {

        String name = view.getEnteredName().trim();
        String phone = view.getEnteredPhoneNumber().trim();
        String type = view.getAccountTypeSelected();
        String email = view.getEnteredEmail().trim();
        String address = view.getEnteredAddress().trim();

        if(name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name and phone number cannot be empty.");
            return;
        }

        try{
            if(!Validate.isValidName(name)) {
                throw new IllegalArgumentException("Invalid Name");
            }
            if(!Validate.isValidPhoneNumber(phone)) {
                throw new IllegalArgumentException("Invalid phone number");
            }
            if(!Validate.isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email address");
            }
            if(!Validate.isValidAddress(address)) {
                throw new IllegalArgumentException("Invalid address");
            }

            Account account = bankController.createAccount(name, phone, email, address, type);

            if(account != null) {
                JOptionPane.showMessageDialog(view,
                        "Account created successfully!\n" 
                       + "Account Number: " + account.getAccountNumber());
                closeView();
            } 
            else {
                JOptionPane.showMessageDialog(view, "Failed to create account");
            }

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(),"Error", 
                                            JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Closes the Create Account window without saving.
     */
    private void closeView() {
        dashboard.refreshDashboard();
        dashboard.showDashboard(true);
        view.dispose();
    }
}