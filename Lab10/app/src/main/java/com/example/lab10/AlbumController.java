package com.example.lab10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab10.entities.Album;
import com.example.lab10.services.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumController extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.album_view, container, false);
        TextView tvAlbumInfo = view.findViewById(R.id.tvAlbumInfo);
        Button btnFindAlbum = view.findViewById(R.id.btnFindAlbum);
        EditText etAlbumId = view.findViewById(R.id.etAlbumId);

        btnFindAlbum.setOnClickListener(v -> {
            tvAlbumInfo.setText("");
            String albumIdText = etAlbumId.getText().toString();

            if (albumIdText.isEmpty()) {
                Toast.makeText(requireContext(), "Ошибка: введите ID альбома", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int albumId = Integer.parseInt(albumIdText);
                    if (albumId > 0) {
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getAlbumWithId(albumId)
                                .enqueue(new Callback<Album>() {
                                    @Override
                                    public void onResponse(Call<Album> call, Response<Album> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            Album album = response.body();
                                            tvAlbumInfo.append("UserId - " + album.getUserId() + "\n");
                                            tvAlbumInfo.append("Id - " + album.getId() + "\n");
                                            tvAlbumInfo.append("Title - " + album.getTitle() + "\n");
                                        } else if (response.code() == 404) {
                                            tvAlbumInfo.append("Альбом с таким ID не найден\n");
                                        } else {
                                            tvAlbumInfo.append("Ошибка: " + response.code() + "\n");
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Album> call, Throwable t) {
                                        tvAlbumInfo.append("Ошибка при подключении к серверу\n");
                                        t.printStackTrace();
                                    }
                                });
                    } else {
                        Toast.makeText(requireContext(), "Ошибка: введите положительное число", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Ошибка: введите числовое значение", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }
}
