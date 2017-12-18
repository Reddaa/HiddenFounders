package com.hiddenfounders.hiddenfounderscodingchallenge.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Redaa on 12/18/2017.
 */

public class Owner {
    @Expose
    private String avatar_url;
    @Expose
    private String login;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
