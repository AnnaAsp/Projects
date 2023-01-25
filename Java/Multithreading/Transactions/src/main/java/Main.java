import java.util.AbstractCollection;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account account = new Account("1234", 30000, bank);
        Account account1 = new Account("1235", 60000, bank);
        Account account3 = new Account("0987", 100000, bank);
        Account account4 = new Account("7890", 50000, bank);

            new Thread(()-> {
                try {
                    bank.transfer("1235", "1234", 60000);
                    System.out.println(account.getMoney());
                    System.out.println(account1.getMoney());
                    System.out.println(bank.getSumAllAccounts());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            Thread.sleep(5000);

            new Thread(() -> {
                try {
                    bank.transfer("1234", "1235", 20000);
                    System.out.println(account.getMoney());
                    System.out.println(account1.getMoney());
                    System.out.println(bank.getSumAllAccounts());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        new Thread(() -> {
            try {
                bank.transfer("0987", "7890", 20000);
                System.out.println(account3.getMoney());
                System.out.println(account4.getMoney());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                bank.transfer("7890", "0987", 10000);
                System.out.println(account3.getMoney());
                System.out.println(account4.getMoney());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


    }
}
