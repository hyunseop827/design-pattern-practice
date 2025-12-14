package prac2.after;

abstract class Connection {
    // 템플릿 메서드
    public final void execute(String data) {
        System.out.println("Logging in...");

        // [핵심] Hook 메서드를 통해 '선택적'으로 암호화 수행
        if (isSecure()) {
            System.out.println("Encrypting Data: " + data);
        }

        // 실제 전송은 하위 클래스에게 위임
        send(data);

        System.out.println("Saving result to DB...");
        System.out.println("Logging out...");
    }

    // [Primitive Operation] 하위 클래스가 반드시 구현해야 함
    protected abstract void send(String data);

    // [Hook Method] 하위 클래스가 선택적으로 오버라이딩 (기본값: false)
    protected boolean isSecure() {
        return false;
    }
}

class TCPConnection extends Connection {
    @Override
    protected void send(String data) {
        System.out.println("Sending via TCP: " + data);
    }

    // TCP는 보안이 필요하므로 Hook을 오버라이딩하여 true 반환
    @Override
    protected boolean isSecure() {
        return true;
    }
}

class UDPConnection extends Connection {
    @Override
    protected void send(String data) {
        System.out.println("Sending via UDP: " + data);
    }
    // UDP는 보안이 필요 없으므로 Hook을 건드리지 않음 (기본값 false 사용)
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