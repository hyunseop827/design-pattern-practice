package prac1.after;

// AbstractProduct - declares an interface for a type of product object
interface Button {
    void paint();
}

// AbstractProduct
interface Checkbox {
    void paint();
}

// ConcreteProduct (Windows Family)
class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style button.");
    }
}

// ConcreteProduct
class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style checkbox.");
    }
}

// ConcreteProduct (Mac Family)
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a Mac style button.");
    }
}

// ConcreteProduct
class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Mac style checkbox.");
    }
}

// AbstractFactory - declares an interface for operations that create abstract product objects
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// ConcreteFactory - implements the operations to create concrete product objects
class WinFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

// ConcreteFactory
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Client - uses only interfaces declared by AbstractFactory and AbstractProduct classes
class Application {
    private final Button button;
    private final Checkbox checkbox;

    // Inject factory via constructor (DI)
    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

public class Main {
    public static void main(String[] args) {
        // Factory is determined by configuration (e.g., OS detection)

        System.out.println("--- Windows OS ---");
        GUIFactory winFactory = new WinFactory();
        Application app1 = new Application(winFactory);
        app1.paint();

        System.out.println("\n--- Mac OS ---");
        GUIFactory macFactory = new MacFactory();
        Application app2 = new Application(macFactory);
        app2.paint();
    }
}
