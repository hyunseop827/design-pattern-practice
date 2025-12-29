package example.ex4.before;

class UserService {
    public void createUser(String name) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] createUser() start - param: " + name);

        System.out.println("Creating user in DB: " + name);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] createUser() end - elapsed: " + elapsed + "ms");
    }

    public String getUser(int id) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] getUser() start - param: " + id);

        String result = "User_" + id;

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] getUser() end - return: " + result + ", elapsed: " + elapsed + "ms");
        return result;
    }

    public void deleteUser(int id) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] deleteUser() start - param: " + id);

        System.out.println("Deleting user from DB: " + id);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] deleteUser() end - elapsed: " + elapsed + "ms");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Logging Code Mixed with Business Logic ===\n");

        UserService service = new UserService();

        service.createUser("John");
        System.out.println();

        String user = service.getUser(1);
        System.out.println("Query result: " + user);
        System.out.println();

        service.deleteUser(1);

        System.out.println("\n=== Problems ===");
        System.out.println("Logging code duplicated in every method");
        System.out.println("Business logic mixed with logging - poor readability");
        System.out.println("Changing log format requires modifying all methods");
    }
}
