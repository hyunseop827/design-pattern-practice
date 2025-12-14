package prac1.after;

// Command
interface Command {
    void execute();
    // void undo();
}

// Concrete Command
class LightOn implements Command {
    private Light light;

    public LightOn(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

class LightOff implements Command {
    private Light light;

    public LightOff(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}

class StereoOn implements Command {
    private Stereo stereo;

    public StereoOn(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        System.out.println("Stereo is on");
    }
}

class StereoSetCD implements Command {
    private Stereo stereo;

    public StereoSetCD(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.setCD();
    }
}

class StereoSetVolume implements Command {
    private Stereo stereo;
    private int volume;

    public StereoSetVolume(Stereo stereo, int volume) {
        this.stereo = stereo;
        this.volume = volume;
    }

    @Override
    public void execute() {
        stereo.setVolume(volume);
    }
}

class StereoOff implements Command {
    private Stereo stereo;

    public StereoOff(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}

// Receiver
class Light {
    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}

class Stereo {
    public void on() {
        System.out.println("Stereo is on");
    }

    public void setCD() {
        System.out.println("Stereo is set for CD input");
    }

    public void setVolume(int volume) {
        System.out.println("Stereo volume set to " + volume);
    }

    public void off() {
        System.out.println("Stereo is off");
    }
}

// Invoker
class RemoteControl {

    public void buttonPressed(Command command) {
        command.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Stereo stereo = new Stereo();

        RemoteControl remote = new RemoteControl();

        LightOn lightOn = new LightOn(light);
        LightOff lightOff = new LightOff(light);

        StereoOn  stereoOn = new StereoOn(stereo);
        StereoSetCD stereoSetCD = new StereoSetCD(stereo);

        StereoSetVolume stereoSetVolume = new StereoSetVolume(stereo,11);

        StereoOff stereoOff = new StereoOff(stereo);

        remote.buttonPressed(lightOn);
        remote.buttonPressed(stereoOn);
        remote.buttonPressed(stereoSetCD);
        remote.buttonPressed(stereoSetVolume);
        remote.buttonPressed(stereoOff);
        remote.buttonPressed(lightOff);

    }
}