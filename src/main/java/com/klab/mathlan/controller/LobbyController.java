package com.klab.mathlan.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.*;
import java.util.Enumeration;

public class LobbyController {

    private InetAddress server;

    @FXML
    private VBox container;

    @FXML
    private Button closeBtn;

    @FXML
    private Button connectBtn;

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("lobby.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage.setTitle("MathLAN");
        stage.setScene(new Scene(root, 450, 450));
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setResizable(true);
        stage.show();


    }

    public void searchServers () {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            byte[] data = "SEARCH_SERVERS_MATHLAN".getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 6200);
            socket.send(packet);

            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                if(networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                for(InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if(broadcast == null) {
                        continue;
                    }

                    try {
                        DatagramPacket packetBroad = new DatagramPacket(data, data.length, broadcast, 6200);
                        socket.send(packetBroad);
                    } catch(Exception exception) {

                    }
                }
            }
            byte[] buffer = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);

            String message = new String(receivePacket.getData()).trim();
            if(message.equals("FOUND_SERVER_MATHLAN")) {
                server = receivePacket.getAddress();
            }

            System.out.println(server.getHostAddress());
            socket.close();
        } catch(Exception exception) {
            System.out.println("Could not search ofr servers");
        }
    }
}
