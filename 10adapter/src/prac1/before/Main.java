package prac1.before;

// Target Interface (Standard)
interface Logger {
    void log(String message);
}

// Adaptee (3rd Party Library) - 수정 불가
class FancyLogLibrary {
    public void mark(String msg) {
        System.out.println("[Fancy MARK] *** " + msg + " ***");
    }

    public void display(String msg) {
        System.out.println("[Fancy DISPLAY] >>> " + msg + " <<<");
    }
}

// Client
class Application {
    private Logger logger;

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
        // 1. 기존 방식 (익명 클래스로 간단 구현 예시)
        Logger standardLogger = new Logger() {
            @Override
            public void log(String message) {
                System.out.println("Standard: " + message);
            }
        };

        Application app1 = new Application(standardLogger);
        System.out.println("--- Standard Logger ---");
        app1.runProcess();

        // 2. FancyLogLibrary를 사용하고 싶음
        FancyLogLibrary fancyLib = new FancyLogLibrary();

        // TODO: Adapter를 사용하여 fancyLib를 Application에서 사용할 수 있게 만드세요.
        // Logger adapter = new ...

        // System.out.println("\n--- Fancy Logger (via Adapter) ---");
        // Application app2 = new Application(adapter);
        // app2.runProcess();
    }
}