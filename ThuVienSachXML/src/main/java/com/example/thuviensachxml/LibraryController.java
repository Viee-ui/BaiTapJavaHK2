package com.example.thuviensachxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, Integer> yearCol;
    @FXML private TableColumn<Book, String> publisherCol;
    @FXML private TableColumn<Book, Integer> pagesCol;
    @FXML private TableColumn<Book, String> genreCol;
    @FXML private TableColumn<Book, Double> priceCol;
    @FXML private TableColumn<Book, String> isbnCol;
    @FXML private TextField searchField;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    private BookManager bookManager;
    private ObservableList<Book> bookData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookManager = new BookManager();
        bookData = FXCollections.observableArrayList(bookManager.getBooks());

        // Cấu hình các cột của TableView
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("pages"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        tableView.setItems(bookData);

        // Xử lý tìm kiếm
        searchField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                bookData.setAll(bookManager.getBooks());
            } else {
                bookData.setAll(bookManager.searchBooks(newValue));
            }
        });
    }

    @FXML
    private void handleAdd() {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Add New Book");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = createInputGrid(null);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    return createBookFromGrid(grid);
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid input: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(book -> {
            try {
                bookManager.addBook(book);
                bookData.setAll(bookManager.getBooks());
                searchField.clear();
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        });
    }

    @FXML
    private void handleUpdate() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a book to update.");
            return;
        }

        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Update Book");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = createInputGrid(selectedBook);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                try {
                    return createBookFromGrid(grid);
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid input: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(book -> {
            bookManager.updateBook(selectedBook.getIsbn(), book);
            bookData.setAll(bookManager.getBooks());
            searchField.clear();
        });
    }

    @FXML
    private void handleDelete() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a book to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete " + selectedBook.getTitle() + "?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                bookManager.deleteBook(selectedBook.getIsbn());
                bookData.setAll(bookManager.getBooks());
                searchField.clear();
            }
        });
    }

    private GridPane createInputGrid(Book book) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleField = new TextField(book != null ? book.getTitle() : "");
        TextField authorField = new TextField(book != null ? book.getAuthor() : "");
        TextField yearField = new TextField(book != null ? String.valueOf(book.getYear()) : "");
        TextField publisherField = new TextField(book != null ? book.getPublisher() : "");
        TextField pagesField = new TextField(book != null ? String.valueOf(book.getPages()) : "");
        TextField genreField = new TextField(book != null ? book.getGenre() : "");
        TextField priceField = new TextField(book != null ? String.valueOf(book.getPrice()) : "");
        TextField isbnField = new TextField(book != null ? book.getIsbn() : "");
        isbnField.setDisable(book != null);

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("Year:"), 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(new Label("Publisher:"), 0, 3);
        grid.add(publisherField, 1, 3);
        grid.add(new Label("Pages:"), 0, 4);
        grid.add(pagesField, 1, 4);
        grid.add(new Label("Genre:"), 0, 5);
        grid.add(genreField, 1, 5);
        grid.add(new Label("Price:"), 0, 6);
        grid.add(priceField, 1, 6);
        grid.add(new Label("ISBN:"), 0, 7);
        grid.add(isbnField, 1, 7);

        return grid;
    }

    private Book createBookFromGrid(GridPane grid) throws NumberFormatException {
        TextField titleField = (TextField) grid.getChildren().get(1);
        TextField authorField = (TextField) grid.getChildren().get(3);
        TextField yearField = (TextField) grid.getChildren().get(5);
        TextField publisherField = (TextField) grid.getChildren().get(7);
        TextField pagesField = (TextField) grid.getChildren().get(9);
        TextField genreField = (TextField) grid.getChildren().get(11);
        TextField priceField = (TextField) grid.getChildren().get(13);
        TextField isbnField = (TextField) grid.getChildren().get(15);

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String isbn = isbnField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            throw new IllegalArgumentException("Title, Author, and ISBN cannot be empty.");
        }

        return new Book(
                title,
                author,
                Integer.parseInt(yearField.getText().trim()),
                publisherField.getText().trim(),
                Integer.parseInt(pagesField.getText().trim()),
                genreField.getText().trim(),
                Double.parseDouble(priceField.getText().trim()),
                isbn
        );
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }
}