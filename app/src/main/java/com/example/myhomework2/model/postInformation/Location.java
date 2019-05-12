package com.example.myhomework2.postInformation;

public class Location
{
    private String slug;

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [slug = "+slug+"]";
    }
}
