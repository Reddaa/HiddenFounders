package com.hiddenfounders.hiddenfounderscodingchallenge.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Redaa on 12/18/2017.
 */

public class Repository {

    @Expose
    private String name;

    @Expose
    private Owner owner;

    @Expose
    private int stargazers_count;

    @Expose
    private String description;

    @Expose
    private String html_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
