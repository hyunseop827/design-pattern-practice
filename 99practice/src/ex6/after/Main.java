package ex6.after;

// Applying Decorator Pattern

// Component
interface DataSource {
    void writeData(String data);
    String readData(String data);
}

// Concrete Component
class FileDataSource implements DataSource {
    @Override
    public void writeData(String data) {
        System.out.println("Writing file: " + data);
    }

    @Override
    public String readData(String data) {
        return data;
    }
}

// Decorator
abstract class DataSourceDecorator implements DataSource {
    protected DataSource dataSource;

    public DataSourceDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeData(String data) {
        dataSource.writeData(data);
    }

    @Override
    public String readData(String data) {
        return dataSource.readData(data);
    }
}

// Concrete Decorator 1
class EncryptDataSource extends DataSourceDecorator {
    public EncryptDataSource(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        String processedData = "Encrypted(" + data + ")";
        super.writeData(processedData);
    }

    @Override
    public String readData(String data) {
        String processedData = super.readData(data);
        return processedData.replace("Encrypted(", "").replace(")", "");
    }
}

// Concrete Decorator 2
class CompressDataSource extends DataSourceDecorator {
    public CompressDataSource(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        String processedData = "Compressed(" + data + ")";
        super.writeData(processedData);
    }

    @Override
    public String readData(String data) {
        String processedData = super.readData(data);
        return processedData.replace("Compressed(", "").replace(")", "");
    }
}

public class Main {
    public static void main(String[] args) {
        DataSource ds = new CompressDataSource(new EncryptDataSource(new FileDataSource()));

        String data = "Hello World";

        System.out.println("--- Write ---");
        ds.writeData(data);

        System.out.println("\n--- Read ---");

        String fileContent = "Compressed(Encrypted(Hello World))";
        System.out.println("Result: " + ds.readData(fileContent));
    }
}