package example.ex1;

interface Command {
    void execute();
}

class Bird {
    public void sing() {
        System.out.println("Bird is singing");
    }
}

class FileNew {
    public void open() {
        System.out.println("File open");
    }
}

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


class Tv {
    public void power() {
        System.out.println("Tv Power On");
    }
}

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

class MenuItem {
    // 기존의 잘못된 예시
//    public void buttonPressed(Object o) {
//        if (o instanceof Bird) ((Bird)o).sing();
//        else if (o instanceof Tv) ((Tv)o).power();
//        else if (o instanceof FileNew) ((FileNew)o).open();
//    }

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
