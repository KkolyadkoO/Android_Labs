package com.example.lab11.abstructions;

import com.example.lab11.entities.University;

import java.util.List;

public interface UniversitiesCallback {
    void onSuccess(List<University> comments);
    void onError(String errorMessage);
}
