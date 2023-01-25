import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Bank {
    private Map<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        boolean fraud = true;
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        Account lowSyncAccount;
        Account topSyncAccount;
        topSyncAccount = (fromAccount.compareTo(toAccount) > 0) ? fromAccount : toAccount;
        lowSyncAccount = (fromAccount.compareTo(toAccount) < 0) ? fromAccount : toAccount;
        if (amount > 50000) {
            fraud = isFraud(fromAccountNum, toAccountNum, amount);
            if (fraud) {
                synchronized(lowSyncAccount) {
                    synchronized (topSyncAccount) {
                        while (true) {
                            transfer(fromAccountNum, toAccountNum, 0);
                        }
                    }
                }
            } else if (!fraud && fromAccount.getMoney() >= amount) {
                synchronized(lowSyncAccount) {
                    synchronized (topSyncAccount) {
                        fromAccount.setMoney(fromAccount.getMoney() - amount);
                        toAccount.setMoney(toAccount.getMoney() + amount);
                    }
                }
            }
        } else if (amount <= 50000 && fromAccount.getMoney() >= amount) {
            synchronized(lowSyncAccount) {
                synchronized (topSyncAccount) {
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                    toAccount.setMoney(toAccount.getMoney() + amount);
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        long balance = accounts.get(accountNum).getMoney();
        return balance;
    }

    public long getSumAllAccounts() {
        long sum = 0;
        Set<String> keys = accounts.keySet();
        for (String key : keys) {
            sum += accounts.get(key).getMoney();
        }
        return sum;
    }

    public Map getAccounts() {
        return accounts;
    }
}
