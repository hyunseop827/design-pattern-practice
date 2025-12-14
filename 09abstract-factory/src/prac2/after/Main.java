package prac2.after;

// AbstractProduct - declares an interface for a type of product object
interface Computer {
    void start();
    void attach(Storage storage);
}

// AbstractProduct
interface Storage {
    void allocate(int size);
}

// ConcreteProduct - defines a product object to be created by the corresponding ConcreteFactory
class Ec2Instance implements Computer {
    @Override
    public void start() {
        System.out.println("AWS EC2 Instance started.");
    }

    @Override
    public void attach(Storage storage) {
        System.out.println("Attached AWS S3 Storage to EC2.");
    }
}

// ConcreteProduct
class S3Storage implements Storage {
    @Override
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on AWS S3.");
    }
}

// ConcreteProduct
class GoogleComputeEngine implements Computer {
    @Override
    public void start() {
        System.out.println("GCP Compute Engine started.");
    }

    @Override
    public void attach(Storage storage) {
        System.out.println("Attached GCP Storage to Compute Engine.");
    }
}

// ConcreteProduct
class GoogleCloudStorage implements Storage {
    @Override
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on GCP Storage.");
    }
}

// AbstractFactory - declares an interface for operations that create abstract product objects
interface CloudFactory {
    Computer createComputer();
    Storage createStorage();
}

// ConcreteFactory - implements the operations to create concrete product objects
class AWSFactory implements CloudFactory {
    @Override
    public Computer createComputer() { return new Ec2Instance(); }
    @Override
    public Storage createStorage() { return new S3Storage(); }
}

// ConcreteFactory
class GCPFactory implements CloudFactory {
    @Override
    public Computer createComputer() { return new GoogleComputeEngine(); }
    @Override
    public Storage createStorage() { return new GoogleCloudStorage(); }
}

// Client - uses only interfaces declared by AbstractFactory and AbstractProduct classes
class CloudManager {
    private CloudFactory factory;

    // Inject which factory (AWS/GCP) to use via constructor
    public CloudManager(CloudFactory factory) {
        this.factory = factory;
    }

    // Core logic: assemble and operate infrastructure
    public void provisionInfrastructure() {
        // 1. Get compatible components from the factory
        Computer computer = factory.createComputer();
        Storage storage = factory.createStorage();

        // 2. Client controls the flow
        storage.allocate(50);
        computer.start();
        computer.attach(storage); // AWS-AWS or GCP-GCP compatibility is guaranteed
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Provisioning AWS ---");
        // Create manager with AWS factory
        CloudManager awsManager = new CloudManager(new AWSFactory());
        awsManager.provisionInfrastructure();

        System.out.println("\n--- Provisioning GCP ---");
        // Create manager with GCP factory
        CloudManager gcpManager = new CloudManager(new GCPFactory());
        gcpManager.provisionInfrastructure();
    }
}
