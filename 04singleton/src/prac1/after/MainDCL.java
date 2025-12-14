package prac1.after;

class DatabaseConnectionDCL {

    // Important: volatile keyword is required! (visibility and reordering prevention)
    private volatile static DatabaseConnectionDCL instance;

    private DatabaseConnectionDCL() {
        System.out.println("Database Connected!");
    }

    // Double Checking Locking
    public static DatabaseConnectionDCL getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionDCL.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionDCL();
                }
            }
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
}

public class MainDCL {
    public static void main(String[] args) {
        Runnable task = () -> {
            DatabaseConnectionDCL db = DatabaseConnectionDCL.getInstance();
            System.out.println("Instance: " + db);
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }
}
