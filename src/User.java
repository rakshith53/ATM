import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;


    public User(String firstName, String lastName, String pin, Bank theBank){
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
        }
        this.uuid = theBank.getNewUserUUID();
        this.accounts = new ArrayList<Account>();

        System.out.printf("New user %s %s with ID %s created.\n", lastName,firstName,this.uuid);
    }

    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    public String getUUID(){
        return this.uuid;
    }

    public boolean validatePin(String aPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        }
        catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void printAccountsSummary(){
        System.out.printf("\n\n%s's accounts summary", this.firstName);
        for(int i=0;i<this.accounts.size();i++){
            System.out.printf("%d) %s\n", (i+1),this.accounts.get(i).getSummaryLine());
        }
    }
    public int numAccounts(){
        return accounts.size();
    }

    public void printTransactionHistory(int acctIndx){
        this.accounts.get(acctIndx).printTransHistory();
    }

    public double getAcctBalance(int acctIndx){
        return this.accounts.get(acctIndx).getBalance();
    }

    public String getAcctUUID(int acctIndx){
        return this.accounts.get(acctIndx).getUUID();
    }

    public void addAcctTransaction(int acctIndx, double amount, String memo){
        this.accounts.get(acctIndx).addTransaction(amount,memo);
    }
}
