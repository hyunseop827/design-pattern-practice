package ex5.before;

class SystemConfig {
    private int volume = 50;
    private int brightness = 70;

    public SystemConfig() {
        System.out.println("Initializing System Config...");
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
        SystemConfig config = new SystemConfig();
        config.setVolume(90);
        config.setBrightness(100);
        System.out.println("Modifier: Settings changed.");
    }
}

class SettingViewer {
    public void showSettings() {
        SystemConfig config = new SystemConfig();
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