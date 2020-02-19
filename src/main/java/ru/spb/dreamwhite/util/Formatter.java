package ru.spb.dreamwhite.util;

public class Formatter {

    public static String formate(String input) {
        String formatted = null;
        formatted = input.replace("-", "").replace(" ", "").trim();
        if (formatted.startsWith("8")) {
            formatted = formatted.replaceFirst("8", "+7");
        }
        return formatted;
    }

    public static void main(String[] args) {
        System.out.println(formate("8812 454 57-6 8"));
    }
}
