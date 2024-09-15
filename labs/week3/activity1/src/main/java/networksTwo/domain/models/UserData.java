package networksTwo.domain.models;

import networksTwo.domain.enums.OpSys;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record UserData (UUID id, String name, String email, String ipAddress, String deviceName, OpSys os, Date startConnection, Date endConnection, List<Error> errorLog) {
    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", os='" + os.name() + '\'' +
                ", endConnection=" + endConnection +
                ", startConnection=" + startConnection +
                ", errorLog=" + errorLog.toString() +
                '}';
    }
}
