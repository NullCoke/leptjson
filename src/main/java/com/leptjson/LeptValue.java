package com.leptjson;

public class LeptValue {
    LeptType type;
    double n;

    public LeptType getType() {
        return type;
    }

    public double getNumber() {
        assert type == LeptType.NUMBER;
        return n;
    }
}
