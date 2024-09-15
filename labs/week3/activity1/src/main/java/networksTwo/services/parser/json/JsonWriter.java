package networksTwo.services.parser.json;

import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;
import org.json.JSONArray;
import org.json.JSONObject;
import networksTwo.domain.models.UserDataStream;

public class JsonWriter {
    public static JSONArray toJson(UserDataStream userDataStream) {
        JSONArray jsonArray = new JSONArray();
        for (UserData userData : userDataStream.userStream()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", userData.id());
            jsonObject.put("name", userData.name());
            jsonObject.put("email", userData.email());
            jsonObject.put("ipAddress", userData.ipAddress());
            jsonObject.put("deviceName", userData.deviceName());
            jsonObject.put("os", userData.os().name());
            jsonObject.put("endConnection", userData.endConnection());
            jsonObject.put("startConnection", userData.startConnection());

            JSONArray errorLogArray = new JSONArray();
            for (Error error : userData.errorLog()) {
                JSONObject errorJson = new JSONObject();
                errorJson.put("id", error.id());
                errorJson.put("priority", error.priority().name());
                errorJson.put("message", error.message());
                errorJson.put("date", error.date());
                errorLogArray.put(errorJson);
            }
            jsonObject.put("errorLog", errorLogArray);

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
