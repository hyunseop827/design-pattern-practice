package example.ex1.after;

import java.util.ArrayList;
import java.util.List;

// Component - declares the interface for objects in the composition
abstract class FileSystem {
    private String name;

    public FileSystem(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract int getSize();

    public abstract void print(String indent);
}

// Leaf - represents leaf objects in the composition (has no children)
class File extends FileSystem {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "[File] " + getName() + ", Size: " + size);
    }
}

// Composite - defines behavior for components having children
class Directory extends FileSystem {
    private List<FileSystem> entries = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void addEntry(FileSystem entry) {
        entries.add(entry);
    }

    public void removeEntry(FileSystem entry) {
        entries.remove(entry);
    }

    @Override
    public int getSize() {
        int size = 0;
        for (FileSystem entry : entries) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "[Directory] " + getName() + ", Size: " + getSize());
        for (FileSystem entry : entries) {
            entry.print(indent + "   ");
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

        // Client treats Composite and Leaf uniformly
        dir1.print("");
    }
}