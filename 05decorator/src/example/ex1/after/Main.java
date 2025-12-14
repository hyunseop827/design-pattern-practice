package example.ex1.after;

import java.util.Scanner;

// Component
interface Text {
    String render();
}

// Concrete Component
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

// Decorator
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

// Concrete Decorator
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 입력: ");
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

