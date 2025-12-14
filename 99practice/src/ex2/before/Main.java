package ex2.before;

class GameLoader {
    public void loadDiablo() {
        System.out.println("Checking system memory...");
        System.out.println("Cleaning up temporary files...");

        // Diablo Specific Logic
        System.out.println("Loading Diablo dark gothic assets...");
        System.out.println("Connecting to Battle.net...");

        System.out.println("Loading Player Profile...");
    }

    public void loadMario() {
        System.out.println("Checking system memory...");
        System.out.println("Cleaning up temporary files...");

        // Mario Specific Logic
        System.out.println("Loading Mario sprite assets...");
        System.out.println("Initializing Nintendo World map...");

        System.out.println("Loading Player Profile...");
    }
}

public class Main {
    public static void main(String[] args) {
        GameLoader loader = new GameLoader();

        System.out.println("--- Start Diablo ---");
        loader.loadDiablo();

        System.out.println("\n--- Start Mario ---");
        loader.loadMario();
    }
}
