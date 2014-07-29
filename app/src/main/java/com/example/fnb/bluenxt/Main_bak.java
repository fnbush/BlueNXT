/*
package com.example.fnb.bluenxt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main_bak extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView t = (TextView) findViewById(R.id.text);
        final Button button = (Button) findViewById(R.id.retry);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t.setText("Retry\n");
                doit();
            }
        });


        doit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doit(){
        Boolean connected = false;
        TextView t = (TextView)findViewById(R.id.text);
        t.setText("Initilizing...\n");

        BTComm bTComm = new BTComm();

        t.append("Verifying Bluetooth Enabled...\n");
        while (true) {
            if (bTComm.enableBT()) t.append("Bluetooth is enabled\n");
            else {
                t.append("Failed, please enable and retry\n");
                //break;
            }

            t.append("Attempting to find devices...\n");
            try {
                t.append("Device(s) Found: ");
                for (String a : bTComm.findDevice()){
                    t.append(" '" + a + "' ");
                }
                t.append("\n");
            } catch (NullPointerException e) {
                t.append("No device found\n");
                Log.e("NXT", "Null Ponter", e);
                break;
            }

            t.append("Attempting to connect...\n");
            try {
                bTComm.connectToNXT("");
                connected = true;
                t.append("Connection sucess!\n");
            } catch (NullPointerException e) {
                t.append("Connection failed\n");
                connected = false;
                Log.e("NXT", "Null Ponter", e);
                break;
            }

            if (connected) {
                t.append("Attempting to send message...\n");
                try {
                    bTComm.writeMessage((byte) 0x06, "nxt1");
                    bTComm.writeMessage((byte) 0x00, "nxt1");
                    bTComm.writeMessage((byte) 0x80, "nxt1");
                    bTComm.writeMessage((byte) 0x03, "nxt1");
                    bTComm.writeMessage((byte) 0x0B, "nxt1");
                    bTComm.writeMessage((byte) 0x02, "nxt1");
                    bTComm.writeMessage((byte) 0xF4, "nxt1");
                    bTComm.writeMessage((byte) 0x01, "nxt1");
                } catch (InterruptedException e) {
                    t.append("message failed\n");
                    Log.e("NXT", "Interuppted", e);
                }
            }
        }
        t.append("Done");
    }

}
*/
