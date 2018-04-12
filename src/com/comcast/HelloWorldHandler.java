package com.comcast;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class HelloWorldHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpContext httpContext = httpExchange.getHttpContext();
        System.out.printf("Received Request to Path:%s%n", httpContext.getPath());
        String response = "Hello World";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
