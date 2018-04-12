package com.comcast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable {

    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(200);
    // TODO: We Should use configuration file to inject corePoolSize, maxPoolSize, KeepAlive,
    // TODO: Queue Size, Port Number, etc.
    // TODO: Configuration Injection.
    // Using Thread Pool to control the Number of Request that is being served Simultaneously
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 99, 2, TimeUnit.SECONDS, blockingQueue);

    Server(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.printf(String.format("Server Listening on Port %d", this.serverPort));
            while (!isStopped()) {
                Socket clientSocket;
                try {
                    clientSocket = this.serverSocket.accept();
                    System.out.printf("Client Address: %s Port: %d%n", clientSocket.getInetAddress(), clientSocket.getPort());

                } catch (IOException e) {
                    if (isStopped()) {
                        System.out.println("Server Stopped.");
                        return;
                    }
                    throw new RuntimeException(
                            "Error accepting client connection", e);
                }
                // Creating Threads Could use Thread
                threadPoolExecutor.execute(new WebServer(clientSocket));
            }
            System.out.println("Server Stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

}