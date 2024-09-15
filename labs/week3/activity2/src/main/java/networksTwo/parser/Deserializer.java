package networksTwo.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import networksTwo.domain.Student;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class Deserializer {
    private final ObjectMapper objectMapper;

    public Deserializer() {
        this.objectMapper = new ObjectMapper(new MessagePackFactory());
    }

    public Student deserialize(byte[] data) throws Exception {
        return objectMapper.readValue(data, Student.class);
    }
}
