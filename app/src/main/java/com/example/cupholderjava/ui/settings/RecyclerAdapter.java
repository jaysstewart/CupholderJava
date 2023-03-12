package com.example.cupholderjava.ui.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cupholderjava.Connect;
import com.example.cupholderjava.Device;
import com.example.cupholderjava.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context context;
    List<Device> deviceList;
    final Connect connect;

    public RecyclerAdapter(Context context, List<Device> deviceList, Connect connect) {
        this.context = context;
        this.deviceList = deviceList;
        this.connect = connect;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder view = new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.bluetooth_view,parent,false));
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.deviceName.setText(deviceList.get(position).getDeviceName());
        holder.macAddress.setText(deviceList.get(position).getMacAddress());
        holder.deviceName.setOnClickListener(v -> connect.gattConnect(deviceList.get(position).getMacAddress(), v.getContext()));
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }
}
