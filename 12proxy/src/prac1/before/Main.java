package prac1.before;

class Video {
    private String id;
    private String title;
    private byte[] data;

    public Video(String id, String title) {
        this.id = id;
        this.title = title;
        downloadFromServer();
    }

    private void downloadFromServer() {
        System.out.println("[" + title + "] Downloading video... (5 sec)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = new byte[1024 * 1024 * 100];
        System.out.println("[" + title + "] Download complete! (100MB)");
    }

    public void play() {
        System.out.println("[" + title + "] Playing...");
    }

    public String getTitle() {
        return title;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== YouTube App Start ===\n");

        System.out.println("Loading recommended videos...");
        Video video1 = new Video("v001", "Cat Video");
        Video video2 = new Video("v002", "Cooking Recipe");
        Video video3 = new Video("v003", "Coding Tutorial");

        System.out.println("\nUser clicks only first video:");
        video1.play();

        System.out.println("\n=== Problems ===");
        System.out.println("User only watched Cat Video but all 3 downloaded");
        System.out.println("Initial loading 15 sec + 300MB memory wasted!");
    }
}
