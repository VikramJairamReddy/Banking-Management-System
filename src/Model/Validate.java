/**
 * This class provides methods to validate user input.
 * 
 * Includes methods to check if a given account number, name, phone number, and amount are valid.
 * 
 * @author Ganta Vikram Jairam Reddy
 **/
package Model;

public class Validate {

    /**
     * Method to check if a given phone number is valid.
     * A valid phone number is a string of exactly 10 digits without country code.
     * 
     * @param phoneNumber The phone number to be validated.
     * @return true if the phone number is valid, false otherwise.
     **/
    public static boolean isValidPhoneNumber(String phoneNumber) {
        //using regular expression to check if the phone number is valid, i.e., 10 digits
        return (phoneNumber != null) && (phoneNumber.matches("\\d{10}")); 
    }
    
    /**
     * Method to check if a given account number is valid.
     * A valid account number starts with "ACCT" followed by exactly 6 digits.
     * 
     * @param acc The account number to be validated.
     * @return true if the account number is valid, false otherwise.
     **/
    public static boolean isValidAccountNumber(String acc) {
        //using regular expression to check if the account number is valid, 
        // i.e., starts with "ACCT" followed by 6 digits
        return acc != null && acc.matches("ACCT\\d{6}");
    }

    /**
     * Method to check if a given name is valid.
     * A valid name is a non-null string with more than 2 characters.
     * 
     * @param name The name to be validated.
     * @return true if the name is valid, false otherwise.
     **/
    public static boolean isValidName(String name) {
        return name != null && name.length() > 2;
    }

    /**
     * Method to check if a given amount is valid.
     * A valid amount, it is a positive  value.
     * 
     * @param amount The amount to be validated.
     * @return true if the amount is valid, false otherwise.
     **/
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
