package com.example.casestudy.service;

import com.example.casestudy.model.response.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{user}/repos")
    Call<List<RepoResponse>> retrieveUserRepos(@Path("user") String user);
}
