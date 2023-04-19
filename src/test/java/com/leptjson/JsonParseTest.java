package com.leptjson;

import org.junit.Assert;
import org.junit.Test;

public class JsonParseTest {
    JsonParse jp = new JsonParse();

    @Test
    public void testParseNull() {
        LeptValue v = new LeptValue();
        v.type = LeptType.NULL;
        Assert.assertEquals(ParseResult.PARSE_OK, jp.parse(v, "null"));
        Assert.assertEquals(ParseResult.PARSE_ROOT_NOT_SINGULAR, jp.parse(v, "null x"));
        Assert.assertEquals(LeptType.NULL, v.getType());
    }

    @Test
    public void testParseTrue() {
        LeptValue v = new LeptValue();
        v.type = LeptType.NULL;
        Assert.assertEquals(ParseResult.PARSE_OK, jp.parse(v, "true"));
        Assert.assertEquals(LeptType.TRUE, v.getType());
    }

    @Test
    public void testParseFalse() {
        LeptValue v = new LeptValue();
        v.type = LeptType.NULL;
        Assert.assertEquals(ParseResult.PARSE_OK, jp.parse(v, "false"));
        Assert.assertEquals(LeptType.FALSE,v.getType());
    }

    @Test
    public void testParseExpectValue() {
    }

    @Test
    public void testParseInvalidValue() {
    }

    @Test
    public void testParseRootNotSingular() {
    }

    @Test
    public void testParse() {
        testParseNull();
        testParseTrue();
        testParseFalse();
        testParseExpectValue();
        testParseInvalidValue();
        testParseRootNotSingular();
    }
}