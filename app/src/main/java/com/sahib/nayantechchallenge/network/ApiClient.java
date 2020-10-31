package com.sahib.nayantechchallenge.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sahib.nayantechchallenge.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static GithubServices baseInstance = null;

    public static GithubServices getBaseInstance(Context context) {
        if (baseInstance == null) {
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.getBASE_URL())
                    .client(okClient)
                    .build();
            baseInstance = retrofit.create(GithubServices.class);
        }
        return baseInstance;
    }

}
