package XML_bai2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("D:/java/XML.java/src/company.xml");
            document.getDocumentElement().normalize();

            NodeList employeeList = document.getElementsByTagName("employee");

            for (int i = 0; i < employeeList.getLength(); i++) {
                Node employeeNode = employeeList.item(i);

                if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element employeeElement = (Element) employeeNode;

                    String id = employeeElement.getAttribute("id");
                    System.out.println("Employee ID: " + id);

                    String name = employeeElement.getElementsByTagName("name")
                            .item(0).getTextContent();
                    System.out.println("Name: " + name);

                    Element contactElement = (Element) employeeElement
                            .getElementsByTagName("contact").item(0);
                    String email = contactElement.getElementsByTagName("email")
                            .item(0).getTextContent();
                    String phone = contactElement.getElementsByTagName("phone")
                            .item(0).getTextContent();
                    System.out.println("Email: " + email);
                    System.out.println("Phone: " + phone);

                    Element deptElement = (Element) employeeElement
                            .getElementsByTagName("department").item(0);
                    String deptName = deptElement.getElementsByTagName("name")
                            .item(0).getTextContent();
                    String location = deptElement.getElementsByTagName("location")
                            .item(0).getTextContent();
                    System.out.println("Department: " + deptName);
                    System.out.println("Location: " + location);

                    System.out.println("-------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
