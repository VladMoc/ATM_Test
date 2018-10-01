
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad.mocan on 29/08/2018.
 */
public class Application {


    private static Card card;
    static List<Account> listOfAccounts = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        generateAccounts();
        card.userLogin();


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


}
