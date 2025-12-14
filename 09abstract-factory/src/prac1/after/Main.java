package prac1.after;

// 1. Abstract Products (제품군별 인터페이스 분리)
interface Button {
    void paint();
    // void click(); // 버튼만의 기능이 추가될 수 있음
}

interface Checkbox {
    void paint();
    // void check(); // 체크박스만의 기능이 추가될 수 있음
}

// 2. Concrete Products (Windows Family)
class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style button.");
    }
}

class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows style checkbox.");
    }
}

// 2. Concrete Products (Mac Family)
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a Mac style button.");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Mac style checkbox.");
    }
}

// 3. Abstract Factory (관련된 제품군을 생성하는 인터페이스)
interface GUIFactory {
    Button createButton();       // 반환 타입이 Component가 아니라 Button
    Checkbox createCheckbox();   // 반환 타입이 Component가 아니라 Checkbox
}

// 4. Concrete Factories
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

// 5. Client (팩토리를 사용하는 녀석)
// Application은 구체적인 OS(Win/Mac)를 몰라도 됩니다.
class Application {
    private Button button;
    private Checkbox checkbox;

    // 생성자를 통해 팩토리를 주입받음 (DI)
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
        // 설정에 따라 팩토리가 결정됨 (예: OS 감지)
        // 여기서는 직접 지정

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