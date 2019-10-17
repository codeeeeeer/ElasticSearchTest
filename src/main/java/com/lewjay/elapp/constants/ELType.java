package com.lewjay.elapp.constants;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/12 17:22
 */
public enum ELType {
    STRING("string"),
    TEXT("text"),
    KEYWORD("keyword"),
    BYTE("byte"),
    SHORT("short"),
    INTEGER("integer"),
    LONG("long"),
    BOOLEAN("boolean"),
    DATE("date"),
    RANGE("range"),
    BINARY("binary"),
    ARRAY("array"),
    OBJECT("object"),
    NESTED("nested"),
    GEO_POINT("geo_point"),
    GEO_SHAPE("geo_shape"),
    IP("ip"),
    COMPLETION("completion"),
    TOKEN_COUNT("token_count"),
    ATTACHMENT("attachment"),
    PERCOLATOR("percolator"),
    ;


    ELType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
