package example.ex3;

// Command - declares an interface for executing an operation
interface Command {
    void execute();
}

// Receiver - knows how to perform the operations
class Light {
    public void on()  { System.out.println("Light On"); }
    public void off() { System.out.println("Light Off"); }
}

// Receiver
class TV {
    public void on()  { System.out.println("TV On"); }
    public void off() { System.out.println("TV Off"); }
}

// ConcreteCommand - binds a Receiver object to an action
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.on(); }
}

// ConcreteCommand
class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.off(); }
}

// ConcreteCommand
class TvOnCommand implements Command {
    private final TV tv;
    public TvOnCommand(TV tv) { this.tv = tv; }
    @Override
    public void execute() { tv.on(); }
}

// ConcreteCommand
class TvOffCommand implements Command {
    private final TV tv;
    public TvOffCommand(TV tv) { this.tv = tv; }
    @Override
    public void execute() { tv.off(); }
}

// Invoker - asks the command to carry out the request
class RemoteControl {
    private Command button1;
    private Command button2;

    public void setButton1(Command cmd) { this.button1 = cmd; }
    public void setButton2(Command cmd) { this.button2 = cmd; }

    public void pressButton1() { if (button1 != null) button1.execute(); }
    public void pressButton2() { if (button2 != null) button2.execute(); }
}

// Client - creates ConcreteCommand objects and sets their receivers
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
