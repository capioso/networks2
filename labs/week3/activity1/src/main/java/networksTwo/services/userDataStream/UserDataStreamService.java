package networksTwo.services.userDataStream;

import networksTwo.domain.enums.OpSys;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;
import networksTwo.domain.models.UserDataStream;
import networksTwo.services.userData.UserDataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class UserDataStreamService implements IUserDataStream {

    private final UserDataService userDataService;

    public UserDataStream getUserDataStream() {
        return userDataStream;
    }

    private final UserDataStream userDataStream;

    public UserDataStreamService() {
        this.userDataService = new UserDataService();
        this.userDataStream = new UserDataStream(new ArrayList<>());
    }

    public UserDataStreamService(UserDataStream userDataStream) {
        this.userDataService = new UserDataService();
        this.userDataStream = userDataStream;
    }

    @Override
    public void printAllUserDataStreams() {
        userDataStream.userStream().forEach(userData -> System.out.println(userData.toString()));
    }

    @Override
    public UserData getUserDataById(UUID id) {
        return userDataStream.userStream()
                .stream()
                .filter(userData -> userData.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserData addUserDataToStream(String name, String email, String ipAddress, String deviceName, OpSys os, Date startConnection, Date endConnection) {
        UserData userData = userDataService.createUserData(name, email, ipAddress, deviceName, os, startConnection, endConnection);
        userDataStream.userStream().add(userData);
        return userData;
    }

    @Override
    public Error addErrorToUserData(UserData userData, Priority priority, String message) {
        return userDataService.addErrorToUserData(userData, priority, message);
    }
}
