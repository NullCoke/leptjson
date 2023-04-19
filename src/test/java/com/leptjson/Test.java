package com.leptjson;

import com.leptjson.utils.Numeric;

public class Test {

    @org.junit.Test
    public void test() {
        String s = "3.1416";
        System.out.println(Double.parseDouble(s));
        System.out.println(Numeric.subNumber(s));
    }
}
