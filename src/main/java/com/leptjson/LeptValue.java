package com.leptjson;

public class LeptValue {
    LeptType type;
    double n;
    String s;

    public LeptType getType() {
        return type;
    }

    public int getBool() {
        assert type == LeptType.TRUE || type == LeptType.FALSE;
        return type == LeptType.TRUE ? 1 : 0;
    }

    public void setBool(boolean b) {
        type = b ? LeptType.TRUE : LeptType.FALSE;
    }

    public double getNumber() {
        assert type == LeptType.NUMBER;
        return n;
    }

    public void setNumber(double n) {
        this.n = n;
        type = LeptType.NUMBER;
    }

    public String getString() {
        assert type == LeptType.STRING;
        return s;
    }

    public void setString(String s) {
        this.s = s;
        type = LeptType.STRING;
    }
}
