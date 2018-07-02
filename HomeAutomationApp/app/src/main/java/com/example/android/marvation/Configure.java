package com.example.android.marvation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Configure extends AppCompatActivity {
    // MAC-address of Bluetooth module
    public String getAddress = null;

    String room1MAC,
            room2MAC,
            room3MAC,
            room4MAC,
            room5MAC;

    private Spinner mRoomSpinner;

    List<String> roomListArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure_room_mac);

        //Get MAC address from DeviceListActivity
        Intent intent = getIntent();
        getAddress = intent.getStringExtra(DevicesPaired.MAC_ADDRESS);

        roomListArray = new ArrayList<>();
        roomListArray.add(MainActivity.room1);
        roomListArray.add(MainActivity.room2);
        roomListArray.add(MainActivity.room3);
        roomListArray.add(MainActivity.room4);
        roomListArray.add(MainActivity.room5);

        mRoomSpinner = (Spinner) findViewById(R.id.spinner_room);

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter<String> roomSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomListArray);

        // Specify dropdown layout style - simple list view with 1 item per line
        roomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mRoomSpinner.setAdapter(roomSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {

                    if(selection.equals(getString(R.string.select))) {

                    }
                    else if (selection.equals(getString(R.string.room_1))) {
                        room1MAC = getAddress;
                        room1MAC();
                    } else if (selection.equals(getString(R.string.room_2))) {
                        room2MAC = getAddress;
                        room2MAC();
                    } else if(selection.equals(getString(R.string.room_3))){
                        room3MAC = getAddress;
                        room3MAC();
                    } else if(selection.equals(getString(R.string.room_4))){
                        room4MAC = getAddress;
                        room4MAC();
                    } else {
                        room5MAC = getAddress;
                        room5MAC();
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Configure.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Call back to the MainActivity
    public void callback() {
        Intent i = new Intent(Configure.this, DevicesPaired.class);
        startActivity(i);
    }

    public void room1MAC() {
        SharedPreferences mSharedPreferences = getSharedPreferences("Room1MAC", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for room 1
        mEditor.putString("MAC_ID1", room1MAC);
        mEditor.apply();
    }

    public void room2MAC() {
        SharedPreferences mSharedPreferences = getSharedPreferences("Room2MAC", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for room 2
        mEditor.putString("MAC_ID2", room2MAC);
        mEditor.apply();
    }

    public void room3MAC() {
        SharedPreferences mSharedPreferences = getSharedPreferences("Room3MAC", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for room 2
        mEditor.putString("MAC_ID3", room3MAC);
        mEditor.apply();
    }

    public void room4MAC() {
        SharedPreferences mSharedPreferences = getSharedPreferences("Room4MAC", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for room 2
        mEditor.putString("MAC_ID4", room4MAC);
        mEditor.apply();
    }

    public void room5MAC() {
        SharedPreferences mSharedPreferences = getSharedPreferences("Room5MAC", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for room 2
        mEditor.putString("MAC_ID5", room5MAC);
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.save_mac:
//                insert();
                callback();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
