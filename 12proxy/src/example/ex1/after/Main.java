package example.ex1.after;

// Virtual Proxy

// Subject
interface Image {
    void display();
    String getFilename();
}

// Real Subject
class HighResolutionImage implements Image {
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

    @Override
    public void display() {
        System.out.println("[" + filename + "] Displaying image");
    }

    @Override
    public String getFilename() {
        return filename;
    }
}

// Proxy
class ImageProxy implements Image {
    private String filename;
    private HighResolutionImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
        System.out.println("[" + filename + "] Proxy created (not loaded yet)");
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new HighResolutionImage(filename);
        }
        realImage.display();
    }

    @Override
    public String getFilename() {
        return filename;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Image Gallery App (with Proxy) ===\n");

        System.out.println("Creating image proxies...");
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");
        Image image3 = new ImageProxy("photo3.jpg");

        System.out.println("\nUser clicks first image:");
        image1.display();

        System.out.println("\nUser clicks third image:");
        image3.display();

        System.out.println("\nUser clicks first image again:");
        image1.display();

        System.out.println("\n=== Improvements ===");
        System.out.println("photo2 not clicked -> not loaded -> memory saved!");
        System.out.println("Only load images when needed (Lazy Loading)");
    }
}
