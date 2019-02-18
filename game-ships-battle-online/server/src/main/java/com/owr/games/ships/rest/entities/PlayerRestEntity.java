package com.owr.games.ships.rest.entities;

public class PlayerRestEntity {

    private String token;

    private String name;

    private String type;


    public PlayerRestEntity() {

    }

    public PlayerRestEntity(String name, String token, String type) {
        this.token = token;
        this.name = name;
        this.type = type;
    }

    public PlayerRestEntity(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
