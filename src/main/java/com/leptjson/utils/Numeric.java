package com.leptjson.utils;

import com.leptjson.JsonContext;

public class Numeric {
    public static int subNumber(String s) {
        int i = 0;
        boolean negative = false;
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-') {
            return 0;
        }
        if (s.charAt(0) == '-') {
            negative = true;
            i++;
        }
        int count = 0;
        while (i < s.length() && count < 2) {
            if (s.charAt(i) == '.') {
                count++;
                i++;
            } else if (!Character.isDigit(s.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i;
    }
}
