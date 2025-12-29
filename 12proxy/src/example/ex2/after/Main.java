package example.ex2.after;

// Protection Proxy

// Subject
interface Document {
    void view();
    void edit(String newContent);
    void delete();
    String getOwner();
}

// Real Subject
class RealDocument implements Document {
    private String title;
    private String content;
    private String owner;

    public RealDocument(String title, String content, String owner) {
        this.title = title;
        this.content = content;
        this.owner = owner;
    }

    @Override
    public void view() {
        System.out.println("View document: " + title);
        System.out.println("Content: " + content);
    }

    @Override
    public void edit(String newContent) {
        this.content = newContent;
        System.out.println("Document edited: " + title);
    }

    @Override
    public void delete() {
        System.out.println("Document deleted: " + title);
    }

    @Override
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

// Proxy
class ProtectionProxy implements Document {
    private RealDocument realDocument;
    private User currentUser;

    public ProtectionProxy(RealDocument realDocument, User currentUser) {
        this.realDocument = realDocument;
        this.currentUser = currentUser;
    }

    @Override
    public void view() {
        if (hasViewPermission()) {
            realDocument.view();
        } else {
            System.out.println("Access denied: No view permission");
        }
    }

    @Override
    public void edit(String newContent) {
        if (hasEditPermission()) {
            realDocument.edit(newContent);
        } else {
            System.out.println("Access denied: No edit permission");
        }
    }

    @Override
    public void delete() {
        if (hasDeletePermission()) {
            realDocument.delete();
        } else {
            System.out.println("Access denied: No delete permission");
        }
    }

    @Override
    public String getOwner() {
        return realDocument.getOwner();
    }

    private boolean hasViewPermission() {
        return !currentUser.getRole().equals("GUEST");
    }

    private boolean hasEditPermission() {
        String role = currentUser.getRole();
        return role.equals("ADMIN") || role.equals("MEMBER");
    }

    private boolean hasDeletePermission() {
        return currentUser.getRole().equals("ADMIN");
    }
}

public class Main {
    public static void main(String[] args) {
        RealDocument doc = new RealDocument("Confidential", "Company secret content", "admin");

        User guest = new User("Guest", "GUEST");
        User member = new User("Employee", "MEMBER");
        User admin = new User("Admin", "ADMIN");

        System.out.println("=== Protection Proxy for Access Control ===\n");

        System.out.println("[GUEST - " + guest.getName() + "]");
        Document guestDoc = new ProtectionProxy(doc, guest);
        guestDoc.view();
        guestDoc.edit("Hacking!");
        guestDoc.delete();

        System.out.println("\n[MEMBER - " + member.getName() + "]");
        Document memberDoc = new ProtectionProxy(doc, member);
        memberDoc.view();
        memberDoc.edit("Normal edit");
        memberDoc.delete();

        System.out.println("\n[ADMIN - " + admin.getName() + "]");
        Document adminDoc = new ProtectionProxy(doc, admin);
        adminDoc.view();
        adminDoc.edit("Admin edit");
        adminDoc.delete();
    }
}
