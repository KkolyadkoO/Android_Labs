package com.example.lab10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab10.abstructions.JSONPlaceHolderApi;
import com.example.lab10.entities.Photo;
import com.example.lab10.services.NetworkService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoController extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_view, container, false);
        TextView tvPhotoInfo = view.findViewById(R.id.tvPhotoInfo);
        Button btnFindPhoto = view.findViewById(R.id.btnFindPhoto);
        EditText etPhotoId = view.findViewById(R.id.etPhotoId);
        ImageView ivLarge = view.findViewById(R.id.ivLarge);
        ImageView ivSmall = view.findViewById(R.id.ivSmall);

        btnFindPhoto.setOnClickListener(v -> {
            tvPhotoInfo.setText("");
            String photoIdText = etPhotoId.getText().toString();

            if (photoIdText.isEmpty()) {
                Toast.makeText(requireContext(), "Ошибка: введите ID альбома", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int photoId = Integer.parseInt(photoIdText);
                    if (photoId > 0) {
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getPhotoWithId(photoId)
                                .enqueue(new Callback<Photo>() {
                                    @Override
                                    public void onResponse(Call<Photo> call, Response<Photo> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            Photo photo = response.body();
                                            tvPhotoInfo.append("AlbumId - " + photo.getAlbumId() + "\n");
                                            tvPhotoInfo.append("Id - " + photo.getId() + "\n");
                                            tvPhotoInfo.append("Title - " + photo.getTitle() + "\n");
                                            setImage(ivLarge, photo.getUrl());
                                            setImage(ivSmall, photo.getThumbnailUrl());
                                        } else if (response.code() == 404) {
                                            tvPhotoInfo.append("Альбом с таким ID не найден\n");
                                        } else {
                                            tvPhotoInfo.append("Ошибка: " + response.code() + "\n");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Photo> call, Throwable t) {
                                        tvPhotoInfo.append("Ошибка при подключении к серверу\n");
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

    private void setImage(ImageView imageView, String url){
        JSONPlaceHolderApi api = NetworkService.getInstance().getJSONApi();
        Call<ResponseBody> call = api.getImage(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                        imageView.setImageBitmap(bmp);
                    } else {
                        Toast.makeText(requireContext(), "Ошибка: картинка не получена", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(requireContext(), "Ошибка: при подключении к серверу", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
