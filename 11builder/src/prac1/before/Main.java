package prac1.before;

class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String country;
    private final boolean isActive;
    private final boolean isEmailVerified;
    private final boolean isPhoneVerified;
    private final String role;

    public User(String firstName, String lastName, String email, String phone,
                String address, String city, String zipCode, String country,
                boolean isActive, boolean isEmailVerified, boolean isPhoneVerified,
                String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.isActive = isActive;
        this.isEmailVerified = isEmailVerified;
        this.isPhoneVerified = isPhoneVerified;
        this.role = role;
    }

    public User(String firstName, String lastName, String email) {
        this(firstName, lastName, email, null, null, null, null, "Korea",
             true, false, false, "USER");
    }

    public User(String firstName, String lastName, String email, String phone) {
        this(firstName, lastName, email, phone, null, null, null, "Korea",
             true, false, false, "USER");
    }

    public User(String firstName, String lastName, String email, String phone,
                String address, String city) {
        this(firstName, lastName, email, phone, address, city, null, "Korea",
             true, false, false, "USER");
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", isActive=" + isActive +
                ", isEmailVerified=" + isEmailVerified +
                ", isPhoneVerified=" + isPhoneVerified +
                ", role='" + role + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        User user1 = new User(
                "김철수",
                "Kim",
                "chulsoo@email.com",
                "010-1234-5678",
                "서울시 강남구",
                "서울",
                "12345",
                "Korea",
                true,
                false,
                true,
                "ADMIN"
        );
        System.out.println(user1);

        User user2 = new User(
                "이영희",
                "Lee",
                "younghee@email.com",
                "010-9876-5432",
                "부산시 해운대구",
                "부산",
                "54321",
                "Korea",
                true,
                true,
                false,
                "USER"
        );
        System.out.println(user2);

        User user3 = new User("박민수", "Park", "minsu@email.com");
        System.out.println(user3);

        User user4 = new User(
                "최지은",
                "Choi",
                "jieun@email.com",
                null,
                null,
                null,
                null,
                "Korea",
                false,
                false,
                false,
                "USER"
        );
        System.out.println(user4);
    }
}