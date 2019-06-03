package com.example.myhomework2.model.postInformation;

import java.util.List;

public class InfoPost {

    private List<Images> images;

    private String body_text;

    private String favorites_count;

    private String description;

    private Dates[] dates;

    private String title;

    private String[] tags;

    private String short_title;

    private String site_url;

    private String price;

    private String comments_count;

    private String is_free;

    private String disable_comments;

    private String publication_date;

    private String tagline;

    private Location location;

    private String id;

    private Place place;

    private String[] categories;

    private String age_restriction;

    private String slug;

    private String[] participants;

    public List<Images> getImages ()
    {
        return images;
    }

    public void setImages (List<Images> images)
    {
        this.images = images;
    }

    public String getBody_text ()
    {
        return body_text;
    }

    public void setBody_text (String body_text)
    {
        this.body_text = body_text;
    }

    public String getFavorites_count ()
    {
        return favorites_count;
    }

    public void setFavorites_count (String favorites_count)
    {
        this.favorites_count = favorites_count;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public Dates[] getDates ()
    {
        return dates;
    }

    public void setDates (Dates[] dates)
    {
        this.dates = dates;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getShort_title ()
    {
        return short_title;
    }

    public void setShort_title (String short_title)
    {
        this.short_title = short_title;
    }

    public String getSite_url ()
    {
        return site_url;
    }

    public void setSite_url (String site_url)
    {
        this.site_url = site_url;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getComments_count ()
    {
        return comments_count;
    }

    public void setComments_count (String comments_count)
    {
        this.comments_count = comments_count;
    }

    public String getIs_free ()
    {
        return is_free;
    }

    public void setIs_free (String is_free)
    {
        this.is_free = is_free;
    }

    public String getDisable_comments ()
    {
        return disable_comments;
    }

    public void setDisable_comments (String disable_comments)
    {
        this.disable_comments = disable_comments;
    }

    public String getPublication_date ()
    {
        return publication_date;
    }

    public void setPublication_date (String publication_date)
    {
        this.publication_date = publication_date;
    }

    public String getTagline ()
    {
        return tagline;
    }

    public void setTagline (String tagline)
    {
        this.tagline = tagline;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Place getPlace ()
    {
        return place;
    }

    public void setPlace (Place place)
    {
        this.place = place;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public String getAge_restriction ()
    {
        return age_restriction;
    }

    public void setAge_restriction (String age_restriction)
    {
        this.age_restriction = age_restriction;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    public String[] getParticipants ()
    {
        return participants;
    }

    public void setParticipants (String[] participants)
    {
        this.participants = participants;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [images = "+images+", body_text = "+body_text+", favorites_count = "+favorites_count+", description = "+description+", dates = "+dates+", title = "+title+", tags = "+tags+", short_title = "+short_title+", site_url = "+site_url+", price = "+price+", comments_count = "+comments_count+", is_free = "+is_free+", disable_comments = "+disable_comments+", publication_date = "+publication_date+", tagline = "+tagline+", location = "+location+", id = "+id+", place = "+place+", categories = "+categories+", age_restriction = "+age_restriction+", slug = "+slug+", participants = "+participants+"]";
    }
}
