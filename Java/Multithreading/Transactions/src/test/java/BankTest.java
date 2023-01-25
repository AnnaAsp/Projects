import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BankTest extends TestCase {

    Bank bank;
    Account account1;
    Account account2;

    @Override
    protected void setUp() throws Exception {
        bank = new Bank();
        account1 = new Account("1234", 100000, bank);
        account2 = new Account("4321", 100000, bank);
    }

    @Test
    public void testTransfer() {
        new Thread(()-> {
            try {
                bank.transfer("1234", "4321", 60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()-> {
            try {
                bank.transfer("4321", "1234", 60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        for (int i = 0; i < 10; i++) {
        new Thread(()-> {
            try {
                bank.transfer("4321", "1234", 10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    bank.transfer("1234", "4321", 20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long actual = bank.getSumAllAccounts();
        long expeсted = 200000;
        assertEquals(expeсted, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
