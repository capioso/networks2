package networksTwo.services.parser.xml;

import networksTwo.domain.models.Error;
import networksTwo.domain.models.UserData;
import networksTwo.domain.models.UserDataStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XmlWriter {
    public static String toXml(UserDataStream userDataStream) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("UserDataStream");
            document.appendChild(rootElement);

            for (UserData userData : userDataStream.userStream()) {
                Element userElement = document.createElement("UserData");
                rootElement.appendChild(userElement);

                createElement(document, userElement, "id", userData.id().toString());
                createElement(document, userElement, "name", userData.name());
                createElement(document, userElement, "email", userData.email());
                createElement(document, userElement, "ipAddress", userData.ipAddress());
                createElement(document, userElement, "deviceName", userData.deviceName());
                createElement(document, userElement, "os", userData.os().name());
                createElement(document, userElement, "endConnection", userData.endConnection().toString());
                createElement(document, userElement, "startConnection", userData.startConnection().toString());

                Element errorLogElement = document.createElement("ErrorLog");
                userElement.appendChild(errorLogElement);

                for (Error error : userData.errorLog()) {
                    Element errorElement = document.createElement("Error");
                    errorLogElement.appendChild(errorElement);

                    createElement(document, errorElement, "id", error.id().toString());
                    createElement(document, errorElement, "priority", error.priority().name());
                    createElement(document, errorElement, "message", error.message());
                    createElement(document, errorElement, "date", error.date().toString());
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            return writer.toString();
        }catch (Exception e){
            return null;
        }
    }

    private static void createElement(Document document, Element parent, String name, String value) {
        if (value != null) {
            Element element = document.createElement(name);
            element.appendChild(document.createTextNode(value));
            parent.appendChild(element);
        }
    }
}
