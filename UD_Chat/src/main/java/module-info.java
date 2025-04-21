module com.example.ud_chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ud_chat to javafx.fxml;
    exports com.example.ud_chat;
}