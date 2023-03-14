package com.example.cupholderjava.ui.settings;

import static androidx.core.content.ContextCompat.registerReceiver;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.bluetooth.le.ScanCallback;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cupholderjava.Connect;
import com.example.cupholderjava.Device;
import com.example.cupholderjava.R;
import com.example.cupholderjava.databinding.FragmentSettingsBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private final Connect connect = new Connect();
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
    private boolean scanning;
    private Handler handler = new Handler();
    List<Device> deviceList = new ArrayList<>();
    private static final long SCAN_PERIOD = 10000;

    TextView terminal;

    @SuppressLint("MissingPermission")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();

        recycler();
        Button scan = getView().findViewById(R.id.ScanButton);
        scan.setOnClickListener(v -> scanLeDevice());

        Button LED = getView().findViewById(R.id.LedButton);
        LED.setOnClickListener(v -> connect.switchLED(true));

        terminal = getView().findViewById(R.id.terminal);
        terminal.setMovementMethod(new ScrollingMovementMethod());
    }

    public void recycler() {
        HashSet<String> set = new HashSet<>();
        deviceList.removeIf(p -> !set.add(p.getDeviceName()));

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerAdapter(getContext(), deviceList, connect));
    }

    @SuppressLint("MissingPermission")
    private void scanLeDevice() {
        if (!scanning) {
            // Stops scanning after a predefined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanning = false;
                    bluetoothLeScanner.stopScan(leScanCallback);
                    terminal.append("Stopping scan \n");
                }
            }, SCAN_PERIOD);

            scanning = true;
            terminal.append("Scanning for devices... \n");
            bluetoothLeScanner.startScan(leScanCallback);
        } else {
            scanning = false;
            terminal.append("Stopping scan \n");
            bluetoothLeScanner.stopScan(leScanCallback);
        }

    }


    // Device scan callback.
    private ScanCallback leScanCallback =
            new ScanCallback() {
                @SuppressLint("MissingPermission")
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    String name = result.getDevice().getName();
                    String address = result.getDevice().getAddress();
                    if (name != null) {
                        deviceList.add(new Device(name, address));
                    }
                    recycler();
                }
            };

    @SuppressLint("MissingPermission")
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}