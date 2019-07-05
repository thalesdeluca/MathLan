package com.klab.mathlan.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscoveryThread implements Runnable {
    private DatagramSocket socket;
    @Override
    public void run() {
        try {
            socket = new DatagramSocket(6200, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);
            while(true) {
                byte[] buffer = new byte[15000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData()).trim();
                if(message.equals("SEARCH_SERVERS_MATHLAN")) {
                    byte[] data = "FOUND_SERVER_MATHLAN".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                    socket.send(sendPacket);
                }
            }
        } catch(Exception ex) {
            System.out.println("Error on listening");
        }
    }

    public static DiscoveryThread getInstance() {
        return new DiscoveryThread();
    }

}
