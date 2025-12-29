package example.ex1.before;

class HighResolutionImage {
    private String filename;
    private byte[] imageData;

    public HighResolutionImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("[" + filename + "] Loading high-resolution image... (3 sec)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.imageData = new byte[1024 * 1024 * 10];
        System.out.println("[" + filename + "] Loading complete! (10MB)");
    }

    public void display() {
        System.out.println("[" + filename + "] Displaying image");
    }

    public String getFilename() {
        return filename;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Image Gallery App ===\n");

        System.out.println("Creating image objects...");
        HighResolutionImage image1 = new HighResolutionImage("photo1.jpg");
        HighResolutionImage image2 = new HighResolutionImage("photo2.jpg");
        HighResolutionImage image3 = new HighResolutionImage("photo3.jpg");

        System.out.println("\nUser clicks only first image:");
        image1.display();

        System.out.println("\n=== Problems ===");
        System.out.println("User only viewed photo1, but photo2, photo3 also loaded");
        System.out.println("Unnecessary memory usage + 9 sec wasted on initial loading!");
    }
}
