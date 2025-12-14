package prac1.after;

import java.util.ArrayList;
import java.util.List;

// Component
abstract class FileComponenet {
    protected String name;

    abstract void print();

    abstract int getSize();
}

class File extends FileComponenet {
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    void print() {
        System.out.println("File: " + name + " (" + size + "kb)");
    }

    @Override
    int getSize() {
        return size;
    }
}

class Directory extends FileComponenet {
    private List<FileComponenet> componenetList = new ArrayList<FileComponenet>();

    public Directory(String name) {
        this.name = name;
    }

    public void addFileComponenet(FileComponenet fc) {
        componenetList.add(fc);
    }

    @Override
    void print() {
        System.out.println("Directory: " + name + " (Total Size: " + getSize() + "kb)");
        for(FileComponenet c : componenetList) {
            c.print();
        }
    }

    @Override
    int getSize() {
        int total = 0;
        for(FileComponenet c : componenetList) {
            total += c.getSize();
        }

        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        Directory bin = new Directory("bin");
        Directory tmp = new Directory("tmp");

        File file1 = new File("vi", 100);
        File file2 = new File("latex", 200);
        File file3 = new File("junk", 10);

        root.addFileComponenet(bin);
        root.addFileComponenet(tmp);

        bin.addFileComponenet(file1);
        bin.addFileComponenet(file2);
        bin.addFileComponenet(file3);

        root.print();
    }
}