package com.leptjson.utils;

import com.leptjson.JsonContext;

public class Numeric {
    public static int subNumber(String s) {
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '.') {
            return 0;
        }
        int count = 0;
        int i = 0;
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
