package com.klab.mathlan;

import com.klab.mathlan.controller.MainController;
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController mainController = new MainController();
        mainController.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
