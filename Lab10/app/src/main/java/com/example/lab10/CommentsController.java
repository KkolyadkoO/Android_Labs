package com.example.lab10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab10.abstructions.CommentsCallback;
import com.example.lab10.entities.Comment;
import com.example.lab10.services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsController extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comments_view, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchComments(new CommentsCallback() {
            @Override
            public void onSuccess(List<Comment> comments) {
                CommentRecyclerAdapter adapter = new CommentRecyclerAdapter(comments);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public void fetchComments(CommentsCallback callback) {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllComments()
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Comment> comments = response.body();
                            callback.onSuccess(comments);
                        } else if (response.code() == 404) {
                            callback.onError("Ошибка при получение комментариев");
                        } else {
                            callback.onError("Ошибка: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        callback.onError("Ошибка при подключении к серверу");
                        t.printStackTrace();
                    }
                });
    }
}
