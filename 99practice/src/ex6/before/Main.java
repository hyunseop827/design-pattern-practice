package ex6.before;

class FileDataSource {
    private boolean encrypt;
    private boolean compress;

    public FileDataSource(boolean encrypt, boolean compress) {
        this.encrypt = encrypt;
        this.compress = compress;
    }

    public void writeData(String data) {
        String processedData = data;

        // Encryption
        if (encrypt) {
            processedData = "Encrypted(" + processedData + ")";
        }
        // Compression
        if (compress) {
            processedData = "Compressed(" + processedData + ")";
        }

        System.out.println("Writing to file: " + processedData);
    }

    public String readData(String data) {
        String processedData = data;

        // Decompression
        if (compress) {
            processedData = processedData.replace("Compressed(", "").replace(")", "");
        }
        // Decryption
        if (encrypt) {
            processedData = processedData.replace("Encrypted(", "").replace(")", "");
        }

        return processedData;
    }
}

public class Main {
    public static void main(String[] args) {
        // Apply encryption + compression
        FileDataSource source = new FileDataSource(true, true);

        String data = "Hello World";

        source.writeData(data);
        // Expected output: Writing to file: Compressed(Encrypted(Hello World))

        System.out.println("Reading: " + source.readData("Compressed(Encrypted(Hello World))"));
        // Expected output: Reading: Hello World
    }
}
