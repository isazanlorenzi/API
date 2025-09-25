package com.example.app;

import com.example.app.service.ApiService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    private final ApiService service = new ApiService();

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    @FXML
    private void onClick() {
        String text = inputField.getText();

        // Usa a API real em vez de texto fixo
        String result = service.getGreeting(text);

        outputLabel.setText(result);
    }
}
