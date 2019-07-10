package com.klab.mathlan.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerMatchController {

    private int porta;
    private String exp;
    private int result;
    private List<PrintStream> clientes;
    private ArrayList<Thread> msgThread = new ArrayList<>();
    private boolean flag = true;
    private int[] points = new int[]{0,0,0,0};

    public ServerMatchController(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }

    public void executa() throws IOException {
        int index = 0;
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta " + this.porta + " aberta");

        while(clientes.size() < 2) {
            //aceita um client
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            //adiciona o client a lista de clients
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            //Cria um recebedor de mensagens em uma nova thread para cada cliente.
            //O recebedor ao receber uma msg chama o metodo mandaMsg que envia para
            //todos os clients da lista.
            RecebeMsgController msg = new RecebeMsgController(cliente.getInputStream(), this, ps);
            msgThread.add(new Thread(msg));
            index = msgThread.size() - 1;
            msgThread.get(index).start();
        }
        initGame();
    }

    public void initGame() {
        if(this.clientes.size() == 2) {
            for (int i = 3; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                    mandaMsg("CNDSe prepare, faltam " + i + " segundos!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Calculation exp = new Calculation();
            this.exp = exp.generateCalculation();
            this.result = exp.getResult();

            for (int i = 0; i < clientes.size(); i++) {
                clientes.get(i).println("STRVocê é o player " + i);
            }
            mandaMsg("OPR" + this.exp);
            flag = true;
        }
    }

    public void mandaMsg(String msg) {
        for(PrintStream cliente : this.clientes) {
            cliente.println(msg);
        }
    }

    public void recebeResult(Integer resultado, PrintStream cliente) {
        cliente.println("Recebido!");
        if(resultado == this.result) {
            cliente.println("Resultado Certo!");
            if(flag) {
                cliente.println("Você ganhou o ponto");
                flag = false;
                for(int i = 0; i < clientes.size(); i++) {
                    if(cliente == clientes.get(i)) {
                        points[i]++;
                    }
                }
                mandaMsg("PTS" + points[0] + "," + points[1] + "," + points[2] + "," + points[3]);
                initGame();
                return;
            } else {
                cliente.println("Alguém foi mais rápido que você");
                return;
            }
        } else {
            cliente.println("Resultado errado!");
        }
    }
}
