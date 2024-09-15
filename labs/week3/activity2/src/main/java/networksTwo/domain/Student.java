package networksTwo.domain;

import java.util.UUID;

public record Student (String firstName, String lastName, int grade, UUID id, int age, Address address, String phone) {
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", id=" + id +
                ", age=" + age +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                '}';
    }
}
