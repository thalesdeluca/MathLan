package com.klab.mathlan.controller;

import com.klab.mathlan.thread.DiscoveryThread;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class PlayerListController {
    private ArrayList<InetAddress> players;

    @FXML
    private VBox container;

    @FXML
    private Button startBtn;

    @FXML
    private Button closeBtn;

    public PlayerListController () {
        players = new ArrayList<>();
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("player-list.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage.setTitle("MathLAN");
        stage.setScene(new Scene(root, 450, 450));
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setResizable(true);
        stage.show();

        Thread discoveryThread = new Thread(DiscoveryThread.getInstance());
        discoveryThread.start();
    }
}
