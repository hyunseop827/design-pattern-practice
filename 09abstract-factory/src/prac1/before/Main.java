package prac1.before;

class WinButton {
    public void paint() {
        System.out.println("Rendering a Windows style button.");
    }
}

class MacButton {
    public void paint() {
        System.out.println("Rendering a Mac style button.");
    }
}

class WinCheckbox {
    public void paint() {
        System.out.println("Rendering a Windows style checkbox.");
    }
}

class MacCheckbox {
    public void paint() {
        System.out.println("Rendering a Mac style checkbox.");
    }
}

class GUIBuilder {
    public void renderWindow(String osType) {
        if (osType.equals("WINDOWS")) {
            WinButton button = new WinButton();
            WinCheckbox checkbox = new WinCheckbox();

            button.paint();
            checkbox.paint();
        } else if (osType.equals("MAC")) {
            MacButton button = new MacButton();
            MacCheckbox checkbox = new MacCheckbox();

            button.paint();
            checkbox.paint();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GUIBuilder builder = new GUIBuilder();

        System.out.println("--- Windows OS ---");
        builder.renderWindow("WINDOWS");

        System.out.println("\n--- Mac OS ---");
        builder.renderWindow("MAC");
    }
}