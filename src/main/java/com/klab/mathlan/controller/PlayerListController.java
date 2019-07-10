package com.klab.mathlan.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class PlayerListController implements ScreenController{

    private Recebedor receiver;
    private Stage staged;

    @FXML
    private TextField ip;

    @FXML
    private TextField port;

    @FXML
    private Label status;

    private Socket me;

    private CodeMap codeMap;


    //playerMatch
    @FXML
    private Label operation;

    @FXML
    private Label playerAPoints;

    @FXML
    private Label playerBPoints;

    @FXML
    private Label playerCPoints;
    @FXML
    private Label playerDPoints;

    @FXML
    private TextField answer;

    @FXML
    private Label player;

    private String[] points;


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

        staged = stage;
    }

    public void startMatch(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("player-match.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage.setTitle("MathLAN");
        stage.setScene(new Scene(root, 450, 450));
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setResizable(true);
        stage.show();

        answer.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    send();
                } catch(IOException io) {
                    System.out.println("Erro ao enviar");
                }

            }
        });
    }
    @FXML
    public void sendAction(ActionEvent event) {
        try {
            send();
        }catch(Exception ex) {
            System.out.println("Erro ao enviar");
        }
    }

    public void send() throws IOException{
        PrintStream saida = new PrintStream(me.getOutputStream());
        saida.println(answer.getText());
        answer.clear();

    }

    @FXML
    public void handleConnect() throws IOException{
            executa(ip.getText(), Integer.parseInt((port.getText())));

    }

    private void executa(String ip, int port) throws IOException {
        Socket client = new Socket(ip, port);
        System.out.println("CLIENTE TA DENTRO!!!!");

        //Cria um recebedor para receber mensagens do servidor
        //coloca o recebedor em uma thread

        CodeMap codeMap = new CodeMap();
        codeMap.setController(this);

        this.codeMap = codeMap;

        Recebedor recebedor = new Recebedor(client.getInputStream(), codeMap);
        new Thread(recebedor).start();

        status.setText("Connected to "+ip+":"+port);

        receiver = recebedor;
        me = client;
    }


    @Override
    public void assignMessage(String message){
        System.out.println(message);
        String type = message.substring(0, 3);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch(type) {
                    case "CND":
                        String payload = message.substring(3);
                        status.setText(payload);
                        break;
                    case "STR":
                        try {
                            startMatch(staged);
                            player.setText(message.substring(3));
                        } catch(IOException exception) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Ocorreu um erro ao Conectar ao sevidor", ButtonType.OK);
                            alert.showAndWait();
                        }
                        break;

                    case "OPR":
                        System.out.println("operation set");
                        operation.setText(message.substring(3));
                        if(points != null) {
                            playerAPoints.setText(points[0] != null ? points[0] : "-");
                            playerBPoints.setText(points[1] != null ? points[1] : "-");
                            playerCPoints.setText(points[2] != null ? points[2] : "-");
                            playerDPoints.setText(points[3] != null ? points[3] : "-");
                        }
                        break;
                    case "PTS":
                        System.out.println("test");
                        String[] msg = message.substring(3).split("[,]");
                        points = msg;
                        playerAPoints.setText(msg[0] != null ? msg[0] : "-");
                        playerBPoints.setText(msg[1] != null ? msg[1] : "-");
                        playerCPoints.setText(msg[2] != null ? msg[2] : "-");
                        playerDPoints.setText(msg[3] != null ? msg[3] : "-");
                        break;

                    default:
                        System.out.println("Not mapped: " + message);
                        break;
                }
            }
        });
    }
}
