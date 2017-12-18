package com.hiddenfounders.hiddenfounderscodingchallenge.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Redaa on 12/18/2017.
 */

//This class is just in case we need some info
public class GithubResponse {
    @Expose
    private  int total_count;

    @Expose
    private List<Repository> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}
