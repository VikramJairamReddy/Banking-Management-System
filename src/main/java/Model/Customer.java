/**
 * Represents a customer in the Banking System.
 *
 * Stores customer information including customer ID, name, phone number, email, and address.
 *
 * @author Ganta Vikram Jairam Reddy
 */

package Model;

public class Customer {

    private final String customerId;
    private String name;
    private String phone;
    private String email;
    private String address;

    /**
     * Creates a new customer object.
     *
     * @param customerId unique customer ID
     * @param name customer full name
     * @param phone customer phone number
     * @param email customer email address
     * @param address customer address
     */
    public Customer(String customerId, String name, String phone, String email, String address) {

        if(!Validate.isValidName(name)) {
            throw new IllegalArgumentException("Invalid customer name");
        }
        
        if(!Validate.isValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        
        if(!Validate.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        if(!Validate.isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid address");
        }

        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Returns customer ID.
     *
     * @return customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns customer name.
     *
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns customer phone number.
     *
     * @return customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns customer email.
     *
     * @return customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns customer address.
     *
     * @return customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates customer name.
     *
     * @param name new customer name
     */
    public boolean setName(String name) {

        if(!Validate.isValidName(name)) {
            return false;
        }

        this.name = name;
        return true;
    }


    /**
     * Updates customer phone number.
     *
     * @param phone new customer phone number
     */
    public boolean setPhone(String phone) {

        if(!Validate.isValidPhoneNumber(phone)) {
            return false;
        }

        this.phone = phone;
        return true;
    }

    /**
     * Updates customer email.
     *
     * @param email new customer email
     */
    public boolean setEmail(String email) {

        if(!Validate.isValidEmail(email)) {
            return false;
        }

        this.email = email;
        return true;
    }

    /**
     * Updates customer address.
     *
     * @param address new customer address
     */
    public boolean setAddress(String address) {

        if(!Validate.isValidAddress(address)) {
            return false;
        }
        this.address = address;
        return true;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return string representation of the customer
     */
    @Override
    public String toString() {
        return String.format("[customerId='%s', name='%s', phone='%s', email='%s']",
                customerId, name, phone, email);
    }

    /**
     * Returns a formatted string representation of the customer for display purposes.
     *
     * @return formatted string representation of the customer
     */
    public String toDisplayString() {
        return "\nCustomer ID : " + customerId + "\n" +
               "Name        : " + name + "\n" +
               "Phone       : " + phone + "\n" +
               "Email       : " + email + "\n" +
               "Address     : " + address + "\n";
    }
}