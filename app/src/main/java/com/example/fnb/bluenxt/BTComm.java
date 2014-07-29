package com.example.fnb.bluenxt;

        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.util.HashSet;
        import java.util.Set;
        import java.util.UUID;


        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.bluetooth.BluetoothSocket;

        import android.util.Log;
        import android.widget.TextView;

public class BTComm {

    //Target NXTs for communication
    String nxt ;//= "00:16:53:08:79:2B";

    BluetoothAdapter localAdapter;
    BluetoothSocket socket_nxt;
    boolean success=false;

    //Enables Bluetooth if not enabled
    public Boolean enableBT(){
        localAdapter=BluetoothAdapter.getDefaultAdapter();
        //If Bluetooth not enable then do it
        if (BluetoothAdapter.getDefaultAdapter()==null) {
            return false;
        } else {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
    }

    //connect to NXT
    public boolean connectToNXT(String nxt){
        this.nxt = nxt;
        localAdapter = BluetoothAdapter.getDefaultAdapter();
        //get the BluetoothDevice of the NXT

        //try to connect to the nxt
        try {
            BluetoothDevice nxt_1 = localAdapter.getRemoteDevice(nxt);
            socket_nxt = nxt_1.createRfcommSocketToServiceRecord(UUID
                    .fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket_nxt.connect();
            success = true;

        } catch (IOException e) {
            Log.d("Bluetooth","Err: Device not found or cannot connect");
            success=false;
        } catch (NullPointerException e){
            Log.e("BTComm","Null Pointer");
            success=false;
        }
        return success;

    }

    public void writeMessage(byte msg) throws InterruptedException{
        BluetoothSocket connSock= socket_nxt;
            try {
            OutputStreamWriter out=new OutputStreamWriter(connSock.getOutputStream());
            out.write(msg);
            out.flush();
            Thread.sleep(1000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int readMessage(){
        int n;
        BluetoothSocket connSock= socket_nxt;
            try {
            InputStreamReader in=new InputStreamReader(connSock.getInputStream());
            n=in.read();
            return n;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public String[] findDevice(){
        Set<String> s = new HashSet<String>();
        s.add("A");
        s.add("B");
        s.add("C");
        //l = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        return s.toArray(new String[s.size()]);
    }
}