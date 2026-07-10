/**
 * Controller for searching bank accounts by account number or customer name.
 *
 * It handles input from FindAccountFrame, fetches data from BankController,
 * and displays results in AccountResultFrame.
 *
 * Also manages navigation between search screen, result screen and dashboard.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import View.FindAccountFrame;
import View.AccountResultFrame;
import Model.Account;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FindAccountController {
    
    private BankController bankController;
    private FindAccountFrame findAccountFrame;
    private DashboardController dashboard;

    public FindAccountController(BankController bankController, DashboardController dashboard) {
        this.bankController = bankController;
        this.dashboard = dashboard;

        dashboard.showDashboard(false);
        findAccountFrame = new FindAccountFrame();

        // Add action listeners for the buttons in the FindAccountFrame
        findAccountFrame.getSearchButton().addActionListener(e -> searchAccount());
        findAccountFrame.getCancelButton().addActionListener(e -> closeWindow());

        findAccountFrame.setVisible(true);
    }

    /**
     * Searches for accounts based on the search value and displays the results in the AccountResultFrame.
     *
     * @param searchValue the value to search for (either account number or customer name)
     */
    private void searchAccount() {
        String searchType = findAccountFrame.getSearchType();
        String searchValue = findAccountFrame.getSearchValue();

        if(searchValue == null || searchValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(findAccountFrame,
                    "Please enter a valid search value.","Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Account> accounts = new ArrayList<>();


        if("Account Number".equals(searchType)) {
            // Since there cannot be multiple accounts with the same account number, 
            // we can directly get the account and add it to the list

            Account acc = bankController.getAccountByAccountNumber(searchValue);
            if(acc != null) {
                accounts.add(acc);
            }
        } 
        else if("Customer Name".equals(searchType)) {
            accounts = bankController.getAccountByName(searchValue);
        }

        StringBuilder sb = new StringBuilder();

        if(accounts.isEmpty()) {
            sb.append("\nNo matching account found for: ").append(searchValue);
        } 
        else {
            sb.append("Found ").append(accounts.size()).append(" account(s) for: ")
              .append(searchValue).append("\n")
              .append("=================================\n");

            for(Account account : accounts) {
                if(account == null) {
                    continue;
                }
                sb.append(account.toDisplayString()).append("\n");
            }
        }

        // Instantiate the AccountResultFrame 
        AccountResultFrame accountResultFrame = new AccountResultFrame(searchValue);
        accountResultFrame.setResult(sb.toString());

        // Add action listener for the back button in the AccountResultFrame
        accountResultFrame.getBackButton().addActionListener(e -> {
            accountResultFrame.dispose();
            findAccountFrame.setVisible(true);
        });
 

        // Hide the FindAccountFrame and show the AccountResultFrame
        findAccountFrame.setVisible(false);
        accountResultFrame.setVisible(true);
    }

    /**
     * Closes the FindAccountFrame and AccountResultFrame, and shows the Dashboard.
     */
    private void closeWindow() {
        dashboard.refreshDashboard();
        dashboard.showDashboard(true);
        findAccountFrame.dispose();
    }
}
