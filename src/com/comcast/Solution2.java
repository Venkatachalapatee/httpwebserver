package com.comcast;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Solution2 {
    public static void main(String[] args){
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
            server.createContext("/api/hi", new HelloWorldHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
