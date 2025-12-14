package prac2.before;

class Ec2Instance {
    public void start() {
        System.out.println("AWS EC2 Instance started.");
    }
    public void attach(S3Storage storage) {
        System.out.println("Attached AWS S3 Storage to EC2.");
    }
}

class S3Storage {
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on AWS S3.");
    }
}

class GoogleComputeEngine {
    public void start() {
        System.out.println("GCP Compute Engine started.");
    }
    public void attach(GoogleCloudStorage storage) {
        System.out.println("Attached GCP Storage to Compute Engine.");
    }
}

class GoogleCloudStorage {
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on GCP Storage.");
    }
}

class CloudManager {
    public void provisionInfrastructure(String provider) {
        if (provider.equals("AWS")) {
            Ec2Instance compute = new Ec2Instance();
            S3Storage storage = new S3Storage();

            storage.allocate(50);
            compute.start();
            compute.attach(storage);

        } else if (provider.equals("GCP")) {
            GoogleComputeEngine compute = new GoogleComputeEngine();
            GoogleCloudStorage storage = new GoogleCloudStorage();

            storage.allocate(50);
            compute.start();
            compute.attach(storage);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CloudManager manager = new CloudManager();

        System.out.println("--- Provisioning AWS ---");
        manager.provisionInfrastructure("AWS");

        System.out.println("\n--- Provisioning GCP ---");
        manager.provisionInfrastructure("GCP");
    }
}