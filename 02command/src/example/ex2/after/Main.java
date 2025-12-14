package example.ex2.after;

// Command - declares an interface for executing an operation
interface Command {
    void execute();
}

// Receiver - knows how to perform the operations
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

// ConcreteCommand - binds a Receiver object to an action (On/Off combined as toggle)
class TvPowerCommand implements Command {
    private final TV tv;
    public TvPowerCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.power(); }
}

// ConcreteCommand
class TvMuteCommand implements Command {
    private final TV tv;
    public TvMuteCommand(TV tv) { this.tv = tv; }

    @Override
    public void execute() { tv.mute(); }
}

// Invoker - asks the command to carry out the request
class TwoButtonController {
    private Command button1Command;
    private Command button2Command;

    // Set button functions via constructor
    public TwoButtonController(Command button1Command, Command button2Command) {
        this.button1Command = button1Command;
        this.button2Command = button2Command;
    }

    // Methods to change button functions later if needed
    public void setButton1Command(Command command) { this.button1Command = command; }
    public void setButton2Command(Command command) { this.button2Command = command; }

    public void button1Pressed() {
        button1Command.execute();
    }

    public void button2Pressed() {
        button2Command.execute();
    }
}

// Client - creates ConcreteCommand objects and sets their receivers
public class Main {
    public static void main(String[] args) {
        TV tv = new TV();

        // Create command objects (toggle behavior, so single command per action)
        Command powerCmd = new TvPowerCommand(tv);
        Command muteCmd = new TvMuteCommand(tv);

        // Map button1: power, button2: mute
        TwoButtonController rc = new TwoButtonController(powerCmd, muteCmd);

        rc.button1Pressed(); // Power On
        rc.button2Pressed(); // Mute On
        rc.button2Pressed(); // Mute Off
        rc.button1Pressed(); // Power Off
    }
}
