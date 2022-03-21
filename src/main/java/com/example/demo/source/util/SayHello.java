package com.example.demo.source.util;

/**
 * rename static method name
 * replace string concat with either string builder or repeat
 * SayOnceHello,SayTwiceHello not used can be removed
 */
public class SayHello {

    public static String sayXHello(Integer x) {
        String xHello = "";
        for (int i = 0; i < x; i++) {
            xHello = xHello + "Hello!";
        }
        return xHello;
    }


    /*
        using string builder
     */
    public static String sayXHelloUsingStringBuilder(Integer noOfTimes) {
        StringBuilder xHello = new StringBuilder();
        for (int i = 0; i < noOfTimes; i++) {
            xHello.append("Hello!");
        }
        return xHello.toString();
    }

    /**
     *  using repeat
     */
    public static String sayXHelloUsingRepeat(Integer noOfTimes) {
        return "Hello!".repeat(Math.max(0, noOfTimes));
    }



    // not used can be removed
    public static String SayOnceHello() {
        return "Hello!";
    }

    // not used can be removed
    public static String SayTwiceHello() {
        return "Hello! Hello!";
    }
}
