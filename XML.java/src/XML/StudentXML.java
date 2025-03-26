package XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class StudentXML {
    public static void main(String[] args) {
        try {
            // Chuẩn bị file XML
            File xmlFile = new File("D:/java/XML.java/src/students.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;
            Element rootElement;

            // Kiểm tra xem file đã tồn tại chưa
            if (xmlFile.exists()) {
                doc = dBuilder.parse(xmlFile);
                rootElement = doc.getDocumentElement();
            } else {
                doc = dBuilder.newDocument();
                rootElement = doc.createElement("students");
                doc.appendChild(rootElement);
            }

            // Nhập thông tin sinh viên từ người dùng
            Scanner scanner = new Scanner(System.in);

            System.out.println("Nhập thông tin sinh viên:");
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Tên: ");
            String name = scanner.nextLine();

            System.out.print("Tuổi: ");
            String age = scanner.nextLine();

            System.out.print("Chuyên ngành: ");
            String major = scanner.nextLine();

            // Tạo element student mới
            Element student = doc.createElement("student");
            student.setAttribute("id", id);

            // Tạo và thêm các element con
            Element nameElement = doc.createElement("name");
            nameElement.setTextContent(name);
            student.appendChild(nameElement);

            Element ageElement = doc.createElement("age");
            ageElement.setTextContent(age);
            student.appendChild(ageElement);

            Element majorElement = doc.createElement("major");
            majorElement.setTextContent(major);
            student.appendChild(majorElement);

            // Thêm student vào root
            rootElement.appendChild(student);

            // Ghi vào file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Để định dạng XML đẹp hơn
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("Đã thêm thông tin sinh viên vào file students.xml thành công!");

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
