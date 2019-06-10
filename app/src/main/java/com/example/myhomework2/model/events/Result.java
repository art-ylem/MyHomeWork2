package com.example.myhomework2.model.events;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("dates")
    @Expose
    private List<Date> dates = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("place")
    @Expose
    private Place place;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("age_restriction")
    @Expose
    private String ageRestriction;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("is_free")
    @Expose
    private boolean isFree;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("site_url")
    @Expose
    private String siteUrl;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

}