package com.example.jennifer.yhackstesting;

public class Rule {

    public int type; //one item = 1, list = 2
    public String NCD;
    public String code;
    public String predicate;
    public String field;
    public String value;

    public Rule(int type, String NCD, String code, String predicate, String field, String value) {
        this.type = type;
        this.NCD = NCD;
        this.code = code;
        this.predicate = predicate;
        this.field = field;
        this.value = value;
    }
}
