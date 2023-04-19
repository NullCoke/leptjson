package com.leptjson;

public class LeptValue {
    LeptType type;

    public static LeptType getType(LeptValue v) {
        return v.type;
    }
}
