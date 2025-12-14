package example.ex2.before;
class TwoButtonController {

    private TV tv;
    public TwoButtonController(TV tv) { this.tv = tv;}
    public void button1Pressed() { tv.power();}
    public void button2Pressed() { tv.mute();}
}

class TV {
    private boolean powerOn = false;
    private boolean muteOn = false;

    public void power() {
        powerOn = !powerOn;
        if (powerOn) System.out.println("Power On");
        else
            System.out.println("Power Off");
    }

    public void mute() {
        if (!powerOn)
            return;
        muteOn = !muteOn;
        if (muteOn) System.out.println("Mute On");
        else
            System.out.println("Mute Off");
    }
}
public class Main {
    public static void main(String[] args) {
        TV tv = new TV();
        TwoButtonController rc = new TwoButtonController(tv);
        rc.button1Pressed();
        rc.button2Pressed();
        rc.button1Pressed();
        rc.button2Pressed();
    }
}