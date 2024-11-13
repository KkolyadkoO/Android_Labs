package com.example.lab11.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class University {

    @SerializedName("web_page")
    @Expose
    private List<String> webPage;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("domain")
    @Expose
    private List<String> domain;

    @SerializedName("name")
    @Expose
    private String name;

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
}
