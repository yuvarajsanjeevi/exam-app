package com.example.demo.source;

public class Sayhello {

    public static String SayXHello(Integer x) {
        String xHello = "";
        for (int i = 0; i < x; i++) {
            xHello = xHello + "Hello!";
        }
        return xHello;
    }

    public static String SayOnceHello() {
        return "Hello!";
    }

    public static String SayTwiceHello() {
        return "Hello! Hello!";
    }
}
