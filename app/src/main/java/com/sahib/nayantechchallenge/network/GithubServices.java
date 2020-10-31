package com.sahib.nayantechchallenge.network;

import com.sahib.nayantechchallenge.network.models.RepositoryModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GithubServices {

    @GET("search/repositories")
    Call<RepositoryModel> listRepos(@QueryMap Map<String, String> options);

}
