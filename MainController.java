package com.example.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    @FXML
    private void onClick() {
        String text = inputField.getText();
        outputLabel.setText("Você digitou: " + text);

        // 🔗 Aqui você pode chamar sua lógica de API, ex:
        // ApiService service = new ApiService();
        // String result = service.getSomething(text);
        // outputLabel.setText(result);
    }
}
