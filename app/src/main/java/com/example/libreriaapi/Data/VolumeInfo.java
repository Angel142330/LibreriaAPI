package com.example.libreriaapi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable{
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("pageCount")
    @Expose
    private String pageCount;

    @SerializedName("printType")
    @Expose
    private String printType;

    @SerializedName("imageLinks")
    @Expose
    private VolumeImageLinks imageLinks;

    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPageCount() {
        return pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public VolumeImageLinks getImageLinks() {
        return imageLinks;
    }
    public List<String> getCategories() {
        return categories;
    }
}