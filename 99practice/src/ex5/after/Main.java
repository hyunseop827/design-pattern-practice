package ex5.after;

// Applying Singleton
class SystemConfig {
    private int volume = 50;
    private int brightness = 70;

    private volatile static SystemConfig singlton;

    private SystemConfig(){
        System.out.println("Initializing System Config...");
    }

    // Inner class Version
    private static class ConfigHolder{
        private static final SystemConfig INSTANCE = new SystemConfig();
    }

    public static SystemConfig getInstance(){
        return ConfigHolder.INSTANCE;
    }

    // Volatile
    public static SystemConfig getInstance2(){
        if (singlton == null){
            synchronized (SystemConfig.class){
                if (singlton == null){
                    singlton = new SystemConfig();
                }
            }
        }
        return  singlton;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }
}

class SettingModifier {
    public void changeSettings() {
        SystemConfig config = SystemConfig.getInstance();
        config.setVolume(90);
        config.setBrightness(100);
        System.out.println("Modifier: Settings changed.");
    }
}

class SettingViewer {
    public void showSettings() {
        SystemConfig config = SystemConfig.getInstance();
        System.out.println("Viewer: Current Volume = " + config.getVolume());
        System.out.println("Viewer: Current Brightness = " + config.getBrightness());
    }
}

public class Main {
    public static void main(String[] args) {
        SettingModifier modifier = new SettingModifier();
        SettingViewer viewer = new SettingViewer();

        modifier.changeSettings();

        System.out.println("----------------");

        viewer.showSettings();
    }
}
