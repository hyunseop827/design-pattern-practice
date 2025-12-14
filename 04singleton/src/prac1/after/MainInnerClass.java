package prac1.after;

class DatabaseConnectionInnerClass {
    private static DatabaseConnectionInnerClass instance;

    private DatabaseConnectionInnerClass() {
        System.out.println("Database Connected!");
    }

    public static DatabaseConnectionInnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final DatabaseConnectionInnerClass INSTANCE =
                new DatabaseConnectionInnerClass();
    }

    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
}

public class MainInnerClass {
    public static void main(String[] args) {
        Runnable task = () -> {
            DatabaseConnectionInnerClass db = DatabaseConnectionInnerClass.getInstance();
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
