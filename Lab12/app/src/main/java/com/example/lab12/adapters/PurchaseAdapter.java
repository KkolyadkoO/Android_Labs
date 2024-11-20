package com.example.lab12.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab12.R;
import com.example.lab12.entities.Purchase;

public class PurchaseAdapter extends ListAdapter<Purchase, PurchaseAdapter.PurchaseViewHolder> {
    private final OnPurchaseClickListener onPurchaseClickListener;

    public interface OnPurchaseClickListener {
        void onPurchaseClick(Purchase purchase);
    }

    public PurchaseAdapter(OnPurchaseClickListener listener) {
        super(DIFF_CALLBACK);
        this.onPurchaseClickListener = listener;
    }

    private static final DiffUtil.ItemCallback<Purchase> DIFF_CALLBACK = new DiffUtil.ItemCallback<Purchase>() {
        @Override
        public boolean areItemsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return oldItem.product.equals(newItem.product) &&
                    oldItem.count.equals(newItem.count) &&
                    oldItem.price.equals(newItem.price) &&
                    oldItem.customerId == newItem.customerId;
        }
    };

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase, parent, false);
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        Purchase purchase = getItem(position);
        holder.bind(purchase);
    }

    class PurchaseViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProduct;
        private final TextView tvDetails;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProduct = itemView.findViewById(R.id.tv_product);
            tvDetails = itemView.findViewById(R.id.tv_details);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onPurchaseClickListener.onPurchaseClick(getItem(position));
                }
            });
        }

        public void bind(Purchase purchase) {
            tvProduct.setText(purchase.product);
            tvDetails.setText("Count: " + purchase.count + ", Price: $" + purchase.price);
        }
    }
}

