package networksTwo.parser;

import networksTwo.domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class Serializer {
    private final ObjectMapper objectMapper;

    public Serializer() {
        this.objectMapper = new ObjectMapper(new MessagePackFactory());
    }

    public byte[] serialize(Student student) throws Exception {
        return objectMapper.writeValueAsBytes(student);
    }
}
