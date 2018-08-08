package com.example.railan.cervejas.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by railan on 06/08/18.
 */

public class Beer {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("first_brewed")
    private String firstBrewed;

    @SerializedName("image_url")
    private String imageUrl;

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirst_brewed() {
        return firstBrewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.firstBrewed = first_brewed;
    }

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
        return imageUrl;
    }

    public void setImage_url(String image_url) {
        this.imageUrl = image_url;
    }

    @Override
    public String toString() {
        return "id: " + id + " nome: " + name;
    }
}
