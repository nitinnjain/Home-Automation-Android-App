package com.example.android.marvation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeRoomName extends AppCompatActivity {

    //Edit text for room names
    EditText edittext1, edittext2, edittext3, edittext4, edittext5;

    //String variables to store text recieved from editText
    public static String string1, string2, string3, string4, string5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_room_name);

        //Edit text for 5 rooms
        edittext1 = (EditText) findViewById(R.id.edit_room_name1);
        edittext1.setText(MainActivity.room1);
        edittext2 = (EditText) findViewById(R.id.edit_room_name2);
        edittext2.setText(MainActivity.room2);
        edittext3 = (EditText) findViewById(R.id.edit_room_name3);
        edittext3.setText(MainActivity.room3);
        edittext4 = (EditText) findViewById(R.id.edit_room_name4);
        edittext4.setText(MainActivity.room4);
        edittext5 = (EditText) findViewById(R.id.edit_room_name5);
        edittext5.setText(MainActivity.room5);

        //Button to save the changed names for rooms
        Button bt = (Button) findViewById(R.id.save_room_name);

        //On click listener for the save button
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String to store the text recieved from edittext
                string1 = edittext1.getText().toString();
                string2 = edittext2.getText().toString();
                string3 = edittext3.getText().toString();
                string4 = edittext4.getText().toString();
                string5 = edittext5.getText().toString();

                setRoomName();
                Intent i = new Intent(ChangeRoomName.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    //Function to save the changed names in the sharedPreferences
    public void setRoomName() {
        SharedPreferences mSharedPreferences = getSharedPreferences("RoomName", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for all 5 rooms
        mEditor.putString("roomName1", string1);
        mEditor.putString("roomName2", string2);
        mEditor.putString("roomName3", string3);
        mEditor.putString("roomName4", string4);
        mEditor.putString("roomName5", string5);
        mEditor.apply();
    }
}
