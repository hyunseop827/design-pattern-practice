package ex3.before;

import java.util.ArrayList;
import java.util.List;

class Circle {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square {
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class GraphicGroup {
    private List<Circle> circles = new ArrayList<>();
    private List<Square> squares = new ArrayList<>();

    public void addCircle(Circle c) {
        circles.add(c);
    }

    public void addSquare(Square s) {
        squares.add(s);
    }

    public void drawGroup() {
        System.out.println("--- Group Start ---");
        for (Circle c : circles) {
            c.draw();
        }
        for (Square s : squares) {
            s.draw();
        }
        System.out.println("--- Group End ---");
    }
}

public class Main {
    public static void main(String[] args) {
        GraphicGroup group = new GraphicGroup();

        group.addCircle(new Circle());
        group.addSquare(new Square());
        group.addCircle(new Circle());

        group.drawGroup();
    }
}
