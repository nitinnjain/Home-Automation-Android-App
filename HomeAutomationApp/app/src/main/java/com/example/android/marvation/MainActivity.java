package com.example.android.marvation;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> roomList;
    ArrayAdapter<String> adapter;
    ListView lv;

    public static String room1, room2, room3, room4, room5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothState();

        room1 = "Room 1";
        room2 = "Room 2";
        room3 = "Room 3";
        room4 = "Room 4";
        room5 = "Room 5";

        getRoomName();

        roomList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.room_list_id);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);

        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.txtview, roomList);
        lv.setOnItemClickListener(roomListClickListner);

        lv.setAdapter(adapter);
    }

    // Set up on-click listener for the listview
    private AdapterView.OnItemClickListener roomListClickListner = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int position, long arg3) {
            if (position == 0) {

                //Start the activity for room 1
                Intent i = new Intent(MainActivity.this, BtPage1.class);
                startActivity(i);
            } else if (position == 1) {

                //Start the activity for room 2
                Intent i = new Intent(MainActivity.this, BtPage2.class);
                startActivity(i);
            } else if (position == 2) {

                //Start the activity for room 3
                Intent i = new Intent(MainActivity.this, BtPage3.class);
                startActivity(i);
            } else if (position == 3) {

                //Start the activity for room 4
                Intent i = new Intent(MainActivity.this, BtPage4.class);
                startActivity(i);
            } else if (position == 4) {

                //Start the activity for room 5
                Intent i = new Intent(MainActivity.this, BtPage5.class);
                startActivity(i);
            }
        }
    };

    public void getRoomName() {
        String DEFAULT = "N/A";
        SharedPreferences mSharedPreferences = getSharedPreferences("RoomName", MODE_PRIVATE);
        String room_name_get1 = mSharedPreferences.getString("roomName1", DEFAULT);
        String room_name_get2 = mSharedPreferences.getString("roomName2", DEFAULT);
        String room_name_get3 = mSharedPreferences.getString("roomName3", DEFAULT);
        String room_name_get4 = mSharedPreferences.getString("roomName4", DEFAULT);
        String room_name_get5 = mSharedPreferences.getString("roomName5", DEFAULT);

        if(room_name_get1.equals(DEFAULT) || room_name_get2.equals(DEFAULT) || room_name_get3.equals(DEFAULT) || room_name_get4.equals(DEFAULT) || room_name_get5.equals(DEFAULT)) {
            room1 = "Room 1";
            room2 = "Room 2";
            room3 = "Room 3";
            room4 = "Room 4";
            room5 = "Room 5";
        }
        else {
            room1 = room_name_get1;
            room2 = room_name_get2;
            room3 = room_name_get3;
            room4 = room_name_get4;
            room5 = room_name_get5;
        }
    }

    //method to check if the device has Bluetooth and if it is on.
    //Prompts the user to turn it on if it is off
    public void bluetoothState() {
        // Member fields for bluetooth
        BluetoothAdapter mBtAdapter;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.config:
                //Call the activity to get the list of paired devices
                Intent i = new Intent(MainActivity.this, DevicesPaired.class);
                startActivity(i);
                return true;
            case R.id.change_room_name:
                //Call the activity to change thee room name
                Intent intent = new Intent(MainActivity.this, ChangeRoomName.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
