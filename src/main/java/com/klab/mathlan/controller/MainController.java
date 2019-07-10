package com.klab.mathlan.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    private Stage staged;

    @FXML
    private Button joinBtn;


    public void start (Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage.setTitle("MathLAN");
        stage.setScene(new Scene(root, 450, 450));
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setResizable(true);
        stage.show();

        staged = stage;

    }

    @FXML
    public void join(ActionEvent actionEvent) {
        try {
            PlayerListController playerListController = new PlayerListController();
            playerListController.start(staged);
        } catch(Exception exception) {
            System.out.println("error");
        }

    }
}
