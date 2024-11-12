package com.example.lab10.abstructions;

import com.example.lab10.entities.Comment;

import java.util.List;

public interface CommentsCallback {
    void onSuccess(List<Comment> comments);
    void onError(String errorMessage);
}
