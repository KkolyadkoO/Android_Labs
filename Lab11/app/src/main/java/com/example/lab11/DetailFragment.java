package com.example.lab11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab11.entities.University;

public class DetailFragment extends Fragment {

    private static final String ARG_UNIVERSITY = "university";

    private University university;

    public static DetailFragment newInstance(University university) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_UNIVERSITY, university); // Используем putParcelable вместо putSerializable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            university = getArguments().getParcelable(ARG_UNIVERSITY); // Используем getParcelable
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvCountry = view.findViewById(R.id.tvCountry);
        TextView tvDomain = view.findViewById(R.id.tvDomain);
        TextView tvWebPage = view.findViewById(R.id.tvWebPage);

        tvName.setText(university.getName());
        tvCountry.setText(university.getCountry());
        tvDomain.setText(String.format(university.getDomain().get(0)));
        tvWebPage.setText(university.getWebPage().get(0));

        return view;
    }
}
