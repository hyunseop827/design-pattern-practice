package ex2.after;

// Applying Template Method Pattern
// Abstract Class
abstract class GameLoader {
    public void load(){
        firstLoad();
        gameLoad();
        endLoad();
    }

    private void firstLoad(){
        System.out.println("Checking system memory...");
        System.out.println("Cleaning up temporary files...");
    }

    private void endLoad(){
        System.out.println("Loading Player Profile...");
    }

    protected abstract void gameLoad();
}

// Concrete Class
class DiabloLoader extends GameLoader {
    @Override
    protected void gameLoad() {
        // Diablo Specific Logic
        System.out.println("Loading Diablo dark gothic assets...");
        System.out.println("Connecting to Battle.net...");
    }
}

class MarioLoader extends GameLoader {
    @Override
    protected void gameLoad() {
        // Mario Specific Logic
        System.out.println("Loading Mario sprite assets...");
        System.out.println("Initializing Nintendo World map...");

    }
}


public class Main {
    public static void main(String[] args) {
        GameLoader loader = new DiabloLoader();
        GameLoader loader2 = new MarioLoader();

        System.out.println("--- Start Diablo ---");
        loader.load();

        System.out.println("\n--- Start Mario ---");
        loader2.load();
    }
}
