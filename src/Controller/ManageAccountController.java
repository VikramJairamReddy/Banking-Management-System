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

import Model.Account;
import View.ManageAccountFrame;

public class ManageAccountController {

    private final ManageAccountFrame frame;
    private final BankController bankController;
    private final DashboardController dashboard;

    public ManageAccountController(BankController bankController, DashboardController dashboard) {

        this.bankController = bankController;
        this.dashboard = dashboard;

        frame = new ManageAccountFrame();
        dashboard.showDashboard(false);

        frame.getSearchButton().addActionListener(e -> searchAccount());
        frame.getRemoveButton().addActionListener(e -> removeAccount());
        frame.getBackButton().addActionListener(e -> close());

        frame.setVisible(true);
    }

    /**
     * Searches account using account number.
     */
    private void searchAccount() {
        String accountNumber = frame.getAccountNumberField().getText().trim();
        Account account = bankController.getAccountByAccountNumber(accountNumber);

        if(account == null) {
            frame.clearDetails();
            frame.showMessage("Account not found");
            return;
        }

        frame.displayAccount(account.getAccountNumber(), account.getAccountHolderName(),
                    account.getAccountType() + " Account", String.valueOf(account.getBalance()),
                    account.getPhoneNumber());
    }

    /**
     * Removes selected account.
     */
    private void removeAccount() {

        if(!frame.confirmRemove()) {
            return;
        }

        String accountNumber = frame.getAccountNumberField().getText().trim();
        boolean removed = bankController.removeAccount(accountNumber);

        if(removed) {
            frame.showMessage("Account removed successfully");
            frame.clearDetails();
            dashboard.refreshDashboard();
        }
        else {
            frame.showMessage("Unable to remove account");
        }
    }

    /**
     * Closes window and returns dashboard.
     */
    private void close() {
        frame.dispose();
        dashboard.showDashboard(true);
    }
}