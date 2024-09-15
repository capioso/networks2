package networksTwo.services.error;

import networksTwo.domain.models.Error;
import networksTwo.domain.enums.Priority;

public interface IError {
    Error createError(Priority priority, String message);

}
