package example.ex2.before;

class Document {
    private String title;
    private String content;
    private String owner;

    public Document(String title, String content, String owner) {
        this.title = title;
        this.content = content;
        this.owner = owner;
    }

    public void view() {
        System.out.println("View document: " + title);
        System.out.println("Content: " + content);
    }

    public void edit(String newContent) {
        this.content = newContent;
        System.out.println("Document edited: " + title);
    }

    public void delete() {
        System.out.println("Document deleted: " + title);
    }

    public String getOwner() {
        return owner;
    }
}

class User {
    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

public class Main {
    public static void main(String[] args) {
        Document doc = new Document("Confidential", "Company secret content", "admin");

        User guest = new User("Guest", "GUEST");
        User member = new User("Employee", "MEMBER");
        User admin = new User("Admin", "ADMIN");

        System.out.println("=== Direct Access Without Permission Check ===\n");

        System.out.println("[" + guest.getName() + "] Document access:");
        doc.view();
        doc.edit("Hacking!");
        doc.delete();

        System.out.println("\n=== Problems ===");
        System.out.println("GUEST modified and deleted confidential document!");
        System.out.println("No permission check logic - anyone can do anything");
    }
}
