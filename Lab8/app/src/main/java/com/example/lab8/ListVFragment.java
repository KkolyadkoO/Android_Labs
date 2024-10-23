package com.example.lab8;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ListVFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listv, container, false);
        listView = view.findViewById(R.id.smartphone_list_view);

        List<Smartphone> smartphones = SmartphoneStore.getSmartphones();

        SmartphoneListAdapter adapter = new SmartphoneListAdapter(getContext(), smartphones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Smartphone selectedSmartphone = smartphones.get(position);
                Log.d("ListVFragment", "Clicked on: " + selectedSmartphone.getBrand());
            }
        });

        return view;
    }
}
