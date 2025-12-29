package prac2.after;

// Protection Proxy

// Subject
interface AccountInterface {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(String toAccount, double amount);
}

// Real Subject
class RealAccount implements AccountInterface {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public RealAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
        System.out.println(amount + " withdrawn. Balance: " + balance);
    }
    @Override
    public void transfer(String toAccount, double amount) {
        balance -= amount;
        System.out.println(amount + " transferred to " + toAccount + ". Balance: " + balance);
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

// Proxy
class ProxyAccount implements AccountInterface {
    private final RealAccount bankAccount;
    private final User user;

    public ProxyAccount(RealAccount bankAccount, User user) {
        this.bankAccount = bankAccount;
        this.user = user;
    }

    @Override
    public void deposit(double amount) {
        if (isAuthenticated()){
            bankAccount.deposit(amount);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (isAuthenticated()){
            bankAccount.withdraw(amount);
        }
    }

    @Override
    public void transfer(String toAccount, double amount) {
        if (isAuthenticated()){
            bankAccount.transfer(toAccount, amount);
        }
    }
    
    private boolean isAuthenticated(){
        return user.isAuthenticated();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bank App ===\n");

        RealAccount realAccount = new RealAccount("1234-5678", "John", 1000000);

        User owner = new User("John", true);
        User hacker = new User("Hacker", false);
        User stranger = new User("Stranger", true);

        System.out.println("[" + hacker.getName() + "] Account access:");
        AccountInterface hackerAccount = new ProxyAccount(realAccount, hacker);
        hackerAccount.withdraw(500000);

        System.out.println("\n[" + owner.getName() + "] Account access:");
        AccountInterface ownerAccount = new ProxyAccount(realAccount, owner);
        ownerAccount.withdraw(500000);
    }
}
