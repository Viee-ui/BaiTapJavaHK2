module com.example.order_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.order_management to javafx.fxml;
    exports com.example.order_management;
}