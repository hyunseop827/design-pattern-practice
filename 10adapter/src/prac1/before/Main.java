package prac1.before;

// Target - defines the domain-specific interface that Client uses
interface Logger {
    void log(String message);
}

// Adaptee (3rd Party Library) - cannot be modified
class FancyLogLibrary {
    public void mark(String msg) {
        System.out.println("[Fancy MARK] *** " + msg + " ***");
    }

    public void display(String msg) {
        System.out.println("[Fancy DISPLAY] >>> " + msg + " <<<");
    }
}

// Client - collaborates with objects conforming to the Target interface
class Application {
    private final Logger logger;

    public Application(Logger logger) {
        this.logger = logger;
    }

    public void runProcess() {
        logger.log("Process Started");
        logger.log("Process Finished");
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Standard approach (simple anonymous class implementation)
        Logger standardLogger = message -> System.out.println("Standard: " + message);

        Application app1 = new Application(standardLogger);
        System.out.println("--- Standard Logger ---");
        app1.runProcess();

        // 2. Want to use FancyLogLibrary
        FancyLogLibrary fancyLib = new FancyLogLibrary();

        // TODO: Create an Adapter to use fancyLib in Application.
        // Logger adapter = new ...

        // System.out.println("\n--- Fancy Logger (via Adapter) ---");
        // Application app2 = new Application(adapter);
        // app2.runProcess();
    }
}
