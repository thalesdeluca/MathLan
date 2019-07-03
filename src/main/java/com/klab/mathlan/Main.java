package com.klab.mathlan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
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

    @FXML
    private void host() {

    }

    public static void main(String[] args) {
        launch(args);
    }

}
