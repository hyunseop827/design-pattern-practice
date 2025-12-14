package ex4.after;

// Applying Command Pattern

// Command
interface Command {
    void execute();
}

// Concrete Command
class CopyCommand implements Command {
    private final Document document;

    public CopyCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.copy();

    }
}

class PasteCommand implements Command {
    private final Document document;

    public PasteCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.paste();

    }
}

class CutCommand implements Command {
    private final Document document;

    public CutCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.cut();

    }
}

class WriteCommand implements Command {
    private final Document document;
    private final String text;

    public WriteCommand(Document document, String text) {
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute() {
        document.write(text);
    }
}

// Receiver
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

// Invoker
class TextEditor {
    private Command command;
    public TextEditor(Command command) {
        this.command = command;
    }

    public void changeCommand(Command command) {
        this.command = command;
    }

    public void pressed() {
        command.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        Document doc = new Document();
        doc.write("Hello World");

        TextEditor editor = null;

        // Create Commnad
        Command copyCommand = new CopyCommand(doc);
        editor = new TextEditor(copyCommand);
        editor.pressed();

        Command cutCommand = new CutCommand(doc);
        editor.changeCommand(cutCommand);
        editor.pressed();

        Command pasteCommand = new PasteCommand(doc);
        editor.changeCommand(pasteCommand);
        editor.pressed();
    }
}