package com.example.lab11.services;

import com.example.lab11.abstructions.UniversityDomainsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService instance;
    private static final String BASE_URL = "http://universities.hipolabs.com";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }
    public UniversityDomainsApi getUniversityDomainsApi(){
        return retrofit.create(UniversityDomainsApi.class);
    }
}
