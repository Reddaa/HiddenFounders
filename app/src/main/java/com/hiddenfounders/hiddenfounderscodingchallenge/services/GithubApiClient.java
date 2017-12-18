package com.hiddenfounders.hiddenfounderscodingchallenge.services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Redaa on 12/18/2017.
 */
// Singleton class to return the api client whenever we need it, I can add dagger dependency
// injection to make it easier but since we'll use it one time in this demo, I think it's sufficient
public class GithubApiClient {


    private static GithubRestService githubRestService;

    public static GithubRestService GetService(Context context) {
        if (githubRestService != null)
            return  githubRestService;
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        Retrofit.Builder builder =  new Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        githubRestService =  retrofit.create(GithubRestService.class);
        return githubRestService;
    }
}
