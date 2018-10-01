import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vlad.mocan on 29/08/2018.
 */
public class Card {

    String cardNumber;
    String pinNumber;
    Integer numberOfApptents;
    Boolean cardLocked;
    static Account insertedAccount = null;
    private static Account  account;
    private static Application application;




    public static void userLogin() throws IOException {
        System.out.println("Welcome to Virtual Bank");
        System.out.println("Please Insert Your Card");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInputCardNumber = reader.readLine();
        searchIfTheCardExists(userInputCardNumber);
        checkIfTheCardIsValid();
        account.choseOption();


    }

    private static Account searchIfTheCardExists(String userInput) {
        for (Account account : application.listOfAccounts) {
            if (userInput.equalsIgnoreCase(account.card.cardNumber)) {
                insertedAccount = account;

            }
        }
        return insertedAccount;
    }

    private static void checkIfTheCardIsValid() throws IOException {
        if (insertedAccount != null && insertedAccount.card.cardLocked == false) {
            System.out.println("Valid Card");
            checkIfThePinIsValid();
        } else {
            System.out.println("Invalid Card");
            userLogin();
        }
    }

    private static void checkIfThePinIsValid() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please insert your PIN");
        String userInputPinNumber = reader.readLine();
        if (userInputPinNumber.equalsIgnoreCase(insertedAccount.card.pinNumber)) {
            insertedAccount.card.numberOfApptents = 0;
            System.out.println("Correct Pin");
            System.out.println("Welcome " + insertedAccount.accountHolder);
            account.showBalance();
        } else {
            System.out.println("Incorrect Pin");
            insertedAccount.card.numberOfApptents = insertedAccount.card.numberOfApptents + 1;
            if (insertedAccount.card.numberOfApptents < 3) {
                System.out.println("You have enter your card wrong " +insertedAccount.card.numberOfApptents + " times");
            }else {
                insertedAccount.card.cardLocked = true;
            }
            userLogin();

        }
    }


}
