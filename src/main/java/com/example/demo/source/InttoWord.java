package com.example.demo.source;

public class InttoWord {

    public static String Translate(Integer caseIndex) {
        String defaultResponse = "";
        String response = "";

        switch (caseIndex) {
            case 0:
                response = "Zero";
                break;
            case 1:
                response = "One";
                break;
            case 2:
                response = "Two";
                break;
            case 3:
                response = "Three";
            case 4:
                response = "Four";
                break;
            case 5:
                response = "Five";
                break;
            case 6:
                response = "Six";
                break;
            case 7:
                response = "Seven";
                break;
            case 8:
                response = "Eight";
                break;
            case 9:
                response = "Nine";
                break;
            case 10:
                response = "Ten";
                break;
            default:
                response = "Not supported";
        }
        return response;
    }
}
