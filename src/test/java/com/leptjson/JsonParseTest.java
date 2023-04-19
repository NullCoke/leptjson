package com.leptjson;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class JsonParseTest {
    JsonParse jp = new JsonParse();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0.0, "0"},
                {0.0, "-0"},
                {0.0, "-0"},
                {-1.0, "-1"},
                {1.5, "1.5"},
                {-1.5, "-1.5"},
                {3.1416, "3.1416"},
//                {1E10, "1E10"},
//                {1e10, "1e10"},
//                {1E+10, "1E+10"},
//                {1E-10, "1E-10"},
//                {-1E10, "-1E10"},
//                {-1e10, "-1e10"},
//                {-1E+10, "-1E+10"},
//                {-1E-10, "-1E-10"},
//                {1.234E+10, "1.234E+10"},
//                {1.234E-10, "1.234E-10"},
//                {1.234E-10, "1.234E-10"},
//                {ParseResult.PARSE_INVALID_VALUE, "+0"},
//                {ParseResult.PARSE_INVALID_VALUE, "+1"},
//                {ParseResult.PARSE_INVALID_VALUE, ".123"},
//                {ParseResult.PARSE_INVALID_VALUE, "1."},
//                {ParseResult.PARSE_INVALID_VALUE, "INF"},
//                {ParseResult.PARSE_INVALID_VALUE, "inf"},
//                {ParseResult.PARSE_INVALID_VALUE, "NAN"},
//                {ParseResult.PARSE_INVALID_VALUE, "nan"}
        });
    }

    @Parameterized.Parameter
    public double expect;

    @Parameterized.Parameter(1)
    public String input;

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
        Assert.assertEquals(LeptType.FALSE, v.getType());
    }

    @Test
    public void testParseNumber() {
        LeptValue v = new LeptValue();
        v.type = LeptType.NULL;
        Assert.assertEquals(ParseResult.PARSE_OK, jp.parse(v, input));
        Assert.assertEquals(LeptType.NUMBER, v.getType());
        Assert.assertEquals(expect, v.getNumber(), 0);
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