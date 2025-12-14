package prac2.before;

class TCPConnection {
    public void execute(String data) {
        System.out.println("Logging in...");

        // TCP requires security, so encryption is performed
        System.out.println("Encrypting Data: " + data);

        System.out.println("Sending via TCP: " + data);
        System.out.println("Saving result to DB...");
        System.out.println("Logging out...");
    }
}

class UDPConnection {
    public void execute(String data) {
        System.out.println("Logging in...");

        // UDP has no encryption (direct transmission)

        System.out.println("Sending via UDP: " + data);
        System.out.println("Saving result to DB...");
        System.out.println("Logging out...");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- TCP Process ---");
        TCPConnection tcp = new TCPConnection();
        tcp.execute("Sensitive Data");

        System.out.println("\n--- UDP Process ---");
        UDPConnection udp = new UDPConnection();
        udp.execute("Video Stream Data");
    }
}
