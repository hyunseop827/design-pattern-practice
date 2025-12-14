package example.ex1.after;

import java.util.Scanner;

// Component - defines the interface for objects that can have responsibilities added dynamically
interface Text {
    String render();
}

// ConcreteComponent - defines an object to which additional responsibilities can be attached
class PlainText implements Text {

    private final String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

// Decorator - maintains a reference to a Component object and defines an interface that conforms to Component's interface
abstract class Decorator implements Text {
    private final Text texts;

    public Decorator(Text texts) {
        this.texts = texts;
    }

    @Override
    public String render() {
        return texts.render();
    }
}

// ConcreteDecorator - adds responsibilities to the component
class SurroundDecorator extends Decorator {
    private final String first;
    private final String second;
    public SurroundDecorator(Text texts, String first, String second) {
        super(texts);
        this.first = first;
        this.second = second;
    }

    @Override
    public String render() {
        return first + super.render() + second;
    }
}

// ConcreteDecorator - adds padding characters on both sides
class RepeatPadDecorator extends Decorator {
    private final char pad;
    private final int repeat;

    public RepeatPadDecorator(Text texts, char pad, int repeat) {
        super(texts);
        this.pad = pad;
        this.repeat = repeat;
    }

    @Override
    public String render() {
        String side = String.valueOf(pad).repeat(Math.max(0, repeat));
        return side + super.render() + side;
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        Text base = new PlainText(input);

        Text styleA =
                new RepeatPadDecorator(
                        new SurroundDecorator(base, "[", "]"),
                        '*', 3
                );


        System.out.println("A: " + styleA.render());

        Text styleB =
                new SurroundDecorator(
                        new RepeatPadDecorator(
                                new SurroundDecorator(base, "[", "]"),
                                '*', 3
                        ), "<<", ">>");

        System.out.println("B: " + styleB.render());
    }
}

