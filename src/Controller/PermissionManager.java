package Controller;

import Model.CurrentUser;
import Model.Role;

public class PermissionManager {
    /**
     * Checks whether a user is currently logged in.
     *
     * @return true if a user is logged in, otherwise false
     */
    public static boolean isLoggedIn() {
        return CurrentUser.isLoggedIn();
    }

    /**
     * Checks whether the current user is an administrator.
     *
     * @return true if user role is ADMIN, otherwise false
     */
    public static boolean isAdmin() {
        return getRole() == Role.ADMIN;
    }

    /**
     * Checks whether the current user is a manager.
     *
     * @return true if user role is MANAGER, otherwise false
     */
    public static boolean isManager() {
        return getRole() == Role.MANAGER;
    }

    /**
     * Checks whether the current user is an employee.
     *
     * @return true if user role is EMPLOYEE, otherwise false
     */
    public static boolean isEmployee() {
        return getRole() == Role.EMPLOYEE;
    }

    /**
     * Checks whether the user can create new accounts.
     * Admin, Manager, and Employee can create accounts.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canCreateAccount() {
        return isAdmin() || isManager() || isEmployee();
    }

    /**
     * Checks whether the user can remove accounts.
     * Only Admin and Manager can remove accounts.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canRemoveAccount() {
        return isAdmin() || isManager();
    }

    /**
     * Checks whether the user can deposit.
     * Admin, Manager, and Employee can perform deposit
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canDeposit() {
        return isAdmin() || isManager() || isEmployee();
    }

    /**
     * Checks whether the user can withdraw.
     * Admin, Manager and Employee can perform withdraw
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canWithdraw() {
        return isAdmin() || isManager() || isEmployee();
    }

    /**
     * Checks whether the user can transfer money.
     * Admin, Manager, and Employee can perform this
    *
     * @return true if user has permission, otherwise false
     */
    public static boolean canTransfer() {
        return isAdmin() || isManager() || isEmployee();
    }


    /**
     * Checks whether the user can view transaction history.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canViewTransactions() {
        return isAdmin() || isManager();
    }


    /**
     * Checks whether the user can view all account information.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canViewAccounts() {
        return isAdmin() || isManager() || isEmployee();
    }


    /**
     * Checks whether the user can manage employees.
     * Only administrators have this permission.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canManageUsers() {
        return isAdmin();
    }


    /**
     * Checks whether the user can assign roles.
     * Only administrators have this permission.
     *
     * @return true if user has permission, otherwise false
     */
    public static boolean canAssignRoles() {
        return isAdmin();
    }


    /**
     * Returns the role of the currently logged-in user.
     * @return current user's role, or null if no user is logged in
     */
    private static Role getRole() {
        if(CurrentUser.getCurrentUser() == null) {
            return null;
        }

        return CurrentUser.getCurrentUser().getRole();
    }
}
