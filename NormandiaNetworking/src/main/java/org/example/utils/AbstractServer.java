package org.example.utils;

import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractServer {

    private int port;
    private ServerSocket server = null;

    public AbstractServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            server = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for clients ...");
                Socket client = server.accept();
                System.out.println("Client connected ...");
                processRequest(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected abstract void processRequest(Socket client);
    public void stop(){
        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
