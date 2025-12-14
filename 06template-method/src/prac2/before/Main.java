package prac2.before;

class TCPConnection {
    public void execute(String data) {
        System.out.println("Logging in...");

        // TCP는 보안이 필요해서 암호화 과정을 거침
        System.out.println("Encrypting Data: " + data);

        System.out.println("Sending via TCP: " + data);
        System.out.println("Saving result to DB...");
        System.out.println("Logging out...");
    }
}

class UDPConnection {
    public void execute(String data) {
        System.out.println("Logging in...");

        // UDP는 암호화 과정 없음 (바로 전송)

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