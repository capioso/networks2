package networksTwo;

import networksTwo.domain.enums.OpSys;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.UserData;
import networksTwo.domain.models.UserDataStream;
import networksTwo.services.userDataStream.UserDataStreamService;
import org.json.JSONArray;

import java.io.StringWriter;
import java.time.Instant;
import java.util.Date;

import static networksTwo.services.parser.json.JsonReader.fromJson;
import static networksTwo.services.parser.json.JsonWriter.toJson;
import static networksTwo.services.parser.xml.XmlReader.fromXml;
import static networksTwo.services.parser.xml.XmlWriter.toXml;

public class Main {
    public static void main(String[] args) {
        // Raw format
        System.out.println("\nRAW FORMAT");
        UserDataStreamService userDataStreamService = new UserDataStreamService();
        UserData userData1 = userDataStreamService.addUserDataToStream("capi", "capi@gmail.com", "0.0.0.0", "celu", OpSys.ANDROID, Date.from(Instant.now()), Date.from(Instant.now()));
        UserData userData2 = userDataStreamService.addUserDataToStream("bara", "bara@gmail.com", "0.0.0.0", "phone", OpSys.IOS, Date.from(Instant.now()), Date.from(Instant.now()));
        userDataStreamService.addErrorToUserData(userData1, Priority.LOW, "No a serious problem");
        userDataStreamService.addErrorToUserData(userData2, Priority.MEDIUM, "Problem Medium");
        userDataStreamService.addErrorToUserData(userData2, Priority.HIGH, "Serious Problem");
        userDataStreamService.printAllUserDataStreams();

        // Raw to Json
        System.out.println("\nRAW TO JSON");
        JSONArray jsonArray = toJson(userDataStreamService.getUserDataStream());
        System.out.println(jsonArray);

        // Json to Raw
        System.out.println("\nJSON TO RAW");
        UserDataStream recovered = fromJson(jsonArray);
        UserDataStreamService userDataStreamService2 = new UserDataStreamService(recovered);
        userDataStreamService2.printAllUserDataStreams();

        // Raw to Xml
        System.out.println("\nRAW TO XML");
        String xml = toXml(userDataStreamService.getUserDataStream());
        System.out.println(xml);

        // Xml to Raw
        System.out.println("\nXML TO RAW");
        UserDataStream recovered2 = fromXml(xml);
        if (recovered2 != null){
            UserDataStreamService userDataStreamService3 = new UserDataStreamService(recovered2);
            userDataStreamService3.printAllUserDataStreams();
        }

    }
}