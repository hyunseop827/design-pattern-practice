package example.ex1.after_refined;

import java.util.ArrayList;
import java.util.List;

// Component
abstract class FileSystem {
    private String name; // 1. private으로 캡슐화

    // 2. 부모 생성자에서 초기화
    public FileSystem(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract int getSize();

    // 3. 깊이(indent)를 표현하기 위한 오버로딩 메서드 추가
    public abstract void print(String indent);
}

// Leaf
class File extends FileSystem {
    private int size;

    public File(String name, int size) {
        super(name); // 부모 생성자 호출
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

// Composite
class Directory extends FileSystem {
    private List<FileSystem> entries = new ArrayList<>();

    public Directory(String name) {
        super(name); // 부모 생성자 호출
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
        // 자식 요소들은 현재 들여쓰기에 공백을 더해서 출력
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

        // 출력 시작 (초기 들여쓰기는 없음)
        dir1.print("");
    }
}