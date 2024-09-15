package networksTwo.domain.models;

import networksTwo.domain.enums.Priority;

import java.util.Date;
import java.util.UUID;

public record Error(UUID id, Priority priority, String message, Date date) {
    @Override
    public String toString() {
        return "Error{" +
                "id=" + id +
                ", priority=" + priority.name() +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
