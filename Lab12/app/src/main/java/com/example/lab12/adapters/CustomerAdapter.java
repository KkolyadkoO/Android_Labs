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
import com.example.lab12.entities.Customer;

public class CustomerAdapter extends ListAdapter<Customer, CustomerAdapter.CustomerViewHolder> {
    private final OnCustomerClickListener onCustomerClickListener;

    public interface OnCustomerClickListener {
        void onCustomerClick(Customer customer);
    }

    public CustomerAdapter(OnCustomerClickListener listener) {
        super(DIFF_CALLBACK);
        this.onCustomerClickListener = listener;
    }

    private static final DiffUtil.ItemCallback<Customer> DIFF_CALLBACK = new DiffUtil.ItemCallback<Customer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
            return oldItem.name.equals(newItem.name) &&
                    oldItem.lastName.equals(newItem.lastName) &&
                    oldItem.birthday.equals(newItem.birthday) &&
                    oldItem.phone.equals(newItem.phone);
        }
    };

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = getItem(position);
        holder.bind(customer);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvPhone;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onCustomerClickListener.onCustomerClick(getItem(position));
                }
            });
        }

        public void bind(Customer customer) {
            tvName.setText(customer.name + " " + customer.lastName);
            tvPhone.setText(customer.phone);
        }
    }
}
