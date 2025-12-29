package example.ex4.after;

// Logging Proxy

// Subject
interface UserService {
    void createUser(String name);
    String getUser(int id);
    void deleteUser(int id);
}

// Real Subject
class RealUserService implements UserService {
    @Override
    public void createUser(String name) {
        System.out.println("Creating user in DB: " + name);
    }

    @Override
    public String getUser(int id) {
        return "User_" + id;
    }

    @Override
    public void deleteUser(int id) {
        System.out.println("Deleting user from DB: " + id);
    }
}

// Proxy
class LoggingProxy implements UserService {
    private RealUserService realService;

    public LoggingProxy() {
        this.realService = new RealUserService();
    }

    @Override
    public void createUser(String name) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] createUser() start - param: " + name);

        realService.createUser(name);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] createUser() end - elapsed: " + elapsed + "ms");
    }

    @Override
    public String getUser(int id) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] getUser() start - param: " + id);

        String result = realService.getUser(id);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] getUser() end - return: " + result + ", elapsed: " + elapsed + "ms");
        return result;
    }

    @Override
    public void deleteUser(int id) {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] deleteUser() start - param: " + id);

        realService.deleteUser(id);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] deleteUser() end - elapsed: " + elapsed + "ms");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Logging Proxy for Method Tracing ===\n");

        UserService service = new LoggingProxy();

        service.createUser("John");
        System.out.println();

        String user = service.getUser(1);
        System.out.println("Query result: " + user);
        System.out.println();

        service.deleteUser(1);

        System.out.println("\n=== Improvements ===");
        System.out.println("Added logging without modifying business logic");
        System.out.println("Spring AOP @Around works this way");
    }
}
