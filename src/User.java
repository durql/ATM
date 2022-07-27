import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    //The first name of the user
    private String firstName;

    //The last name of the user
    private String lastName;

    //The ID number of the user
    private String uuid;

    //The MD5 hash of the user's pin number
    private byte pinHash[];

    //The list of accounts for this user
    private ArrayList<Account> accounts;

    /**
     * Create a new user
     * @param firstName
     * @param lastName
     * @param pin
     * @param theBank
     */
    public User(String firstName, String lastName, String  pin, Bank theBank) {

        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MD5 has, rather than original value, for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            throw new RuntimeException(e);
        }

        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.println("New user "+firstName+" "+lastName+" with ID "+this.uuid+" created.");
    }

    /**
     * Add an account for the user
     * @param anAcct    the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID
     * @return  the UUID
     */
    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            throw new RuntimeException(e);
        }

    }

    public String getFirstName() {

        return this.firstName;
    }
}
