package com.example.ud_chat;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML private TextArea messageArea;
    @FXML private TextField inputField, ipField, portField;

    private ChatClient client = new ChatClient();

    @FXML
    public void onConnectClick() {
        String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());

        boolean connected = client.connect(ip, port);
        if (connected) {
            messageArea.appendText("Đã kết nối đến server\n");
            client.receiveMessages(message -> {
                messageArea.appendText(message + "\n");
            });
        } else {
            messageArea.appendText("Không thể kết nối\n");
        }
    }

    @FXML
    public void onSendClick() {
        String msg = inputField.getText();
        if (!msg.isEmpty()) {
            client.sendMessage(msg);
            inputField.clear();
        }
    }
}
