/**
 * Handles the Create Account screen logic.
 *
 * Responsibilities:
 * - Collect user input from the CreateAccountFrame
 * - Create Savings or Checking account objects
 * - Send account data to the BankController
 * - Display success or error messages
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import Model.Account;
import Model.Bank;
import Model.CheckingAccount;
import Model.SavingsAccount;
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
     * Creates a new bank account based on the user input.
     *
     * Steps:
     * - Collect user input from the CreateAccountFrame
     * - Create Savings or Checking account objects
     * - Send account data to the BankController
     * - Display success or error messages
     */
    private void createAccount() {

        String name = view.getEnteredName().trim();
        String phone = view.getEnteredPhoneNumber().trim();
        String type = view.getAccountTypeSelected();

        if(name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name and phone number cannot be empty.");
            return;
        }

        try{
            String accountNumber = bankController.generateAccountNumber();

            Account account;

            if(type.equals("Savings")) {
                account = new SavingsAccount(accountNumber, name, phone, Bank.getInterestRate());
            } 
            else {
                account = new CheckingAccount(accountNumber, name, phone);
            }

            boolean success = bankController.createAccount(account);

            if(success) {
                JOptionPane.showMessageDialog(view,
                        "Account created successfully!\nAccount Number: " + accountNumber);
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