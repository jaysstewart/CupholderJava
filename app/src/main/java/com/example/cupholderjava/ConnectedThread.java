//package com.example.cupholderjava;
//
//import android.annotation.SuppressLint;
//import android.app.Service;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothGatt;
//import android.bluetooth.BluetoothGattCallback;
//import android.bluetooth.BluetoothProfile;
//import android.content.Context;
//import android.os.Handler;
//
//import java.security.Provider;
//import java.util.List;
//import java.util.Map;
//
//
//public class ConnectedThread  {
//
////    private final BluetoothSocket mmSocket;
////    private final InputStream mmInStream;
////    private final OutputStream mmOutStream;
//    public static final int RESPONSE_MESSAGE = 10;
//    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//    private BluetoothGatt bluetoothGatt;
//    Handler handler;
//
////    public ConnectedThread(BluetoothSocket socket, Handler handler){
////        mmSocket = socket;
////        InputStream tmpIn = null;
////        OutputStream tmpOut = null;
////        this.handler = handler;
////        Log.i("[THREAD-CT]","Creating thread");
////        try{
////            tmpIn = socket.getInputStream();
////            tmpOut = socket.getOutputStream();
////        } catch(IOException e) {
////            Log.e("[THREAD-CT]","Error:"+ e.getMessage());
////        }
////        mmInStream = tmpIn;
////        mmOutStream = tmpOut;
////        try {
////            mmOutStream.flush();
////        } catch (IOException e) {
////            return;
////        }
////        Log.i("[THREAD-CT]","IO's obtained");
////    }
//
//
//
////    public void run(){
////        BufferedReader br;
////        br = new BufferedReader(new InputStreamReader(mmInStream));
////        while(true){
////            try{            String resp = br.readLine();
////                Message msg = new Message();
////                msg.what = RESPONSE_MESSAGE;
////                msg.obj = resp;
////                handler.sendMessage(msg);
////            }catch(IOException e){
////                break;
////            }
////        }
////        Log.i("[THREAD-CT]","While loop ended");
////    }
////
////    public void write(byte[] bytes){
////        try{
////            Log.i("[THREAD-CT]", "Writting bytes");
////            mmOutStream.write(bytes);
////        }catch(IOException e){}
////    }
//
//
//}
