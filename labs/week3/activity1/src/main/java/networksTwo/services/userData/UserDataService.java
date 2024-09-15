package networksTwo.services.userData;

import networksTwo.domain.enums.OpSys;
import networksTwo.domain.models.Error;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.UserData;
import networksTwo.services.error.ErrorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class UserDataService implements IUserData{

    private final ErrorService errorService;

    public UserDataService() {
        this.errorService = new ErrorService();
    }

    @Override
    public UserData createUserData(String name, String email, String ipAddress, String deviceName, OpSys os, Date startConnection, Date endConnection) {
        return new UserData(UUID.randomUUID(), name,email,ipAddress,deviceName, os, startConnection, endConnection, new ArrayList<>());
    }

    @Override
    public Error addErrorToUserData(UserData userData, Priority priority, String message) {
        Error error = errorService.createError(priority, message);
        userData.errorLog().add(error);
        return error;
    }
}
