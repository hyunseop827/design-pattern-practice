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

        // 암호화 처리
        if (encrypt) {
            processedData = "Encrypted(" + processedData + ")";
        }
        // 압축 처리
        if (compress) {
            processedData = "Compressed(" + processedData + ")";
        }

        System.out.println("Writing to file: " + processedData);
    }

    public String readData(String data) {
        String processedData = data;

        // 압축 해제
        if (compress) {
            processedData = processedData.replace("Compressed(", "").replace(")", "");
        }
        // 복호화
        if (encrypt) {
            processedData = processedData.replace("Encrypted(", "").replace(")", "");
        }

        return processedData;
    }
}

public class Main {
    public static void main(String[] args) {
        // 암호화 + 압축 적용
        FileDataSource source = new FileDataSource(true, true);

        String data = "Hello World";

        source.writeData(data);
        // 출력 예상: Writing to file: Compressed(Encrypted(Hello World))

        System.out.println("Reading: " + source.readData("Compressed(Encrypted(Hello World))"));
        // 출력 예상: Reading: Hello World
    }
}