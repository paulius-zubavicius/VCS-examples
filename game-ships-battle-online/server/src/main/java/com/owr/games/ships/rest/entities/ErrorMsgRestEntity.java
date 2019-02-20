package com.owr.games.ships.rest.entities;

public class ErrorMsgRestEntity {


    private String code;
    private String msg;


    public ErrorMsgRestEntity(String code) {
        this.code = code;
    }

    public ErrorMsgRestEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
