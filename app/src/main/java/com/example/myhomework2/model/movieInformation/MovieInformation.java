package com.example.myhomework2.model.movieInformation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieInformation {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body_text")
    @Expose
    private String bodyText;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("age_restriction")
    @Expose
    private String ageRestriction;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
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

}