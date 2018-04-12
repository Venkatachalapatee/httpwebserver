package com.comcast;

import java.io.*;
import java.net.Socket;

public class WebServer implements Runnable {
    private final Socket clientSocket;

    WebServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {

            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String inputLine;
            //Note: We should be converting this stream to HttpRequest to verify the GET Path
            //Note: For this exercise i am skipping those steps.
            while (!(inputLine = in.readLine()).equals(""))
                System.out.println(inputLine);
            //in.close();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWebServer: " + " - " +
                    time + "").getBytes());
            output.close();
            in.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
