package com.example.lab11.abstructions;

import com.example.lab11.entities.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UniversityDomainsApi {
    @GET("/search?")
    Call<List<University>> getUniversityByNameAndCountry(@Query("name") String name, @Query("country") String country);
}
