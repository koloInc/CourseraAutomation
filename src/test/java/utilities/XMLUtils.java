package utilities;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XMLUtils {

    private Document doc;

    public XMLUtils(String fileName) {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName;
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load XML file: " + e.getMessage());
        }
    }

    public String getElementValue(String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        } else {
            return null;
        }
    }

    public String getAttributeValue(String tagName, String attributeName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getAttribute(attributeName);
            }
        }
        return null;
    }

    public void printAllData() {
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(node.getNodeName() + ": " + node.getTextContent().trim());
            }
        }
    }

}
