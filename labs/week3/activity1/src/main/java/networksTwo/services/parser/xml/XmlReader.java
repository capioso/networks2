package networksTwo.services.parser.xml;
import networksTwo.domain.enums.OpSys;
import networksTwo.domain.enums.Priority;
import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;
import networksTwo.domain.models.UserDataStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class XmlReader {

    public static UserDataStream fromXml(String xml) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));

            Element rootElement = document.getDocumentElement();

            List<UserData> userDataList = new ArrayList<>();

            NodeList userNodes = rootElement.getElementsByTagName("UserData");
            for (int i = 0; i < userNodes.getLength(); i++) {
                Element userElement = (Element) userNodes.item(i);

                UUID id = UUID.fromString(getElementValue(userElement, "id"));
                String name = getElementValue(userElement, "name");
                String email = getElementValue(userElement, "email");
                String ipAddress = getElementValue(userElement, "ipAddress");
                String deviceName = getElementValue(userElement, "deviceName");
                OpSys os = OpSys.valueOf(getElementValue(userElement, "os"));
                String endConnection = getElementValue(userElement, "endConnection");
                String startConnection = getElementValue(userElement, "startConnection");

                List<Error> errorList = new ArrayList<>();
                NodeList errorNodes = userElement.getElementsByTagName("Error");
                for (int j = 0; j < errorNodes.getLength(); j++) {
                    Element errorElement = (Element) errorNodes.item(j);

                    UUID errorId = UUID.fromString(getElementValue(errorElement, "id"));
                    Priority priority = Priority.valueOf(getElementValue(errorElement, "priority"));
                    String message = getElementValue(errorElement, "message");
                    String errorDate = getElementValue(errorElement, "date");

                    Error error = new Error(errorId, priority, message, parseDateFromString(errorDate));
                    errorList.add(error);
                }

                UserData userData = new UserData(id, name, email, ipAddress, deviceName, os, parseDateFromString(endConnection), parseDateFromString(startConnection), errorList);
                userDataList.add(userData);
            }

            return new UserDataStream(userDataList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    private static Date parseDateFromString(String dateStr) {
        String[] parts = dateStr.split(" ");
        int day = Integer.parseInt(parts[2]);
        int year = Integer.parseInt(parts[5]);
        int hour = Integer.parseInt(parts[3].substring(0, 2));
        int minute = Integer.parseInt(parts[3].substring(3, 5));
        int second = Integer.parseInt(parts[3].substring(6, 8));
        String monthStr = parts[1];

        int month = getMonthNumber(monthStr);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.set(year, month, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    private static int getMonthNumber(String monthStr) {
        return switch (monthStr) {
            case "Jan" -> Calendar.JANUARY;
            case "Feb" -> Calendar.FEBRUARY;
            case "Mar" -> Calendar.MARCH;
            case "Apr" -> Calendar.APRIL;
            case "May" -> Calendar.MAY;
            case "Jun" -> Calendar.JUNE;
            case "Jul" -> Calendar.JULY;
            case "Aug" -> Calendar.AUGUST;
            case "Sep" -> Calendar.SEPTEMBER;
            case "Oct" -> Calendar.OCTOBER;
            case "Nov" -> Calendar.NOVEMBER;
            case "Dec" -> Calendar.DECEMBER;
            default -> throw new IllegalArgumentException("Invalid month: " + monthStr);
        };
    }
}
