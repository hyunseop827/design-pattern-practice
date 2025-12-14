package example.ex3;

// 1. 공통 명령 인터페이스
interface Command {
    void execute();
}

// 2. 실제 장치들 (수신자)
class Light {
    public void on()  { System.out.println("Light On"); }
    public void off() { System.out.println("Light Off"); }
}

class TV {
    public void on()  { System.out.println("TV On"); }
    public void off() { System.out.println("TV Off"); }
}

// 3. 구체 커맨드 객체들
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.on(); }
}

class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.off(); }
}

class TvOnCommand implements Command {
    private final TV tv;
    public TvOnCommand(TV tv) { this.tv = tv; }
    @Override
    public void execute() { tv.on(); }
}

class TvOffCommand implements Command {
    private final TV tv;
    public TvOffCommand(TV tv) { this.tv = tv; }
    @Override
    public void execute() { tv.off(); }
}

// 4. 리모컨(Invoker) – 버튼에 Command를 꽂아 쓰는 역할
class RemoteControl {
    private Command button1;
    private Command button2;

    public void setButton1(Command cmd) { this.button1 = cmd; }
    public void setButton2(Command cmd) { this.button2 = cmd; }

    public void pressButton1() { if (button1 != null) button1.execute(); }
    public void pressButton2() { if (button2 != null) button2.execute(); }
}

// 5. Client – 어떤 커맨드를 어느 버튼에 꽂을지 조립
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        TV tv = new TV();

        Command lightOn  = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command tvOn     = new TvOnCommand(tv);
        Command tvOff    = new TvOffCommand(tv);

        RemoteControl rc = new RemoteControl();
        rc.setButton1(lightOn);
        rc.setButton2(tvOn);

        rc.pressButton1(); // Light On
        rc.pressButton2(); // TV On

        rc.setButton1(lightOff);
        rc.setButton2(tvOff);

        rc.pressButton1(); // Light Off
        rc.pressButton2(); // TV Off
    }
}
