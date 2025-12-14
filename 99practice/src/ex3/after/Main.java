package ex3.after;

import java.util.ArrayList;
import java.util.List;

// Applying Composite Pattern
// Component
interface Component {
    void draw();
}

// Leaf
class Circle implements Component {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Component {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

// Composite
class GraphicGroup implements Component {

    private final List<Component> components = new ArrayList<Component>();

    @Override
    public void draw() {
        System.out.println("--- Group Start ---");
        for (Component c : components) c.draw();
        System.out.println("--- Group End ---");
    }

    public void addComponent(Component c) {
        components.add(c);
    }

    public void removeComponent(Component c) {
        components.remove(c);
    }
}

public class Main {
    public static void main(String[] args) {
        GraphicGroup group = new GraphicGroup();

        Component circle = new Circle();
        Component square = new Square();

        group.addComponent(circle);
        group.addComponent(square);

        group.draw();
    }
}