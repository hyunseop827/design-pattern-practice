package prac2.after;

// Abstract Product
interface Computer {
    void start();
    void attach(Storage storage);
}

interface Storage {
    void allocate(int size);
}

// Concrete Product
class Ec2Instance implements Computer{
    @Override
    public void start() {
        System.out.println("AWS EC2 Instance started.");
    }

    @Override
    public void attach(Storage storage) {
        System.out.println("Attached AWS S3 Storage to EC2.");
    }
}

class S3Storage implements Storage {
    @Override
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on AWS S3.");
    }
}

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

class GoogleCloudStorage implements Storage{
    @Override
    public void allocate(int size) {
        System.out.println("Allocated " + size + "GB on GCP Storage.");
    }
}

// 2. Abstract Factory (순수하게 생성 인터페이스만 정의)
interface CloudFactory { // 이름 변경: AbstractFactory -> CloudFactory
    Computer createComputer();
    Storage createStorage();
}

// 3. Concrete Factories (생성 책임만 가짐)
class AWSFactory implements CloudFactory {
    @Override
    public Computer createComputer() { return new Ec2Instance(); }
    @Override
    public Storage createStorage() { return new S3Storage(); }
}

class GCPFactory implements CloudFactory {
    @Override
    public Computer createComputer() { return new GoogleComputeEngine(); }
    @Override
    public Storage createStorage() { return new GoogleCloudStorage(); }
}

// 4. Client (비즈니스 로직 담당)
class CloudManager {
    private CloudFactory factory;

    // 생성자를 통해 "어떤 공장(AWS/GCP)"을 쓸지 주입받음
    public CloudManager(CloudFactory factory) {
        this.factory = factory;
    }

    // [핵심] 조립 및 운영 로직은 여기서 수행
    public void provisionInfrastructure() {
        // 1. 팩토리를 통해 호환성이 보장된 부품들을 납품받음
        Computer computer = factory.createComputer();
        Storage storage = factory.createStorage();

        // 2. Client가 직접 흐름을 제어함
        storage.allocate(50);
        computer.start();
        computer.attach(storage); // 여기서 AWS-AWS 끼리, GCP-GCP 끼리 연결됨이 보장됨
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Provisioning AWS ---");
        // AWS 공장을 넣어서 매니저 생성
        CloudManager awsManager = new CloudManager(new AWSFactory());
        awsManager.provisionInfrastructure();

        System.out.println("\n--- Provisioning GCP ---");
        // GCP 공장을 넣어서 매니저 생성
        CloudManager gcpManager = new CloudManager(new GCPFactory());
        gcpManager.provisionInfrastructure();
    }
}