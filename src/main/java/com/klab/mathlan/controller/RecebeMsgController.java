package com.klab.mathlan.controller;

import java.io.InputStream;
import java.util.Scanner;

public class RecebeMsgController implements Runnable {
    private InputStream cliente;
    private ServerMatchController servidor;

    public RecebeMsgController(InputStream cliente, ServerMatchController servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        //ao receber uma msg, envia para todos os clientes
        Scanner s = new Scanner(this.cliente);
        while(s.hasNext()) {
            servidor.mandaMsg(s.nextLine());
        }
        s.close();
    }
}
