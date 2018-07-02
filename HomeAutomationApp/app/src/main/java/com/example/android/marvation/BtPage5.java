package com.example.android.marvation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BtPage5 extends AppCompatActivity {
    // Insert bluetooth devices MAC address for room 1
    public static String room5_address = null;

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

        getMAC5();

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

    public void getMAC5() {
        String DEFAULT = "N/A";
        SharedPreferences mSharedPreferences = getSharedPreferences("Room5MAC", MODE_PRIVATE);
        String roomMAC = mSharedPreferences.getString("MAC_ID5", DEFAULT);

        if(roomMAC.equals(DEFAULT)) {
            Toast.makeText(this, "Device not linked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(BtPage5.this, MainActivity.class);
            startActivity(i);
        }
        else {
            room5_address = roomMAC;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //It is best to check BT status at onResume in case something has changed while app was paused etc
        checkBTState();

        BluetoothDevice device = mBtAdapter.getRemoteDevice(room5_address);

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
}
