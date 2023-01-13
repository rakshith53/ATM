import javax.sql.rowset.spi.TransactionalWriter;
import java.util.ArrayList;

public class Account {
    private String name;
    private double balance;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank){
        this.name = name;
        this.holder = holder;

        this.uuid = theBank.getNewAccountUUID();
    }
}
