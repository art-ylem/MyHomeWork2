package com.example.myhomework2.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("poster")
    @Expose
    private Poster poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

}