package prac2.before;

class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. Balance: " + balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println(amount + " withdrawn. Balance: " + balance);
    }

    public void transfer(String toAccount, double amount) {
        balance -= amount;
        System.out.println(amount + " transferred to " + toAccount + ". Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }
}

class User {
    private String name;
    private boolean isAuthenticated;

    public User(String name, boolean isAuthenticated) {
        this.name = name;
        this.isAuthenticated = isAuthenticated;
    }

    public String getName() {
        return name;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bank App ===\n");

        BankAccount account = new BankAccount("1234-5678", "John", 1000000);

        User owner = new User("John", true);
        User hacker = new User("Hacker", false);
        User stranger = new User("Stranger", true);

        System.out.println("[" + hacker.getName() + "] Account access:");
        account.withdraw(500000);

        System.out.println("\n[" + stranger.getName() + "] Account access:");
        account.transfer("9999-9999", 300000);

        System.out.println("\n=== Problems ===");
        System.out.println("Unauthenticated hacker withdrew successfully");
        System.out.println("Non-owner transferred successfully");
        System.out.println("No security check logic!");
    }
}
