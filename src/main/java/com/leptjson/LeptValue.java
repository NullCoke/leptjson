package com.leptjson;

public class LeptValue {
    LeptType type;
    double n;
    String s;

    public LeptType getType() {
        return type;
    }

    public int getBool() {
        return 0;
    }

    public void setBool() {

    }

    public double getNumber() {
        assert type == LeptType.NUMBER;
        return n;
    }

    public void setNumber(LeptValue v, double n) {

    }

    public String getString() {
        return this.s;
    }

    public void setString(LeptValue v, String s) {
        v.type = LeptType.STRING;
        this.s = s;
    }
}
