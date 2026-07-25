/**
 * Controls account management operations.
 *
 * Responsibilities:
 * - Searches accounts
 * - Displays account details
 * - Removes accounts
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Account;
import View.ManageAccountFrame;

public class ManageAccountController {

    private final ManageAccountFrame frame;
    private final BankController bankController;
    private final DashboardController dashboard;

    private String selectedAccountNumber;

    public ManageAccountController(BankController bankController, DashboardController dashboard) {

        this.bankController = bankController;
        this.dashboard = dashboard;

        frame = new ManageAccountFrame();
        if(!PermissionManager.canRemoveAccount()) {
            frame.showMessage("You cannot remove accounts");
            return;
        }
        dashboard.showDashboard(false);

        addSearchListener();
        frame.getRemoveButton().addActionListener(e -> removeAccount());
        frame.getBackButton().addActionListener(e -> close());
        frame.getAccountTable().addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int row = frame.getAccountTable().getSelectedRow();
                    if(row != -1) {
                        showSelectedAccount(row);
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    /**
     * Adds a DocumentListener to the search field for
     * live searching whenever the user types or removes text.
     */
    private void addSearchListener() {
        frame.getSearchField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAccount();
            }
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAccount();
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAccount();
            }
        });
    }

    /**
     * Searches accounts based on entered text and updates the result table.
     */
    private void searchAccount() {
    
        String search = frame.getSearchField().getText().trim();
    
        if(search.isEmpty()) {
            frame.clearTable();
            selectedAccountNumber = null;
            return;
        }
    
        Collection<Account> accounts = bankController.searchAccounts(search);
    
        loadAccounts(accounts);
    }

    /**
     * Removes selected account.
     */
    private void removeAccount() {

        if(selectedAccountNumber == null) {
            frame.showMessage("Please select an account first");
            return;
        }
    
        if(!frame.confirmRemove()) {
            return;
        }
    
        boolean removed = bankController.removeAccount(selectedAccountNumber);
    
        if(removed) {
            frame.showMessage("Account removed successfully");
            frame.clearDetails();
            searchAccount();
            dashboard.refreshDashboard();
            selectedAccountNumber = null;
        }
        else {
            frame.showMessage("Unable to remove account");
        }
    }

    /**
     * Displays detailed information of the selected account.
     *
     * Retrieves the selected account from the table and
     * displays complete account information.
     *
     * @param row selected account table row
     */
    private void showSelectedAccount(int row) {

        selectedAccountNumber = (String) frame.getAccountTable().getValueAt(row, 0);
        Account account = bankController.getAccountByAccountNumber(selectedAccountNumber);
    
        if(account == null) {
            return;
        }
    
        frame.displayAccount(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getClass().getSimpleName().replace("Account", ""),
                String.format("$%.2f", account.getBalance()),
                account.getPhoneNumber()
        );
    }

    /**
     * Displays accounts in the search results table.
     *
     * @param accounts accounts to display
     */
    private void loadAccounts(Collection<Account> accounts) {

        frame.clearTable();

        for(Account account : accounts) {
            frame.addAccount(new Object[] {
                    account.getAccountNumber(),
                    account.getAccountHolderName(),
                    // Displaying only 'Savings' or 'Checkings' for account type
                    account.getClass().getSimpleName().replace("Account", ""),
                    String.format("$%.2f", account.getBalance())
            });
        }
    }

    /**
     * Closes window and returns dashboard.
     */
    private void close() {
        frame.dispose();
        if(PermissionManager.canAssignRoles()) {
            dashboard.openUserManagement();
        }
        else {
            dashboard.showDashboard(true);
        }
    }
}