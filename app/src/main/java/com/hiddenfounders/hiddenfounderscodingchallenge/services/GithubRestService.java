package com.hiddenfounders.hiddenfounderscodingchallenge.services;

import com.hiddenfounders.hiddenfounderscodingchallenge.models.GithubResponse;
import com.hiddenfounders.hiddenfounderscodingchallenge.models.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Redaa on 12/18/2017.
 */

public interface GithubRestService {

    //I keep the other parameters static
    @GET("search/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Call<GithubResponse> getStarredRepos(@Query("page") int page);
}
