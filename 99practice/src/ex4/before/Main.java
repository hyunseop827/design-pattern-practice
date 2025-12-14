package ex4.before;

class Document {
    private String content = "";

    public void copy() {
        System.out.println("Copying text to clipboard: " + content);
    }

    public void paste() {
        content += " [Pasted] ";
        System.out.println("Pasted text. Current content: " + content);
    }

    public void cut() {
        System.out.println("Cutting text: " + content);
        content = "";
    }

    public void write(String text) {
        this.content += text;
    }
}

// Bad Design
class TextEditor {
    private Document document;

    public TextEditor(Document document) {
        this.document = document;
    }

    public void inputKey(String keyCombination) {
        if (keyCombination.equals("CTRL+C")) {
            document.copy();
        } else if (keyCombination.equals("CTRL+V")) {
            document.paste();
        } else if (keyCombination.equals("CTRL+X")) {
            document.cut();
        } else {
            System.out.println("Unknown shortcut: " + keyCombination);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Document doc = new Document();
        doc.write("Hello World");

        TextEditor editor = new TextEditor(doc);

        editor.inputKey("CTRL+C");
        editor.inputKey("CTRL+X");
        editor.inputKey("CTRL+V");
    }
}
