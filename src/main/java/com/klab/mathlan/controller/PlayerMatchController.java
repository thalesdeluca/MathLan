package com.klab.mathlan.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class PlayerMatchController {
    private String host;
    private int port;

    public PlayerMatchController(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void executa() throws UnknownHostException, IOException {
        Socket client = new Socket(this.host, this.port);
        System.out.println("CLIENTE TA DENTRO!!!!");

        Recebedor recebedor = new Recebedor(client.getInputStream());
        new Thread(recebedor).start();

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(client.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();
        client.close();
    }
}
