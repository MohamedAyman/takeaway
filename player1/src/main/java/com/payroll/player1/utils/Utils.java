package com.payroll.player1.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static List<String> logs = new ArrayList<>();

    public static void restLogs() {
        logs = new ArrayList<>();
    }

    public static String getLogs() {
        return String.join("\n", logs);
    }

    public static void print(String s) {
        logs.add(s);
        System.out.println(s);
    }
}
