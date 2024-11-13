package com.example.lab11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11.entities.University;

import java.util.List;

public class UniversityRecyclerAdapter extends RecyclerView.Adapter<UniversityRecyclerAdapter.UniversityViewHolder> {

    private List<University> universityList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(University university);
    }

    public UniversityRecyclerAdapter(List<University> universityList, OnItemClickListener listener) {
        this.universityList = universityList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unversity_recycler_item,
                parent, false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        University university = universityList.get(position);
        holder.bind(university, listener);
    }

    @Override
    public int getItemCount() {
        return universityList.size();
    }

    public static class UniversityViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;


        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvUniversityName);

        }

        public void bind(University university, OnItemClickListener listener) {
            tvName.setText(university.getName());

            itemView.setOnClickListener(v -> listener.onItemClick(university));
        }
    }

}
