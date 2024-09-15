package networksTwo.services.userDataStream;

import networksTwo.domain.enums.OpSys;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;

import java.util.Date;
import java.util.UUID;

public interface IUserDataStream {
    void printAllUserDataStreams();
    UserData getUserDataById(UUID id);
    UserData addUserDataToStream(String name, String email, String ipAddress, String deviceName, OpSys os, Date startConnection, Date endConnection);
    Error addErrorToUserData(UserData userData, Priority priority, String message);
}
