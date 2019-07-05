package com.klab.mathlan.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerListController {
    private ArrayList<>

    @FXML
    private VBox container;

    @FXML
    private Button startBtn;

    @FXML
    private Button closeBtn;

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage.setTitle("MathLAN");
        stage.setScene(new Scene(root, 450, 450));
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setResizable(true);
        stage.show();
    }
}
