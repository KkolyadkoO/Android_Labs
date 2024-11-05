package com.example.lab9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SmartphoneRecyclerAdapter extends RecyclerView.Adapter<SmartphoneRecyclerAdapter.SmartphoneViewHolder> {

    private List<Smartphone> smartphoneList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Smartphone smartphone);
    }

    public SmartphoneRecyclerAdapter(List<Smartphone> smartphoneList, OnItemClickListener listener) {
        this.smartphoneList = smartphoneList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SmartphoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smartphone_recycler_item, parent, false);
        return new SmartphoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartphoneViewHolder holder, int position) {
        Smartphone smartphone = smartphoneList.get(position);
        holder.bind(smartphone, listener);
    }

    @Override
    public int getItemCount() {
        return smartphoneList.size();
    }

    public static class SmartphoneViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView brandTextView;
        private TextView priceTextView;
        private TextView memoryTextView;

        public SmartphoneViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.smartphone_image);
            brandTextView = itemView.findViewById(R.id.smartphone_brand);
            priceTextView = itemView.findViewById(R.id.smartphone_price);
            memoryTextView = itemView.findViewById(R.id.smartphone_memory);
        }

        public void bind(Smartphone smartphone, OnItemClickListener listener) {
            imageView.setImageResource(smartphone.getImageResourceId());
            brandTextView.setText(smartphone.getBrand());
            priceTextView.setText(String.format("$%.2f", smartphone.getPrice()));
            memoryTextView.setText(smartphone.getMemorySize() + " GB");

            itemView.setOnClickListener(v -> listener.onItemClick(smartphone));
        }
    }
}
