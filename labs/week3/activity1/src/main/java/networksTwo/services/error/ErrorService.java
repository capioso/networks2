package networksTwo.services.error;

import networksTwo.domain.models.Error;
import networksTwo.domain.enums.Priority;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ErrorService implements IError{
    @Override
    public Error createError(Priority priority, String message) {
        return new Error(UUID.randomUUID(), priority, message, Date.from(Instant.now()));
    }
}
