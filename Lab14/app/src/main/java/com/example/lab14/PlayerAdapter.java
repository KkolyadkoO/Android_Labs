package com.example.lab14;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.numberText.setText(String.valueOf(player.getNumber()));
        holder.lastNameText.setText(player.getLastName());

        if (player.getBitmap() != null) {
            holder.imageView.setImageBitmap(player.getBitmap());
        } else {
            holder.imageView.setImageResource(R.drawable.placeholder);
        }
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView numberText;
        TextView lastNameText;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.playerImage);
            numberText = itemView.findViewById(R.id.playerNumber);
            lastNameText = itemView.findViewById(R.id.playerLastName);
        }
    }
}
