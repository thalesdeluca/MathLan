package com.klab.mathlan.controller;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable{
    private InputStream server;
    private CodeMap codeMap;

    public Recebedor(InputStream server, CodeMap codeMap) {
        this.server = server;
        this.codeMap = codeMap;
    }

    //recebe mensagens do servidor e printa
    public void run() {
        Scanner s = new Scanner(this.server);
        while(s.hasNextLine()) {
            codeMap.assignMessage(s.nextLine());
        }
    }
}
