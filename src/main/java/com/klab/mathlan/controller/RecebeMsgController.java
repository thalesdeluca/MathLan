package com.klab.mathlan.controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RecebeMsgController implements Runnable {
    private InputStream cliente;
    private ServerMatchController servidor;
    private PrintStream clienteObj;

    public RecebeMsgController(InputStream cliente, ServerMatchController servidor, PrintStream clienteObj) {
        this.cliente = cliente;
        this.servidor = servidor;
        this.clienteObj = clienteObj;
    }

    public void run() {
        //ao receber uma msg, envia para todos os clientes
        Scanner s = new Scanner(this.cliente);
        while(s.hasNext()) {
            String msgConvertida = s.next();
            try {
                servidor.recebeResult(Integer.parseInt(msgConvertida), clienteObj);
            } catch (Exception ex) {
                System.out.println("escreveu letra!");
            }
        }
        s.close();
    }
}
