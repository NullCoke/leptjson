package com.leptjson.utils;

import com.leptjson.JsonContext;

public class Numeric {
    public static boolean validateNum(String s) {
        char first = s.charAt(0);
        char last = s.charAt(s.length() - 1);
        if (!Character.isDigit(first) && first != '-') {
            return false;
        }

        if (last == '.') {
            return false;
        }

        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
