package prac2.after;

// Component
interface Notification {
    void send(String message);
}

// ConcreteComponent - defines an object to which additional responsibilities can be attached
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// Decorator
abstract class OtherNotification implements Notification {
    protected Notification notification;
    public OtherNotification(Notification notification) {
        this.notification = notification;
    }
    @Override
    public void send(String message) {
        notification.send(message);
    }
}

// Concrete Decorator
class SMSNotification extends OtherNotification {
    public SMSNotification(Notification notification) {
        super(notification);
    }
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS: " + message);
    }
}

class FacebookNotification extends OtherNotification {
    public FacebookNotification(Notification notification) {
        super(notification);
    }
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Facebook: " + message);
    }
}

class SlackNotification extends OtherNotification {
    public SlackNotification(Notification notification) {
        super(notification);
    }
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Slack: " + message);
    }
}
public class Main {
    public static void main(String[] args) {
        // Send Email + SMS + Slack
        Notification notification = new SlackNotification(new SMSNotification(new EmailNotification()));
        notification.send("Hello World!");

    }
}