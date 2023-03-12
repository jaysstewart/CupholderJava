package com.example.cupholderjava.ui.settings;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cupholderjava.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView deviceName;
    TextView macAddress;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        deviceName = itemView.findViewById(R.id.deviceName);
        macAddress = itemView.findViewById(R.id.macAddress);
    }
}
