package example.ex1.before;

import java.util.ArrayList;
import java.util.List;

class File {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        System.out.println("[File] " + name + ", Size: " + size);
    }
}

class Directory {
    private String name;
    private int depth = 0;
    private List<Object> entries = new ArrayList<Object>();

    public Directory(String name) {
        this.name = name;
    }

    public void addEntry(Object entry) {
        entries.add(entry);
    }

    public void removeEntry(Object entry) {
        entries.remove(entry);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        int size = 0;
        for (Object entry : entries) {
            if (entry instanceof File)
                size += ((File) entry).getSize();
            if (entry instanceof Directory)
                size += ((Directory) entry).getSize();
        }
        return size;
    }

    public void print() {
        System.out.println("[Directory] " + name + ", Size: " + getSize());
        for (Object entry : entries) {
            if (entry instanceof File)
                ((File) entry).print();
            if (entry instanceof Directory)
                ((Directory) entry).print();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Directory dir1 = new Directory("root");
        Directory dir2 = new Directory("Dir1");
        File f1 = new File("f1", 100);
        File f2 = new File("f2", 200);
        File f3 = new File("f3", 300);
        File f4 = new File("f4", 400);
        dir1.addEntry(f1);
        dir1.addEntry(dir2);
        dir2.addEntry(f2);
        dir2.addEntry(f3);
        dir1.addEntry(f4);
        dir1.print();
    }
}