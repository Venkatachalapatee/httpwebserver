package com.comcast;

public class Solution1 {
    public static void main(String[] args){
        //Note: This Solution doesn't parse the input Stream to HttpRequest/HttpContext
        //Note: So this solution will respond to all the request with same response "Hello World"
        Server webServer = new Server(8888);
        webServer.run();
    }
}
