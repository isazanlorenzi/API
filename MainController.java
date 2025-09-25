package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController<TextField, outputLabel, Label> {

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    @SuppressWarnings("hiding")
    @FXML
    private <outputLabel> void onClick() {
        String text = ((Object) inputField).getText();
        ((Object) outputLabel).setText("Você digitou: " + text);

        // 🔗 Aqui você pode chamar sua lógica de API, ex:
        // ApiService service = new ApiService();
        // String result = service.getSomething(text);
        // outputLabel.setText(result);
    }
}
