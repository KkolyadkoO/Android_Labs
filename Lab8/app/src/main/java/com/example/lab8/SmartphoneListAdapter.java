package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab8.R;
import com.example.lab8.Smartphone;

import java.util.List;

public class SmartphoneListAdapter extends BaseAdapter {

    private Context context;
    private List<Smartphone> smartphoneList;

    public SmartphoneListAdapter(Context context, List<Smartphone> smartphoneList) {
        this.context = context;
        this.smartphoneList = smartphoneList;
    }

    @Override
    public int getCount() {
        return smartphoneList.size();
    }

    @Override
    public Object getItem(int position) {
        return smartphoneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.smartphone_list_item, parent, false);
        }

        Smartphone smartphone = (Smartphone) getItem(position);

        ImageView imageView = convertView.findViewById(R.id.smartphone_image);
        TextView brandTextView = convertView.findViewById(R.id.smartphone_brand);
        TextView osTextView = convertView.findViewById(R.id.smartphone_os);
        TextView priceTextView = convertView.findViewById(R.id.smartphone_price);
        TextView memoryTextView = convertView.findViewById(R.id.smartphone_memory);
        TextView manufacturerTextView = convertView.findViewById(R.id.smartphone_manufacturer);
        TextView releaseYearTextView = convertView.findViewById(R.id.smartphone_release_year);
        TextView quantityTextView = convertView.findViewById(R.id.smartphone_quantity);

        imageView.setImageResource(smartphone.getImageResourceId());
        brandTextView.setText(smartphone.getBrand());
        osTextView.setText(smartphone.getOsType());
        priceTextView.setText(String.format("$%.2f", smartphone.getPrice()));
        memoryTextView.setText(smartphone.getMemorySize() + " GB");
        manufacturerTextView.setText(smartphone.getManufacturer());
        releaseYearTextView.setText(String.valueOf(smartphone.getReleaseYear()));
        quantityTextView.setText("In Stock: " + smartphone.getQuantity());

        return convertView;
    }
}
