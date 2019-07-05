package com.klab.mathlan;

import com.klab.mathlan.controller.MainController;
import com.klab.mathlan.controller.PlayerMatchController;
import com.klab.mathlan.controller.ServerMatchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;
import java.net.UnknownHostException;


public class Main {

/*    @Override
    public void start(Stage stage) throws Exception {
        MainController mainController = new MainController();
        mainController.start(stage);
    }

    @FXML
    private void host() {

    }*/

    public static void main(String[] args) throws UnknownHostException, IOException {
            new ServerMatchController(62548).executa();

    }

}
