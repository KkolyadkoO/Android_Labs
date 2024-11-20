package com.example.lab11.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class University implements Parcelable {

    @SerializedName("web_pages")
    @Expose
    private List<String> webPage;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("domains")
    @Expose
    private List<String> domain;

    @SerializedName("name")
    @Expose
    private String name;

    protected University(Parcel in) {
        webPage = in.createStringArrayList();
        country = in.readString();
        domain = in.createStringArrayList();
        name = in.readString();
    }

    public static final Creator<University> CREATOR = new Creator<University>() {
        @Override
        public University createFromParcel(Parcel in) {
            return new University(in);
        }

        @Override
        public University[] newArray(int size) {
            return new University[size];
        }
    };

    public List<String> getWebPage() {
        return webPage;
    }

    public void setWebPage(List<String> webPage) {
        this.webPage = webPage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeList(webPage);
        parcel.writeString(country);
        parcel.writeList(domain);
        parcel.writeString(name);
    }
}
