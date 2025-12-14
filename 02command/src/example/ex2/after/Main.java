package example.ex2.after;

// 1. Command 인터페이스 (유지)
interface Command {
    void execute();
}

// 2. Receiver (TV) - 변경 없음
class TV {
    private boolean powerOn = false;
    private boolean muteOn = false;

    public void power() {
        powerOn = !powerOn;
        System.out.println(powerOn ? "Power On" : "Power Off");
    }

    public void mute() {
        if (!powerOn) return;
        muteOn = !muteOn;
        System.out.println(muteOn ? "Mute On" : "Mute Off");
    }
}

// 3. ConcreteCommand (중복 제거: On/Off 합침)
class TvPowerCommand implements Command {
    private final TV tv;
    public TvPowerCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.power(); }
}

class TvMuteCommand implements Command {
    private final TV tv;
    public TvMuteCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.mute(); }
}

// 4. Invoker (TwoButton의 의미를 살림)
class TwoButtonController {
    private Command button1Command;
    private Command button2Command;

    // 생성자에서 버튼 2개의 기능을 각각 설정
    public TwoButtonController(Command button1Command, Command button2Command) {
        this.button1Command = button1Command;
        this.button2Command = button2Command;
    }

    // 필요하다면 나중에 버튼 기능을 바꿀 수 있는 메서드 추가 가능
    public void setButton1Command(Command command) { this.button1Command = command; }
    public void setButton2Command(Command command) { this.button2Command = command; }

    public void button1Pressed() {
        button1Command.execute();
    }

    public void button2Pressed() {
        button2Command.execute();
    }
}

// 5. Client
public class Main {
    public static void main(String[] args) {
        TV tv = new TV();

        // 커맨드 객체 생성 (On/Off 구분 없이 동작 자체가 토글이므로 하나만 있으면 됨)
        Command powerCmd = new TvPowerCommand(tv);
        Command muteCmd = new TvMuteCommand(tv);

        // 버튼1: 전원, 버튼2: 음소거로 매핑
        TwoButtonController rc = new TwoButtonController(powerCmd, muteCmd);

        rc.button1Pressed(); // Power On
        rc.button2Pressed(); // Mute On
        rc.button2Pressed(); // Mute Off
        rc.button1Pressed(); // Power Off
    }
}