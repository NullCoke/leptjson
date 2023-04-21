package com.leptjson;

import com.leptjson.utils.Numeric;

public class JsonParse {
    public ParseResult parse(LeptValue v, String json) {
        JsonContext c = new JsonContext();
        ParseResult ret = ParseResult.PARSE_INVALID_VALUE;
        c.json = json;
        v.type = LeptType.NULL;
        parseWhitespace(c);
        if (parseValue(c, v) == ParseResult.PARSE_OK) {
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
        int i = 0, len = c.json.length();
        char[] s = c.json.toCharArray();
        if (i < len && s[i] == '-') i++;
        if (i < len && s[i] == '0') i++;
        else {
            if (i < len && !Numeric.isDigit1To9(s[i])) return ParseResult.PARSE_INVALID_VALUE;
            i++;
            while (i < len && Character.isDigit(s[i])) {
                i++;
            }
        }

        if (i < len && s[i] == '.') {
            i++;
            if (i < len && !Character.isDigit(s[i])) return ParseResult.PARSE_INVALID_VALUE;
            i++;
            while (i < len && Character.isDigit(s[i])) {
                i++;
            }
        }

        if (i < len && (s[i] == 'e' || s[i] == 'E')) {
            i++;
            if (i < len && (s[i] == '-' || s[i] == '+')) i++;
            if (i < len && !Character.isDigit(s[i])) return ParseResult.PARSE_INVALID_VALUE;
            i++;
            while (i < len && Character.isDigit(s[i])) {
                i++;
            }
        }
        v.type = LeptType.NUMBER;
        if (i >= len) {
            v.n = Double.parseDouble(c.json);
            c.json = "";
        } else {
            v.n = Double.parseDouble(c.json.substring(0, i));
            c.json = c.json.substring(i);
        }
        return ParseResult.PARSE_OK;
    }

    ParseResult parseString(JsonContext c, LeptValue v) {
        String json = c.json;
        int i = 1;
        while (i < json.length()) {
            if (json.charAt(i) == '\"' && json.charAt(i - 1) != '\\') {
                break;
            }
            i++;
        }
        c.json = json.substring(i + 1);
        v.type = LeptType.STRING;
        v.s = json.substring(1, i);
        return ParseResult.PARSE_OK;
    }

    ParseResult parseValue(JsonContext c, LeptValue v) {
        char prefix = c.json.charAt(0);
        return switch (prefix) {
            case 'n' -> parseNull(c, v);
            case 't' -> parseTrue(c, v);
            case 'f' -> parseFalse(c, v);
            case '\"' -> parseString(c, v);
            default -> parseNumber(c, v);
        };
    }
}
