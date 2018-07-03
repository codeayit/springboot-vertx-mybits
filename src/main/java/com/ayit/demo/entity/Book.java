package com.ayit.demo.entity;


import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DataObject(generateConverter = true)
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


    public Book() {
    }

    // Mandatory for data objects
    public Book(JsonObject jsonObject) {
        BookConverter.fromJson(jsonObject, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BookConverter.toJson(this, json);
        return json;
    }
}
