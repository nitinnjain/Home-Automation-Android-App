package com.example.android.marvation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.android.marvation.ChangeRoomName.string2;
import static com.example.android.marvation.ChangeRoomName.string3;

public class ChangeSwitchName extends AppCompatActivity {

    //Edit text for switche names
    private EditText edittext1, edittext2, edittext3, edittext4, edittext5, edittext6, edittext7, edittext8;

    //String variables to store text recieved from editText
    private static String string1, string2, string3, string4, string5, string6, string7, string8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_switch_name);

        setRoomName();

        //Edit text for 8 switches
        edittext1 = (EditText) findViewById(R.id.edit_switch_name1);
        edittext1.setText(BtPage1.st1);

        edittext2 = (EditText) findViewById(R.id.edit_switch_name2);
        edittext2.setText(BtPage1.st2);

        edittext3 = (EditText) findViewById(R.id.edit_switch_name3);
        edittext3.setText(BtPage1.st3);

        edittext4 = (EditText) findViewById(R.id.edit_switch_name4);
        edittext4.setText(BtPage1.st4);

        edittext5 = (EditText) findViewById(R.id.edit_switch_name5);
        edittext5.setText(BtPage1.st5);

        edittext6 = (EditText) findViewById(R.id.edit_switch_name6);
        edittext6.setText(BtPage1.st6);

        edittext7 = (EditText) findViewById(R.id.edit_switch_name7);
        edittext7.setText(BtPage1.st7);

        edittext8 = (EditText) findViewById(R.id.edit_switch_name8);
        edittext8.setText(BtPage1.st8);

        //Button to save the changed names for rooms
        Button bt = (Button) findViewById(R.id.save_switch_name);

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
                string6 = edittext6.getText().toString();
                string7 = edittext7.getText().toString();
                string8 = edittext8.getText().toString();

                setRoomName();
                Intent i = new Intent(ChangeSwitchName.this, BtPage1.class);
                startActivity(i);
            }
        });
    }

    //Function to save the changed names in the sharedPreferences
    public void setRoomName() {
        SharedPreferences mSharedPreferences = getSharedPreferences("SwitchName", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        //Save data for all 8 rooms
        mEditor.putString("switchName1", string1);
        mEditor.putString("switchName2", string2);
        mEditor.putString("switchName3", string3);
        mEditor.putString("switchName4", string4);
        mEditor.putString("switchName5", string5);
        mEditor.putString("switchName6", string6);
        mEditor.putString("switchName7", string7);
        mEditor.putString("switchName8", string8);
        mEditor.apply();
    }
}
