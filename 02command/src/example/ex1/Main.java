package example.ex1;

// Command - declares an interface for executing an operation
interface Command {
    void execute();
}

// Receiver - knows how to perform the operations
class Bird {
    public void sing() {
        System.out.println("Bird is singing");
    }
}

// Receiver
class FileNew {
    public void open() {
        System.out.println("File open");
    }
}

// ConcreteCommand - binds a Receiver object to an action
class BirdSingCmd implements Command {
    private final Bird bird;

    public BirdSingCmd(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void execute() {
        bird.sing();
    }
}

// ConcreteCommand
class FileNewCmd implements Command {
    private final FileNew f;

    public FileNewCmd(FileNew f) {
        this.f = f;
    }

    @Override
    public void execute() {
        f.open();
    }
}


// Receiver
class Tv {
    public void power() {
        System.out.println("Tv Power On");
    }
}

// ConcreteCommand
class TvPwrCmd implements Command {
    private final Tv tv;

    public TvPwrCmd(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.power();
    }
}

// Invoker - asks the command to carry out the request
class MenuItem {
    public void buttonPressed(Command cmd) {
        cmd.execute();
    }
}


public class Main {
    public static void main(String[] args) {
        MenuItem m = new MenuItem();
        m.buttonPressed(new BirdSingCmd(new Bird()));
    }
}
