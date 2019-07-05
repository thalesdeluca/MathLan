package com.klab.mathlan.thread;

public class DiscoveryThread implements Runnable {
    @Override
    public void run() {

    }

    public static DiscoveryThread getInstance() {
        return new DiscoveryThread();
    }

}
