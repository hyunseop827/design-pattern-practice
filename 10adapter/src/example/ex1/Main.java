package example.ex1;

interface Adder {
    int plus(int x, int y);
}

class MyAdder implements Adder {
    @Override
    public int plus(int x, int y) {
        return x + y;
    }
}

class UseAdder {
    public int add(Adder adder, int x, int y) {
        int r = 0;
        r = adder.plus(x, y);
        return r;
    }
}

class YourAdder {
    public int add3(int x, int y, int z) {
        return x + y + z;
    }
}

class YourAdderAdapter implements Adder {
    private final YourAdder yourAdder;

    public YourAdderAdapter(YourAdder yourAdder) {
        this.yourAdder = yourAdder;
    }

    @Override
    public int plus(int x, int y) {
        return yourAdder.add3(x, y, 0);
    }
}

public class Main {
    public static void main(String[] args) {
        Adder adder = new MyAdder();
        UseAdder use = new UseAdder();
        System.out.println(use.add(adder, 10, 20));
        Adder adder1 = new YourAdderAdapter(new YourAdder());
        System.out.println(use.add(adder1, 10, 20));
    }
}
