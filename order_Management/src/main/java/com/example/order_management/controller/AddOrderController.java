package com.example.order_management.controller;

import com.example.order_management.dao.CustomerDAO;
import com.example.order_management.dao.OrderDAO;
import com.example.order_management.dao.ProductDAO;
import com.example.order_management.model.Customer;
import com.example.order_management.model.Order;
import com.example.order_management.model.OrderItem;
import com.example.order_management.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController {

    @FXML private ComboBox<Customer> cbCustomer;
    @FXML private ComboBox<Product> cbProduct;
    @FXML private TextField tfQuantity;
    @FXML private TableView<OrderItem> tableItems;
    @FXML private TableColumn<OrderItem, Integer> colProductId;
    @FXML private TableColumn<OrderItem, Integer> colQuantity;
    @FXML private Label lblMessage;

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbCustomer.setItems(FXCollections.observableArrayList((Customer) new Customer().getAllCustomer()));
        cbProduct.setItems(FXCollections.observableArrayList((Product) new ProductDAO().getAllProducts()));

        colProductId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getProductId()).asObject());
        colQuantity.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject());

        tableItems.setItems(orderItems);
    }

    @FXML
    public void handleAddItem() {
        Product product = cbProduct.getValue();
        int quantity = Integer.parseInt(tfQuantity.getText());

        if (product == null || quantity <= 0) {
            lblMessage.setText("Chọn sản phẩm và nhập số lượng hợp lệ.");
            return;
        }

        orderItems.add(new OrderItem(0, product.getId(), quantity));
        lblMessage.setText("Đã thêm sản phẩm.");
    }

    @FXML
    public void handleSubmitOrder() {
        Customer customer = cbCustomer.getValue();

        if (customer == null || orderItems.isEmpty()) {
            lblMessage.setText("Chọn khách hàng và ít nhất 1 sản phẩm.");
            return;
        }

        Order order = new Order(customer.getId(), LocalDateTime.now());
        OrderDAO orderDAO = new OrderDAO();

        int orderId = orderDAO.createOrder(order);
        if (orderId != -1) {
            for (OrderItem item : orderItems) {
                item.setOrderId(orderId);
            }
            orderDAO.addOrderItems(orderId, new ArrayList<>(orderItems));
            double total = orderDAO.calculateOrderTotal(orderId);
            lblMessage.setText("Tạo đơn hàng thành công! Tổng tiền: " + total + "₫");
            orderItems.clear();
        } else {
            lblMessage.setText("Tạo đơn hàng thất bại.");
        }
    }
}
