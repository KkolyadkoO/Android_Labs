package com.example.lab9;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerFragment extends Fragment {

    private SmartphoneViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = new ViewModelProvider(requireActivity()).get(SmartphoneViewModel.class);

        List<Smartphone> smartphones = SmartphoneStore.getSmartphones();
        SmartphoneRecyclerAdapter adapter = new SmartphoneRecyclerAdapter(smartphones, smartphone -> {
            viewModel.selectSmartphone(smartphone);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
