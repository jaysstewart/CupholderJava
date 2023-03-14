package com.example.cupholderjava;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Connect {

    private final static byte START = 0x1;
    private final static byte LED_COMMAND = 0x4;

    private final static byte VALUE_OFF = 0x0;
    private final static byte VALUE_ON = (byte)0xFF;

    String address;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic bluetoothGattChar = null;

    private byte[] createControlWord(Byte type, byte ... args) {
        byte[] command = new byte[args.length +3];
        command[0] = START;
        command[1] = type;
        command[2] = (byte)args.length;
        for(int i = 0; i < args.length; i++) {
            command[i+3] = args[i];
        }
        return command;
    }

    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // successfully connected to the GATT Server
                Log.d("GattCallBack:", "Connected to Gatt Server");
                bluetoothGatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // disconnected from the GATT Server
                Log.d("GattCallBack:", "Disconnected from Gatt Server");
            }
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if(null == bluetoothGattChar) {
                for (int i = 0; i < gatt.getServices().size(); i++) {
                    System.out.println(gatt.getServices().get(i).getUuid());
                    for (int j = 0; j < gatt.getServices().get(i).getCharacteristics().size(); j++) {
                        System.out.println((gatt.getServices().get(i).getCharacteristics().get(j).getUuid()));
                        if (gatt.getServices().get(i).getCharacteristics().get(j).getUuid().equals(UUID.fromString("19b10001-e8f2-537e-4f6c-d104768a1214")) ) {
                            bluetoothGattChar = gatt.getServices().get(i).getCharacteristics().get(j);
                            System.out.println("did i get here????");
                        }
                    }
                }
            }
        }
    };


@SuppressLint("MissingPermission")
    public void gattConnect(final String address, Context context) {
        this.address = address;
        try {
            final BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
            bluetoothGatt = device.connectGatt(context,false, bluetoothGattCallback);
        } catch (Exception e) {}
    }


    @SuppressLint("MissingPermission")
    public void sendData(byte[] msg) {
        System.out.println(bluetoothGatt + " send data METHOD");
        bluetoothGattChar.setValue(msg);
        bluetoothGatt.writeCharacteristic(bluetoothGattChar);
    }

    public void switchLED(boolean on){
        sendData(createControlWord(LED_COMMAND, on?VALUE_ON:VALUE_OFF));
    }
}
