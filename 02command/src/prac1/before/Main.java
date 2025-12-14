package prac1.before;

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

class RemoteControl {
    private Light light;
    private Stereo stereo;

    public RemoteControl(Light light, Stereo stereo) {
        this.light = light;
        this.stereo = stereo;
    }

    public void buttonPressed(String slot) {
        if (slot.equals("LIGHT_ON")) {
            light.on();
        } else if (slot.equals("LIGHT_OFF")) {
            light.off();
        } else if (slot.equals("STEREO_ON_WITH_CD")) {
            stereo.on();
            stereo.setCD();
            stereo.setVolume(11);
        } else if (slot.equals("STEREO_OFF")) {
            stereo.off();
        } else {
            System.out.println("No function assigned");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Stereo stereo = new Stereo();

        RemoteControl remote = new RemoteControl(light, stereo);

        remote.buttonPressed("LIGHT_ON");
        remote.buttonPressed("STEREO_ON_WITH_CD");
        remote.buttonPressed("LIGHT_OFF");
    }
}