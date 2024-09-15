package networksTwo;

import networksTwo.domain.Address;
import networksTwo.domain.Student;
import networksTwo.parser.Deserializer;
import networksTwo.parser.Serializer;

import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            Student student = new Student(
                    "Capi",
                    "Oso",
                    85,
                    UUID.randomUUID(),
                    17,
                    new Address("Bolivia", "Cercado", "Oruro", "La Paz St", 123),
                    "456-789123"
            );
            System.out.println("\nRAW");
            System.out.println(student);

            System.out.println("\nSERIALIZED");
            Serializer serializer = new Serializer();
            byte[] serializedData = serializer.serialize(student);
            System.out.println(Arrays.toString(serializedData));

            System.out.println("\nDESERIALIZED");
            Deserializer deserializer = new Deserializer();
            Student deserializedStudent = deserializer.deserialize(serializedData);
            System.out.println(deserializedStudent);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}