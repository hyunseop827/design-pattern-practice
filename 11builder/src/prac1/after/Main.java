package prac1.after;

// Product
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

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
        this.country = builder.country;
        this.isActive = builder.isActive;
        this.isEmailVerified = builder.isEmailVerified;
        this.isPhoneVerified = builder.isPhoneVerified;
        this.role = builder.role;
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

    // Builder
    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String city;
        private String zipCode;
        private String country;
        private boolean isActive;
        private boolean isEmailVerified;
        private boolean isPhoneVerified;
        private String role;


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder isEmailVerified(boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
            return this;
        }

        public Builder isPhoneVerified(boolean isPhoneVerified) {
            this.isPhoneVerified = isPhoneVerified;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
    User user1 = new User.Builder().firstName("Anna").lastName("Oxana")
            .email("anna@email.com").phone("02-11-23").address("Seoul")
            .zipCode("00000").country("Korea").isActive(true).isEmailVerified(false)
            .isPhoneVerified(true).role("USER").build();

    System.out.println(user1);
    }
}
