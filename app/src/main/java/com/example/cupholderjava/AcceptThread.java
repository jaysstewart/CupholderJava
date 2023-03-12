package com.example.cupholderjava;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.Set;

//public class AcceptThread extends Thread {
//    private final BluetoothServerSocket mmServerSocket;
//    BluetoothManager bluetoothManager;
//    BluetoothAdapter bluetoothAdapter;
//
//    Bluetooth bluetooth = new Bluetooth();
//
//    Context context;
//
//    AcceptThread(BluetoothManager btm, BluetoothAdapter bta, BluetoothServerSocket bss, Context context) {
//        this.bluetoothManager = btm;
//        this.bluetoothAdapter = bta;
//        this.mmServerSocket = bss;
//        this.context = context;
//    }
//
//
//    @SuppressLint("MissingPermission")
//    public AcceptThread() {
//        // Use a temporary object that is later assigned to mmServerSocket
//        // because mmServerSocket is final.
//        BluetoothServerSocket tmp = null;
//        try {
//            // MY_UUID is the app's UUID string, also used by the client code.
//            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
//        } catch (IOException e) {
//            Log.e(TAG, "Socket's listen() method failed", e);
//        }
//        mmServerSocket = tmp;
//    }
//
//    public void run() {
//        BluetoothSocket socket = null;
//        // Keep listening until exception occurs or a socket is returned.
//        while (true) {
//            try {
//                socket = mmServerSocket.accept();
//            } catch (IOException e) {
//                Log.e(TAG, "Socket's accept() method failed", e);
//                break;
//            }
//
//            if (socket != null) {
//                // A connection was accepted. Perform work associated with
//                // the connection in a separate thread.
//
//                try {
//                    mmServerSocket.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                break;
//            }
//        }
//    }
//
//    // Closes the connect socket and causes the thread to finish.
//    public void cancel() {
//        try {
//            mmServerSocket.close();
//        } catch (IOException e) {
//            Log.e(TAG, "Could not close the connect socket", e);
//        }
//    }
//
//    private void paired() {
//        @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//        if (pairedDevices.size() > 0) {
//            for (BluetoothDevice device : pairedDevices) {
//                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//
//                    Log.d(TAG, "Permission failed!!!!!!");
//                    return;
//                }
//                String deviceName = device.getName();
//                String deviceHardwareAddress = device.getAddress();
//                System.out.println(deviceName);
//            }
//        }
//    }
//
////    @SuppressLint("MissingPermission")
////    public void enableBT() {
////
////        if (bluetoothAdapter == null) {
////            Log.d(TAG, "enableDisableBT: Does not have BT capabilites");
////        }
////        if (!bluetoothAdapter.isEnabled()) {
////            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
////            startActivity(intent);
////
////            IntentFilter BtIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
////            registerReceiver(mReceiver, BtIntent);
////        }
////    }
//}

