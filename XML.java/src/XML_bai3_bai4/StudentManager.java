package XML_bai3_bai4;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) {
        try {
            // Tạo file XML mẫu nếu chưa có
            createSampleXML();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Hiển thị menu
                System.out.println("\n=== QUẢN LÝ SINH VIÊN ===");
                System.out.println("1. Xóa sinh viên theo ID");
                System.out.println("2. Cập nhật thông tin sinh viên theo ID");
                System.out.println("3. Thoát");
                System.out.print("Chọn hành động (1-3): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Xóa ký tự xuống dòng

                if (choice == 3) {
                    System.out.println("Đã thoát chương trình!");
                    break;
                }

                // Đọc file XML
                File xmlFile = new File("D:/java/XML.java/src/studentManager.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                switch (choice) {
                    case 1: // Xóa sinh viên
                        System.out.print("Nhập ID sinh viên cần xóa: ");
                        String idToDelete = scanner.nextLine();

                        NodeList studentList = doc.getElementsByTagName("student");
                        boolean deleted = false;

                        for (int i = 0; i < studentList.getLength(); i++) {
                            Node student = studentList.item(i);
                            if (student.getNodeType() == Node.ELEMENT_NODE) {
                                Element studentElement = (Element) student;
                                String id = studentElement.getElementsByTagName("id").item(0).getTextContent();

                                if (id.equals(idToDelete)) {
                                    student.getParentNode().removeChild(student);
                                    System.out.println("Đã xóa sinh viên có ID: " + idToDelete);
                                    deleted = true;
                                    break;
                                }
                            }
                        }

                        if (!deleted) {
                            System.out.println("Không tìm thấy sinh viên với ID: " + idToDelete);
                        }
                        break;

                    case 2: // Cập nhật sinh viên
                        System.out.print("Nhập ID sinh viên cần cập nhật: ");
                        String idToUpdate = scanner.nextLine();

                        System.out.print("Nhập tên mới: ");
                        String newName = scanner.nextLine();
                        System.out.print("Nhập mã sinh viên mới: ");
                        String newMsv = scanner.nextLine();
                        System.out.print("Nhập lớp mới: ");
                        String newClass = scanner.nextLine();

                        studentList = doc.getElementsByTagName("student");
                        boolean updated = false;

                        for (int i = 0; i < studentList.getLength(); i++) {
                            Node student = studentList.item(i);
                            if (student.getNodeType() == Node.ELEMENT_NODE) {
                                Element studentElement = (Element) student;
                                String id = studentElement.getElementsByTagName("id").item(0).getTextContent();

                                if (id.equals(idToUpdate)) {
                                    studentElement.getElementsByTagName("name").item(0).setTextContent(newName);
                                    studentElement.getElementsByTagName("msv").item(0).setTextContent(newMsv);
                                    studentElement.getElementsByTagName("class").item(0).setTextContent(newClass);
                                    System.out.println("Đã cập nhật thông tin sinh viên có ID: " + idToUpdate);
                                    updated = true;
                                    break;
                                }
                            }
                        }

                        if (!updated) {
                            System.out.println("Không tìm thấy sinh viên với ID: " + idToUpdate);
                        }
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                        continue;
                }

                // Ghi lại nội dung vào file XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("D:/java/XML.java/src/studentManager.xml"));
                transformer.transform(source, result);
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm tạo file XML mẫu
    private static void createSampleXML() {
        try {
            File file = new File("D:/java/XML.java/src/studentManager.xml");
            if (!file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element root = doc.createElement("students");
                doc.appendChild(root);

                // Tạo sinh viên mẫu
                Element student1 = doc.createElement("student");
                root.appendChild(student1);

                Element id1 = doc.createElement("id");
                id1.appendChild(doc.createTextNode("1"));
                student1.appendChild(id1);

                Element name1 = doc.createElement("name");
                name1.appendChild(doc.createTextNode("Nguyễn Văn Phong"));
                student1.appendChild(name1);

                Element msv1 = doc.createElement("msv");
                msv1.appendChild(doc.createTextNode("24ITE01"));
                student1.appendChild(msv1);

                Element class1 = doc.createElement("class");
                class1.appendChild(doc.createTextNode("24ITe"));
                student1.appendChild(class1);

                // Ghi vào file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
