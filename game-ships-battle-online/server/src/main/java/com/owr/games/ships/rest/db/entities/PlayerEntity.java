
package com.owr.games.ships.rest.db.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Player")
@Table( name="player", uniqueConstraints = @UniqueConstraint(columnNames={"token"}))
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String type;

    @NotBlank
    private String name;

    @NotBlank
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
