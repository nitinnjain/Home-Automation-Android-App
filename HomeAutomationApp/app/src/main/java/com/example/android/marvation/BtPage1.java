package com.example.android.marvation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BtPage1 extends AppCompatActivity {

    //TextView for switches
    TextView text1, text2, text3, text4, text5, text6, text7, text8;

    //String for switches 1 to 8
    public static String st1 = "Switch 1", st2 = "Switch 2", st3 = "Switch 3", st4 = "Switch 4", st5 = "Switch 5", st6 = "Switch 6", st7 = "Switch 7", st8 = "Switch 8";
    // Insert bluetooth devices MAC address for room 1
    public static String room1_address = null;

    //Declared buttons to select switches
    Button s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16;

    // Member fields
    private BluetoothAdapter mBtAdapter;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    // UUID service - This is the type of Bluetooth device that the BT module is
    // It is very likely yours will be the same, if not google UUID for your manufacturer
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_page);

        getMAC1();
        getSwitchName();

        text1 = (TextView) findViewById(R.id.switch1_text);
        text1.setText(st1);
        text2 = (TextView) findViewById(R.id.switch2_text);
        text2.setText(st2);
        text3 = (TextView) findViewById(R.id.switch3_text);
        text3.setText(st3);
        text4 = (TextView) findViewById(R.id.switch4_text);
        text4.setText(st4);
        text5 = (TextView) findViewById(R.id.switch5_text);
        text5.setText(st5);
        text6 = (TextView) findViewById(R.id.switch6_text);
        text6.setText(st6);
        text7 = (TextView) findViewById(R.id.switch7_text);
        text7.setText(st7);
        text8 = (TextView) findViewById(R.id.switch8_text);
        text8.setText(st8);

        s1 = (Button) findViewById(R.id.switch1);
        s2 = (Button) findViewById(R.id.switch2);
        s3 = (Button) findViewById(R.id.switch3);
        s4 = (Button) findViewById(R.id.switch4);
        s5 = (Button) findViewById(R.id.switch5);
        s6 = (Button) findViewById(R.id.switch6);
        s7 = (Button) findViewById(R.id.switch7);
        s8 = (Button) findViewById(R.id.switch8);
        s9 = (Button) findViewById(R.id.switch9);
        s10 = (Button) findViewById(R.id.switch10);
        s11 = (Button) findViewById(R.id.switch11);
        s12 = (Button) findViewById(R.id.switch12);
        s13 = (Button) findViewById(R.id.switch13);
        s14 = (Button) findViewById(R.id.switch14);
        s15 = (Button) findViewById(R.id.switch15);
        s16 = (Button) findViewById(R.id.switch16);

        //On click Listener for the 16 switches on the board
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("b");      //Turn Off switch
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("a");      //Turn On switch
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("d");      //Turn Off switch
            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("c");      //Turn On switch
            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("f");      //Turn Off switch
            }
        });

        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("e");      //Turn On switch
            }
        });

        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("h");      //Turn Off switch
            }
        });

        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("g");      //Turn On switch
            }
        });

        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("j");      //Turn Off switch
            }
        });

        s10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("i");      //Turn On switch
            }
        });

        s11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("l");      //Turn Off switch
            }
        });

        s12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("k");      //Turn On switch
            }
        });

        s13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("n");      //Turn Off switch
            }
        });

        s14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("m");      //Turn On switch
            }
        });

        s15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("p");      //Turn Off switch
            }
        });

        s16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("o");      //Turn On switch
            }
        });
    }

    public void getMAC1() {
        String DEFAULT = "N/A";
        SharedPreferences mSharedPreferences = getSharedPreferences("Room1MAC", MODE_PRIVATE);
        String roomMAC = mSharedPreferences.getString("MAC_ID1", DEFAULT);

        if(roomMAC.equals(DEFAULT)) {
            Toast.makeText(this, "Device not linked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(BtPage1.this, MainActivity.class);
            startActivity(i);
        }
        else {
            room1_address = roomMAC;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //It is best to check BT status at onResume in case something has changed while app was paused etc
        checkBTState();

        BluetoothDevice device = mBtAdapter.getRemoteDevice(room1_address);

        //Attempt to create a bluetooth socket for comms
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e1) {
            Toast.makeText(getBaseContext(), "ERROR - Could not create Bluetooth socket", Toast.LENGTH_SHORT).show();
        }

        // Establish the connection.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();        //If IO exception occurs attempt to close socket
            } catch (IOException e2) {
                Toast.makeText(getBaseContext(), "ERROR - Could not close Bluetooth socket", Toast.LENGTH_SHORT).show();
            }
        }

        // Create a data stream so we can talk to the device
        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "ERROR - Could not create bluetooth outstream", Toast.LENGTH_SHORT).show();
        }
        //When activity is resumed, attempt to send a piece of junk data ('x') so that it will fail if not connected
        // i.e don't wait for a user to press button to recognise connection failure
        sendData("x");
    }

    //method to check if the device has Bluetooth and if it is on.
    //Prompts the user to turn it on if it is off
    public void checkBTState() {
        // Check device has Bluetooth and that it is turned on
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (!mBtAdapter.isEnabled()) {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    // Method to send data
    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        try {
            //attempt to place data on the outstream to the BT device
            outStream.write(msgBuffer);
        } catch (IOException e) {
            //if the sending fails this is most likely because device is no longer there
            Toast.makeText(getBaseContext(), "ERROR - Device not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //Pausing can be the end of an app if the device kills it or the user doesn't open it again
        //close all connections so resources are not wasted

        //Close BT socket to device
        try {
            btSocket.close();
        } catch (IOException e2) {
            Toast.makeText(getBaseContext(), "ERROR - Failed to close Bluetooth socket", Toast.LENGTH_SHORT).show();
        }
    }

    public void getSwitchName() {
        String DEFAULT = "N/A";
        SharedPreferences mSharedPreferences = getSharedPreferences("SwitchName", MODE_PRIVATE);
        String room_name_get1 = mSharedPreferences.getString("switchName1", DEFAULT);
        String room_name_get2 = mSharedPreferences.getString("switchName2", DEFAULT);
        String room_name_get3 = mSharedPreferences.getString("switchName3", DEFAULT);
        String room_name_get4 = mSharedPreferences.getString("switchName4", DEFAULT);
        String room_name_get5 = mSharedPreferences.getString("switchName5", DEFAULT);
        String room_name_get6 = mSharedPreferences.getString("switchName6", DEFAULT);
        String room_name_get7 = mSharedPreferences.getString("switchName7", DEFAULT);
        String room_name_get8 = mSharedPreferences.getString("switchName8", DEFAULT);

        if(room_name_get1.equals(DEFAULT) || room_name_get2.equals(DEFAULT) || room_name_get3.equals(DEFAULT) || room_name_get4.equals(DEFAULT) || room_name_get5.equals(DEFAULT) || room_name_get6.equals(DEFAULT) || room_name_get7.equals(DEFAULT) || room_name_get8.equals(DEFAULT)) {
            st1 = "Switch 1";
            st2 = "Switch 2";
            st3 = "Switch 3";
            st4 = "Switch 4";
            st5 = "Switch 5";
            st6 = "Switch 6";
            st7 = "Switch 7";
            st8 = "Switch 8";
        }
        else {
            st1 = room_name_get1;
            st2 = room_name_get2;
            st3 = room_name_get3;
            st4 = room_name_get4;
            st5 = room_name_get5;
            st6 = room_name_get6;
            st7 = room_name_get7;
            st8 = room_name_get8;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.switch_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.switch_name_change:
                //Call the activity to change thee room name
                Intent intent = new Intent(BtPage1.this, ChangeSwitchName.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
