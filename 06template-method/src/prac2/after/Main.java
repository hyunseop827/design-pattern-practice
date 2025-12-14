package prac2.after;

// AbstractClass - defines abstract primitive operations that concrete subclasses define
abstract class Connection {
    // Template Method - defines the skeleton of an algorithm
    public final void execute(String data) {
        System.out.println("Logging in...");

        // Hook method allows optional encryption
        if (isSecure()) {
            System.out.println("Encrypting Data: " + data);
        }

        // Delegate actual transmission to subclass
        send(data);

        System.out.println("Saving result to DB...");
        System.out.println("Logging out...");
    }

    // Primitive Operation - subclasses must implement
    protected abstract void send(String data);

    // Hook Method - subclasses can optionally override (default: false)
    protected boolean isSecure() {
        return false;
    }
}

// ConcreteClass - implements the primitive operations to carry out subclass-specific steps
class TCPConnection extends Connection {
    @Override
    protected void send(String data) {
        System.out.println("Sending via TCP: " + data);
    }

    // TCP requires security, so override Hook to return true
    @Override
    protected boolean isSecure() {
        return true;
    }
}

// ConcreteClass
class UDPConnection extends Connection {
    @Override
    protected void send(String data) {
        System.out.println("Sending via UDP: " + data);
    }
    // UDP doesn't need security, so use default Hook value (false)
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- TCP Process ---");
        Connection tcp = new TCPConnection();
        tcp.execute("Sensitive Data");

        System.out.println("\n--- UDP Process ---");
        Connection udp = new UDPConnection();
        udp.execute("Video Stream Data");
    }
}
