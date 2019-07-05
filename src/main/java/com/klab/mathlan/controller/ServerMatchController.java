package com.klab.mathlan.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMatchController {

    private int porta;
    private List<PrintStream> clientes;

    public ServerMatchController(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }

    public void executa() throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta " + this.porta + " aberta");

        while(clientes.size() < 4) {
            //aceita um client
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            //adiciona o client a lista de clients
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            //Cria um recebedor de mensagens em uma nova thread para cada cliente.
            //O recebedor ao receber uma msg chama o metodo mandaMsg que envia para
            //todos os clients da lista.
            RecebeMsgController msg = new RecebeMsgController(cliente.getInputStream(), this);
            new Thread(msg).start();
        }
    }

    public void mandaMsg(String msg) {
        for(PrintStream cliente : this.clientes) {
            cliente.println(msg);
        }
    }
}
