package prac1.after;

// Virtual Proxy

// Subject
interface VideoInterface {
    void play();
}

// Real Subject
class RealVideo implements VideoInterface {

    private String id;
    private String title;
    private byte[] data;

    public RealVideo(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public void play() {
        System.out.println("[" + title + "] Playing...");
    }

    public void downloadFromServer() {
        System.out.println("[" + title + "] Downloading video... (5 sec)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = new byte[1024 * 1024 * 100];
        System.out.println("[" + title + "] Download complete! (100MB)");
    }
}

// Proxy
class ProxyVideo implements VideoInterface {

    private RealVideo realVideo;

    private String id;
    private String title;

    public ProxyVideo(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public void play() {
        if(realVideo == null){
            realVideo = new RealVideo(id,title);
            realVideo.downloadFromServer();
        }
        realVideo.play();
    }

}
public class Main {
    public static void main(String[] args) {
        System.out.println("=== YouTube App Start ===\n");

        System.out.println("Loading recommended videos...");
        VideoInterface pv1 = new ProxyVideo("v001", "Cat Video");
        VideoInterface pv2 = new ProxyVideo("v002", "Cooking Recipe");
        VideoInterface pv3 = new ProxyVideo("v003", "Coding Tutorial");

        System.out.println("\nUser clicks first video:");
        pv1.play();

    }
}
