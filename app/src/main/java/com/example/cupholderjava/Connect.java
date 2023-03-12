package com.example.cupholderjava;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Connect {

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothGatt bluetoothGatt;

    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // successfully connected to the GATT Server
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // disconnected from the GATT Server
            }
        }
    };

    @SuppressLint("MissingPermission")
    public void gattConnect(final String address, Context context) {
        try {
            final BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
            bluetoothGatt = device.connectGatt(context,false, bluetoothGattCallback);
        } catch (Exception e) {}
    }
//
//    static BluetoothSocket mmSocket;
//    static Handler handler;
//    static InputStream mmInStream;
//    static OutputStream mmOutStream;
//
//    private final static int CONNECTING_STATUS = 1; // used in bluetooth handler to identify message status
//    private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
//
//
//    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//
//    @SuppressLint("MissingPermission")
//    public void CreateConnectThread(String address) {
//        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
//        //bluetoothDevice.createBond();
//        //UUID uuid = bluetoothDevice.getUuids()[0].getUuid();
////        BluetoothSocket tmp = null;
////        try {
////            tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
////        } catch (IOException e) {
////            Log.e("CCT", "Sockets create() method failed", e);
////        }
//        mmSocket = tmp;
//        ConnectToThread();
//    }
//
//    @SuppressLint("MissingPermission")
//    public void ConnectToThread() {
//        try {
//            // Connect to the remote device through the socket. This call blocks
//            // until it succeeds or throws an exception.
//            mmSocket.connect();
//            Log.e("Status", "Device connected");
//            handler.obtainMessage(CONNECTING_STATUS, 1, -1).sendToTarget();
//        } catch (IOException connectException) {
//            // Unable to connect; close the socket and return.
//            try {
//                mmSocket.close();
//                Log.e("Status", "Cannot connect to device");
//                handler.obtainMessage(CONNECTING_STATUS, -1, -1).sendToTarget();
//            } catch (IOException closeException) {
//                Log.e("CTT", "Could not close the client socket", closeException);
//            }
//            return;
//        }
//        ConnectedThread();
//    }
//
//    public void ConnectedThread() {
//        // Get the input and output streams, using temp objects because
//        // member streams are final
//        InputStream tmpIn = null;
//        OutputStream tmpOut = null;
//
//        try {
//            tmpIn = mmSocket.getInputStream();
//            tmpOut = mmSocket.getOutputStream();
//        } catch (IOException e) { }
//
//        mmInStream = tmpIn;
//        mmOutStream = tmpOut;
//        run();
//    }
//
//
//    public void run() {
//        byte[] buffer = new byte[1024];  // buffer store for the stream
//        int bytes = 0; // bytes returned from read()
//        // Keep listening to the InputStream until an exception occurs
//        while (true) {
//            try {
//                    /*
//                    Read from the InputStream from Arduino until termination character is reached.
//                    Then send the whole String message to GUI Handler.
//                     */
//                buffer[bytes] = (byte) mmInStream.read();
//                String readMessage;
//                if (buffer[bytes] == '\n'){
//                    readMessage = new String(buffer,0,bytes);
//                    Log.e("Arduino Message",readMessage);
//                    handler.obtainMessage(MESSAGE_READ,readMessage).sendToTarget();
//                    bytes = 0;
//                } else {
//                    bytes++;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
//    }
//
//    public void write(String input) {
//        byte[] bytes = input.getBytes(); //converts entered String into bytes
//        try {
//            mmOutStream.write(bytes);
//        } catch (IOException e) {
//            Log.e("Send Error","Unable to send message",e);
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            mmSocket.close();
//        } catch (IOException e) {}
//    }

}
