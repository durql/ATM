import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        // init Scanner
        Scanner sc = new Scanner(System.in);

        // init Bank
        Bank theBank = new Bank("National Bank of Murzyn State");

        // add a user, which also creating a saving account
        User aUser = theBank.addUser("Sebastian", "Bąk", "1234");

        // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

    }
}
