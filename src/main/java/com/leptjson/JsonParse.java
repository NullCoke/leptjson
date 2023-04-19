package com.leptjson;

import com.leptjson.utils.Numeric;

public class JsonParse {
    public ParseResult parse(LeptValue v, String json) {
        JsonContext c = new JsonContext();
        ParseResult ret = ParseResult.PARSE_INVALID_VALUE;
        c.json = json;
        v.type = LeptType.NULL;
        parseWhitespace(c);
        if (ParseValue(c, v) == ParseResult.PARSE_OK) {
            ret = ParseResult.PARSE_OK;
            parseWhitespace(c);
            if (c.json.length() > 0) {
                ret = ParseResult.PARSE_ROOT_NOT_SINGULAR;
            }
        }
        return ret;
    }

    void parseWhitespace(JsonContext c) {
        c.json = c.json.trim();
    }

    ParseResult parseNull(JsonContext c, LeptValue v) {
        String json = c.json;
        if (!json.startsWith("null")) {
            return ParseResult.PARSE_INVALID_VALUE;
        }
        c.json = json.substring(4);
        v.type = LeptType.NULL;
        return ParseResult.PARSE_OK;
    }

    ParseResult parseTrue(JsonContext c, LeptValue v) {
        String json = c.json;
        if (!json.startsWith("true")) {
            return ParseResult.PARSE_INVALID_VALUE;
        }
        c.json = json.substring(4);
        v.type = LeptType.TRUE;
        return ParseResult.PARSE_OK;
    }

    ParseResult parseFalse(JsonContext c, LeptValue v) {
        String json = c.json;
        if (!json.startsWith("false")) {
            return ParseResult.PARSE_INVALID_VALUE;
        }
        c.json = json.substring(5);
        v.type = LeptType.FALSE;
        return ParseResult.PARSE_OK;
    }

    ParseResult parseNumber(JsonContext c, LeptValue v) {
        int i = Numeric.subNumber(c.json);
        if (i == 0) {
            return ParseResult.PARSE_INVALID_VALUE;
        }
        v.n = Double.parseDouble(c.json.substring(0, i));
        v.type = LeptType.NUMBER;
        c.json = c.json.substring(i);
        return ParseResult.PARSE_OK;
    }

    ParseResult ParseValue(JsonContext c, LeptValue v) {
        char prefix = c.json.charAt(0);
        return switch (prefix) {
            case 'n' -> parseNull(c, v);
            case 't' -> parseTrue(c, v);
            case 'f' -> parseFalse(c, v);
            default -> parseNumber(c, v);
        };
    }
}
