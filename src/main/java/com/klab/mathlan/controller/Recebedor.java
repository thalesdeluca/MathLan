package com.klab.mathlan.controller;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable{
    private InputStream server;

    public Recebedor(InputStream server) {
        this.server = server;
    }

    //recebe mensagens do servidor e printa
    public void run() {
        Scanner s = new Scanner(this.server);
        while(s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
    }
}
