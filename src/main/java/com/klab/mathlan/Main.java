package com.klab.mathlan;

import com.klab.mathlan.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainController mainController = new MainController();
        mainController.start(stage);
    }

    @FXML
    private void host() {

    }

    public static void main(String[] args) {
        launch(args);
    }

}
