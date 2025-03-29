package com.example.thuviensachxml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private static final String XML_FILE = "books.xml";
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        loadBooksFromXML();
    }

    private void loadBooksFromXML() {
        try {
            File file = new File(XML_FILE);
            if (!file.exists()) {
                initializeEmptyXML(file);
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    try {
                        Book book = new Book(
                                getTagValue("title", element),
                                getTagValue("author", element),
                                Integer.parseInt(getTagValue("year", element)),
                                getTagValue("publisher", element),
                                Integer.parseInt(getTagValue("pages", element)),
                                getTagValue("genre", element),
                                Double.parseDouble(getTagValue("price", element)),
                                getTagValue("isbn", element)
                        );
                        books.add(book);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid data format in XML for book at index " + i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private void initializeEmptyXML(File file) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("books");
        doc.appendChild(rootElement);
        saveXML(doc);
    }

    public void addBook(Book book) throws IllegalArgumentException {
        if (books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()))) {
            throw new IllegalArgumentException("ISBN already exists!");
        }
        books.add(book);
        saveToXML();
    }

    public void updateBook(String isbn, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, updatedBook);
                saveToXML();
                return;
            }
        }
    }

    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        saveToXML();
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> searchBooks(String query) {
        String lowerQuery = query.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerQuery) ||
                        book.getAuthor().toLowerCase().contains(lowerQuery) ||
                        book.getIsbn().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    private void saveToXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("books");
            doc.appendChild(rootElement);

            for (Book book : books) {
                Element bookElement = doc.createElement("book");
                rootElement.appendChild(bookElement);

                appendElement(doc, bookElement, "title", book.getTitle());
                appendElement(doc, bookElement, "author", book.getAuthor());
                appendElement(doc, bookElement, "year", String.valueOf(book.getYear()));
                appendElement(doc, bookElement, "publisher", book.getPublisher());
                appendElement(doc, bookElement, "pages", String.valueOf(book.getPages()));
                appendElement(doc, bookElement, "genre", book.getGenre());
                appendElement(doc, bookElement, "price", String.valueOf(book.getPrice()));
                appendElement(doc, bookElement, "isbn", book.getIsbn());
            }

            saveXML(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendElement(Document doc, Element parent, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }

    private void saveXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(XML_FILE));
        transformer.transform(source, result);
    }
}