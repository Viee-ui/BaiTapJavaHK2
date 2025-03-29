module com.example.thuviensachxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml; // Thêm module java.xml để sử dụng các package như org.w3c.dom, javax.xml.*

    opens com.example.thuviensachxml to javafx.fxml;
    exports com.example.thuviensachxml;
}