package com.example.dort.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String key;

    private String value;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
