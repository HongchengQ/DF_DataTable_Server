package com.nailoong.dfdatatableserver.utils;

public class Utils {
    public static boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }
}
