package com.anonymous.attendandsystem;

/**
 * Created by Mahmoud on 4/9/2018.
 */

public class Attend {
    public static final String ATTEND_TABLE_NAME = "attend";
    public static final String ATTEND_COLUMN_ID = "id";
    public static final String ATTEND_COLUMN_CODE = "code";

    private String code;
    private int id;

    public Attend(String code, int id) {
        this.code = code;
        this.id = id;
    }

    public Attend(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
