package com.example.myhomework2.model.postInformation;

public class Images
{
    private String image;

    private Source source;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public Source getSource ()
    {
        return source;
    }

    public void setSource (Source source)
    {
        this.source = source;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [image = "+image+", source = "+source+"]";
    }
}
