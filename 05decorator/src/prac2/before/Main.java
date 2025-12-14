package prac2.before;

class Notifier {
    private boolean sendSMS;
    private boolean sendFacebook;
    private boolean sendSlack;

    public Notifier(boolean sendSMS, boolean sendFacebook, boolean sendSlack) {
        this.sendSMS = sendSMS;
        this.sendFacebook = sendFacebook;
        this.sendSlack = sendSlack;
    }

    public void send(String message) {
        // Basic Email (Default)
        System.out.println("Sending Email: " + message);

        if (sendSMS) {
            System.out.println("Sending SMS: " + message);
        }
        if (sendFacebook) {
            System.out.println("Sending Facebook: " + message);
        }
        if (sendSlack) {
            System.out.println("Sending Slack: " + message);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Send Email + SMS + Slack
        Notifier notifier = new Notifier(true, false, true);
        notifier.send("Critical Error Occurred!");
    }
}