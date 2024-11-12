package com.example.lab10.abstructions;

import com.example.lab10.entities.Album;
import com.example.lab10.entities.Comment;
import com.example.lab10.entities.Photo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface JSONPlaceHolderApi {
    @GET("/albums/{id}")
    public Call<Album> getAlbumWithId(@Path("id") int id);

    @GET("/comments/")
    public Call<List<Comment>> getAllComments();

    @GET("/photos/{id}")
    public Call<Photo> getPhotoWithId(@Path("id") int id);

    @GET
    Call<ResponseBody> getImage(@Url String url);


}
