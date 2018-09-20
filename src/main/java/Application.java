

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad.mocan on 29/08/2018.
 */
public class Application {

    public static Account insertedAccount = null;
    private static List<Account> listOfAccounts = new ArrayList<>();
    private static BigDecimal maximumSumForDeposit = BigDecimal.valueOf(2000000);
    private static BigDecimal minimumAccountAllow = BigDecimal.valueOf(0);


    public static void main(String[] args) throws IOException {

        generateAccounts();
        userLogin();
    }

    private static void generateAccounts() {

        Account account1 = new Account();
        Card card1 = new Card();
        account1.accountHolder = "Bere Grils";
        account1.balance = BigDecimal.valueOf(Double.valueOf("60000"));
        account1.card = card1;
        card1.cardNumber = "100001";
        card1.pinNumber = "1245";
        card1.cardLocked = false;
        card1.numberOfApptents = 0;


        Account account2 = new Account();
        Card card2 = new Card();
        account2.accountHolder = "Lefter Shomer";
        account2.balance = BigDecimal.valueOf(Double.valueOf("0"));
        account2.card = card2;
        account2.card.cardNumber = "100002";
        account2.card.pinNumber = "1246";
        card2.cardLocked = false;
        card2.numberOfApptents = 0;


        Account account3 = new Account();
        Card card3 = new Card();
        account3.accountHolder = "Richiy Rich";
        account3.balance = BigDecimal.valueOf(Double.valueOf("999243"));
        account3.card = card3;
        account3.card.cardNumber = "100003";
        account3.card.pinNumber = "0000";
        card3.cardLocked = false;
        card3.numberOfApptents = 0;

        Account account4 = new Account();
        Card card4 = new Card();
        account4.accountHolder = "Blocked Card";
        account4.balance = BigDecimal.valueOf(Double.valueOf("8239922"));
        account4.card = card4;
        account4.card.cardNumber = "343";
        account4.card.pinNumber = "0005";
        card4.cardLocked = true;
        card4.numberOfApptents = 0;

        listOfAccounts.add(0, account1);
        listOfAccounts.add(1, account2);
        listOfAccounts.add(2, account3);
        listOfAccounts.add(3, account4);

    }


    private static void userLogin() throws IOException {
        System.out.println("Welcome to Virtual Bank");
        System.out.println("Please Insert Your Card");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInputCardNumber = reader.readLine();
        searchIfTheCardExists(userInputCardNumber);
        checkIfTheCardIsValid();
        checkIfThePinIsValid();
        addOrRemoveMoney();

    }

    private static Account searchIfTheCardExists(String userInput) {
        for (Account account : listOfAccounts) {
            if (userInput.equalsIgnoreCase(account.card.cardNumber)) {
                insertedAccount = account;

            }
        }
        return insertedAccount;
    }

    private static void checkIfTheCardIsValid() throws IOException {
        if (insertedAccount != null && insertedAccount.card.cardLocked == false) {
            System.out.println("Valid Card");

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
            showBalance();
        } else {
            System.out.println("Incorrect Pin");
            insertedAccount.card.numberOfApptents = insertedAccount.card.numberOfApptents + 1;
            if (insertedAccount.card.numberOfApptents < 3) {
                System.out.println("You have enter your card wrong " +insertedAccount.card.numberOfApptents + " times");
                userLogin();
            }else {
                {insertedAccount.card.cardLocked = true;}
                userLogin();
            }

        }
    }

    private static void showBalance() {
        System.out.println("Your Balance is " + insertedAccount.balance);
    }


    private static void addOrRemoveMoney() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please Select Options");
        System.out.println("Press 1 to add");
        System.out.println("Press 2 to withdraw money");
        System.out.println("Press 3 to check the balance");
        BufferedReader stringReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = stringReader.readLine();

        switch (userInput) {
            case "1":
                System.out.println("How much money you want to add?");
                BigDecimal userInputSum = new BigDecimal(reader.readLine());
                if (userInputSum.compareTo(maximumSumForDeposit) == 1) {
                    System.out.println("You can't deposit more than " + maximumSumForDeposit);
                    showBalance();
                    addOrRemoveMoney();
                } else {
                    BigDecimal newBalance = insertedAccount.balance.add(userInputSum);
                    insertedAccount.balance = newBalance;
                    showBalance();
                    addOrRemoveMoney();
                }


            case "2":
                System.out.println("How much money you want to withdraw?");
                BigDecimal userInputWitdraw = new BigDecimal(reader.readLine());
                if (insertedAccount.balance.subtract(userInputWitdraw).compareTo(minimumAccountAllow) < 0) {
                    System.out.println("You can only withdraw " + insertedAccount.balance);
                    addOrRemoveMoney();

                } else {
                    BigDecimal newLowerBalance = insertedAccount.balance.subtract(userInputWitdraw);
                    insertedAccount.balance = newLowerBalance;
                    showBalance();
                    addOrRemoveMoney();
                }


            case "3":
                showBalance();

            default:
                System.out.println("You are a retard");
                System.out.println("");
                addOrRemoveMoney();
        }


    }

}
