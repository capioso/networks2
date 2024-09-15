package networksTwo.services.parser.json;

import networksTwo.domain.enums.OpSys;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;
import networksTwo.domain.models.UserDataStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JsonReader {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    public static UserDataStream fromJson(JSONArray jsonArray) {
        List<UserData> userDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            UUID id = UUID.fromString(jsonObject.get("id").toString());
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String ipAddress = jsonObject.getString("ipAddress");
            String deviceName = jsonObject.getString("deviceName");
            OpSys os = OpSys.valueOf(jsonObject.getString("os"));
            Date endConnection = parseDateFromJson(jsonObject, "endConnection");
            Date startConnection = parseDateFromJson(jsonObject, "startConnection");

            List<Error> errorLog = new ArrayList<>();
            JSONArray errorLogArray = jsonObject.getJSONArray("errorLog");
            for (int j = 0; j < errorLogArray.length(); j++) {
                JSONObject errorJson = errorLogArray.getJSONObject(j);
                UUID errorId = UUID.fromString(jsonObject.get("id").toString());
                Priority priority = Priority.valueOf(errorJson.getString("priority"));
                String message = errorJson.getString("message");
                Date date = parseDateFromJson(errorJson, "date");

                Error error = new Error(errorId, priority, message, date);
                errorLog.add(error);
            }
            UserData userData = new UserData(id, name, email, ipAddress, deviceName, os, endConnection, startConnection, errorLog);
            userDataList.add(userData);
        }

        return new UserDataStream(userDataList);
    }

    private static Date parseDateFromJson(JSONObject jsonObject, String key) {
        Object dateObject = jsonObject.get(key);
        if (dateObject instanceof String) {
            try {
                return DATE_FORMAT.parse((String) dateObject);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format: " + dateObject, e);
            }
        } else if (dateObject instanceof Date) {
            return (Date) dateObject;
        } else {
            throw new RuntimeException("Unexpected type for date field: " + key);
        }
    }
}
