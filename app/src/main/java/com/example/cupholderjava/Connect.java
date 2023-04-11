package com.example.cupholderjava;

import static android.bluetooth.BluetoothGattCharacteristic.FORMAT_UINT16;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.UUID;

public class Connect {

    private final static byte START = 0x1;
    private final static byte LED_COMMAND = 0x4;
    private final static byte VALUE_OFF = 0x0;
    private final static byte VALUE_ON = (byte)0xFF;

    private final static String KEY = "ccefjclqrxqrtxcefijlqcertxccetfixxfijlqllqcrttrrifijlefcqlftxijl";

    String address;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public BluetoothGatt bluetoothGatt;
    public static BluetoothGattCharacteristic bluetoothGattChar = null;
    public static BluetoothGattCharacteristic weightGattChar = null;
    public static BluetoothGattCharacteristic dataGattChar = null;
    public static BluetoothGattCharacteristic errorGattChar = null;


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
            if (null == bluetoothGattChar) {
                for (int i = 0; i < gatt.getServices().size(); i++) {
                    System.out.println(gatt.getServices().get(i).getUuid());
                    for (int j = 0; j < gatt.getServices().get(i).getCharacteristics().size(); j++) {;
                        System.out.println(gatt.getServices().get(i).getCharacteristics().get(j).getUuid());
                        if (gatt.getServices().get(i).getCharacteristics().get(j).getUuid().equals(UUID.fromString("19B10001-E8F2-537E-4F6C-D104768A1214"))) {
                            weightGattChar = gatt.getServices().get(i).getCharacteristics().get(j);
                        }
                        if (gatt.getServices().get(i).getCharacteristics().get(j).getUuid().equals(UUID.fromString("19B10003-E8F2-537E-4F6C-D104768A1214"))) {
                            errorGattChar = gatt.getServices().get(i).getCharacteristics().get(j);
                        }
                        if (gatt.getServices().get(i).getCharacteristics().get(j).getUuid().equals(UUID.fromString("19B10004-E8F2-537E-4F6C-D104768A1214"))) {
                            dataGattChar = gatt.getServices().get(i).getCharacteristics().get(j);
                        }
                    }
                }
            }
        }

        @Override
        public void onCharacteristicRead(@NonNull BluetoothGatt gatt, @NonNull BluetoothGattCharacteristic characteristic, @NonNull byte[] value, int status) {
            super.onCharacteristicRead(gatt, characteristic, value, status);
            System.out.println(value.toString());
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


    @SuppressLint({"MissingPermission"})
    public void sendData(String msg) {
        byte[] value = new byte[1];
        value[0] = (byte) 21;
        dataGattChar.setWriteType(FORMAT_UINT16);
        bluetoothGatt.writeCharacteristic(dataGattChar, value, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);


//        dataGattChar.getValue();
    }

    String oneTimePad(String binary, String binaryKey) {
        String ciphertextBinary = "";

        // XOR respective bits of binary plaintext and key and append to ciphertext binary string
        for (int i = 0; i < binary.length(); i++) {
            if ((binary.charAt(i) == '0' && binaryKey.charAt(i) == '1') || (binary.charAt(i) == '1' && binaryKey.charAt(i) == '0')) {
                ciphertextBinary += '1';
            } else {
                ciphertextBinary += '0';
            }
        }

        return ciphertextBinary;
    }

    String stringToBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

}
