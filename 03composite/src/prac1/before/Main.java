package prac1.before;

import java.util.ArrayList;
import java.util.List;

class File {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        System.out.println("File: " + name + " (" + size + "kb)");
    }
}

class Directory {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Directory> directories = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    // Problematic part - different methods for files and directories
    public void addFile(File file) {
        files.add(file);
    }

    public void addDirectory(Directory directory) {
        directories.add(directory);
    }

    public int getSize() {
        int total = 0;
        for (File f : files) {
            total += f.getSize();
        }
        for (Directory d : directories) {
            total += d.getSize();
        }
        return total;
    }

    public void print() {
        System.out.println("Directory: " + name + " (Total Size: " + getSize() + "kb)");

        for (Directory d : directories) {
            d.print();
        }
        for (File f : files) {
            f.print();
        }
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

        root.addDirectory(bin);
        root.addDirectory(tmp);

        bin.addFile(file1);
        bin.addFile(file2);
        tmp.addFile(file3);

        root.print();
    }
}