package com.example.lab11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11.abstructions.UniversitiesCallback;
import com.example.lab11.entities.University;
import com.example.lab11.services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAndRecyclerController extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_and_view_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EditText etCountry = view.findViewById(R.id.etCountry);
        EditText etName = view.findViewById(R.id.etName);
        Button btnFind = view.findViewById(R.id.btnFind);
        btnFind.setOnClickListener(v -> {
            fetchUniversities(new UniversitiesCallback() {
                @Override
                public void onSuccess(List<University> universities) {
                    UniversityRecyclerAdapter adapter = new UniversityRecyclerAdapter(universities, university -> {
            // Переход на DetailFragment при нажатии на элемент
            DetailFragment detailFragment = DetailFragment.newInstance(university);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }););
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }, etName.getText().toString() ,etCountry.getText().toString());
        });


//
//        List<Smartphone> smartphones = SmartphoneStore.getSmartphones();
//        SmartphoneRecyclerAdapter adapter = new SmartphoneRecyclerAdapter(smartphones, smartphone -> {
//            // Переход на DetailFragment при нажатии на элемент
//            DetailFragment detailFragment = DetailFragment.newInstance(smartphone);
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, detailFragment)
//                    .addToBackStack(null)
//                    .commit();
//        });
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);

        return view;
    }
    public void fetchUniversities(UniversitiesCallback callback, String name, String country) {
        NetworkService.getInstance()
                .getUniversityDomainsApi()
                .getUniversityByNameAndCountry(name, country)
                .enqueue(new Callback<List<University>>() {
                    @Override
                    public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<University> universities = response.body();
                            callback.onSuccess(universities);
                        } else if (response.code() == 404) {
                            callback.onError("Ошибка при получение комментариев");
                        } else {
                            callback.onError("Ошибка: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<University>> call, Throwable t) {
                        callback.onError("Ошибка при подключении к серверу");
                        t.printStackTrace();
                    }
                });
    }
}
