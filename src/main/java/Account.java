import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by vlad.mocan on 29/08/2018.
 */
public class Account {

    private static final BigDecimal maximumSumForDeposit = BigDecimal.valueOf(2000000);
    private static final BigDecimal minimumAccountAllow = BigDecimal.valueOf(0);
    private static final BigDecimal zero = BigDecimal.ZERO;
    static Card cardClass;
    BigDecimal balance;
    String accountHolder;
    Card card;

    static void showBalance() {
        System.out.println("Your Balance is " + cardClass.insertedAccount.balance);
    }


    static void choseOption() throws IOException {
        System.out.println("Please Select Options");
        System.out.println("Press 1 to add");
        System.out.println("Press 2 to withdraw money");
        System.out.println("Press 3 to check the balance");
        BufferedReader stringReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = stringReader.readLine();

        switch (userInput) {

            case "1":
                addMoney();
                showBalance();
                choseOption();

            case "2":
                removeMoney();
                showBalance();
                choseOption();

            case "3":
                showBalance();
                choseOption();

            default:
                System.out.println("You are a retard");
                choseOption();

        }
    }

    private static void addMoney() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How much money you want to add?");
        BigDecimal userInputSum = new BigDecimal(reader.readLine());
        if(userInputSum.compareTo(zero) == -1) {
            System.out.println("You can't add negative sums");}
            else {
            if (userInputSum.compareTo(maximumSumForDeposit) == 1) {
                System.out.println("You can't deposit more than " + maximumSumForDeposit);

            } else {
                BigDecimal newBalance = cardClass.insertedAccount.balance.add(userInputSum);
                cardClass.insertedAccount.balance = newBalance;

            }
        }

    }

    private static void removeMoney() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How much money you want to withdraw?");
        BigDecimal userInputWithdraw = new BigDecimal(reader.readLine());
        if(userInputWithdraw.compareTo(zero) == -1){
            System.out.println("You can't withdraw a negative sum of money");
        } else {

            if (cardClass.insertedAccount.balance.subtract(userInputWithdraw).compareTo(minimumAccountAllow) < 0) {
                System.out.println("You can only withdraw " + cardClass.insertedAccount.balance);

            } else {
                BigDecimal newLowerBalance = cardClass.insertedAccount.balance.subtract(userInputWithdraw);
                cardClass.insertedAccount.balance = newLowerBalance;

            }
        }

    }

}
