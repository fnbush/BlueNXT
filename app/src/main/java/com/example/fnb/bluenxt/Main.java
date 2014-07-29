package com.example.fnb.bluenxt;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class Main extends Activity implements View.OnClickListener {
    BTComm bTComm = new BTComm();
    TextView t;
    Button retry, conn, beep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.t = (TextView) findViewById(R.id.text);
        t.setMovementMethod(new ScrollingMovementMethod());

        this.retry = (Button) findViewById(R.id.retry);
        this.conn = (Button) findViewById(R.id.connect);
        this.beep = (Button) findViewById(R.id.beep);
        retry.setOnClickListener(this);
        conn.setOnClickListener(this);
        beep.setOnClickListener(this);
        t.setText("Initilizing...\n");
        init();
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

    public void init() {
        String[] s = null;

        t.append("Verifying Bluetooth Enabled...\n");

        if (bTComm.enableBT()) {
            t.append("Bluetooth is enabled\n");
            conn.setEnabled(true);
        /*
        t.append("Attempting to find devices...\n");
        try {
            s = bTComm.findDevice();
            setContentView(R.layout.activity_device);
            ViewGroup linearLayout = (ViewGroup) findViewById(R.id.device_layout);
            for (int i=0;i < s.length;i++){
                Button device_button = new Button(this);
                device_button.setText(s[i]);
                device_button.setId(i);
                device_button.setOnClickListener(this);
                linearLayout.addView(device_button);
            }

        } catch (NullPointerException e) {
            t.append("No device found\n");
            Log.e("NXT", "Null Ponter", e);
        }*/
        } else {
            t.append("Failed, please enable and retry\n");
            //conn.setEnabled(true);
        }
    }

    public void sendBeep() {
        t.append("Attempting to send message...\n");
        try {
            bTComm.writeMessage((byte) 0x06);
            bTComm.writeMessage((byte) 0x00);
            bTComm.writeMessage((byte) 0x80);
            bTComm.writeMessage((byte) 0x03);
            bTComm.writeMessage((byte) 0x0B);
            bTComm.writeMessage((byte) 0x02);
            bTComm.writeMessage((byte) 0xF4);
            bTComm.writeMessage((byte) 0x01);
            t.append("message sent\n");
        } catch (InterruptedException e) {
            t.append("message failed\n");
            Log.e("NXT", "Interuppted", e);
        } catch (NullPointerException e) {
            t.append("message failed\n");
            Log.e("NXT", "Null Pointer", e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                t.append("Retrying...\n");
                init();
                break;
            case R.id.connect:
                t.append("Attempting to connect...\n");
                Connect connect = new Connect("00:16:53:08:79:2B");
                connect.doInBackground();
                break;
            case R.id.beep:
                t.append("Attempting to send beep...");
                sendBeep();
                break;
            //default:
        }
    }

    public class Connect extends AsyncTask<String, Void, String[]>{
        String nxt;

        public Connect(String nxt) {
            this.nxt = nxt;
        }

        public String[] doInBackground(String... params){
            String[] a = null;
            Boolean connected = false;
            //setContentView(R.layout.activity_main);
            if (bTComm.connectToNXT(nxt)) {
                t.append("Connection Sucess\n");
                beep.setEnabled(true);
            }
            else {
                t.append("Connection Failed\n");
            }
            return a;
        }

    }

}

