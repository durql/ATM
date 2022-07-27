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

        User curUser;
        while (true) {

            // stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank theBank, Scanner sc) {

        // inits
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID and pin until a correct one is reached
        do {

            System.out.printf("Welcome to ", theBank.getName());
            System.out.println("Enter user ID: ");
            userID = sc.nextLine();
            System.out.printf("Enter pin: ");
            pin = sc.nextLine();

            // try to get the user object corresping to the ID and pin
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.printf("Incorrect user ID or pin. Please try again.");
            }
        } while( authUser == null ); // continue looping until successful login

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {

        // print a summary of the user's accounts
        theUser.printAccountSummary();

        // init
        int choice;

        // user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?",
                    theUser.getFirstName());
            System.out.printf("  1) Show account transaction history");
            System.out.printf("  2) Withdrawl");
            System.out.printf("  3) Deposit");
            System.out.printf("  4) Transfer");
            System.out.printf("  5) Quit");
            System.out.printf("");
            System.out.printf("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        //process the choice
                switch (choice) {

                    case 1:
                        ATM.showTransHistory(theUser, sc);
                    case 2:
                        ATM.withdrawlFunds(theUser, sc);
                    case 3:
                        ATM.depositFunds(theUser, sc);
                    case 4:
                        ATM.transferFunds(theUser, sc);
                        break;
                }

                // redisplay menu unless user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }
}
