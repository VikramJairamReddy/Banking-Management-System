/**
 * Defines the different roles available in the banking management system.
 * 
 * Roles:
 * - ADMIN: Full access to all features.
 * - MANAGER: Access to banking operations and account management,
 *            but restricted from administrative tasks.
 * - EMPLOYEE: Access to day-to-day banking operations with limited permissions.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public enum Role {
    ADMIN,
    MANAGER,
    EMPLOYEE
}