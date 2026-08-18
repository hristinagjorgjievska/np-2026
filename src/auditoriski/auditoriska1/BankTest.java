package auditoriski.auditoriska1;

abstract class Account{
    private String holderName;
    private int accountNumber;
    private double balance;

    public Account(String holderName, int accountNumber, int balance) {
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //getBalance() е public за да може контролирано да се чита состојбата од надвор
    //(пример од auditoriski.auditoriska1.Bank), а balance е private за да не може никој да ја менува директно
    //и неконтролирано, секоја промена мора да помине низ addMoney / withdrawMoney

    public double getBalance(){
        return balance;
    }

    public void addMoney(double money){
        balance += money;
    }

    public void withdrawMoney(double money){
        balance -= money;
    }
}

interface InterestBearingAccount{
    void addInterest();
}

class Bank{
    private Account accounts[];
    private int numberAccounts;

    public Bank(int n) {
        accounts = new Account[n];
        numberAccounts = 0;
    }

    public void addAccount(Account account){
        accounts[numberAccounts] = account;
        numberAccounts += 1;
    }

    public double totalAssets(){
        double sum = 0;
        for (int i = 0; i < numberAccounts; i++){
            sum += accounts[i].getBalance();
        }

        return sum;
    }

    public void addInterest(){
        for (int i = 0; i < numberAccounts; i++) {
            if (accounts[i] instanceof InterestBearingAccount){
                InterestBearingAccount iba = (InterestBearingAccount) accounts[i];
                iba.addInterest();
            }
        }
    }
}

class InterestCheckingAccount extends Account implements InterestBearingAccount{

    public static final double interest = 0.03;

    public InterestCheckingAccount(String holderName, int accountNumber, int balance) {
        super(holderName, accountNumber, balance);
    }

    @Override
    public void addInterest() {
        double amount = getBalance() * interest;
        addMoney(amount);
    }
}

class PlatinumCheckingAccount extends InterestCheckingAccount {

    public PlatinumCheckingAccount(String holderName, int accountNumber, int balance) {
        super(holderName, accountNumber, balance);
    }

    @Override
    public void addInterest() {
        double amount = getBalance() * (interest * 2);
        addMoney(amount);
    }
}

class NonInterestCheckingAccount extends Account{

    public NonInterestCheckingAccount(String holderName, int accountNumber, int balance) {
        super(holderName, accountNumber, balance);
    }
}

public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank(10);

        bank.addAccount(new NonInterestCheckingAccount("Kiki", 1, 1000));
        bank.addAccount(new InterestCheckingAccount("Evica", 2, 1000));
        bank.addAccount(new PlatinumCheckingAccount("Ognen", 3, 1000));

        System.out.println("Before interest: " + bank.totalAssets());
        bank.addInterest();
        System.out.println("After interest: " + bank.totalAssets());
    }
}