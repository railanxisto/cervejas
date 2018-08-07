package com.example.railan.cervejas.dtos;

/**
 * Created by railan on 06/08/18.
 */

public class Beer {
    private int id;
    private String name;
    private String description;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "id: " + id + " nome: " + name;
    }
}
