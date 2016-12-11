package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by delcidb on 12/1/2016.
 */

public class Club_Instruction extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
     //Club_Instruction links to the clubinstruction screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubinstructionscreen);
    //This button leads to the newclubcreationscreen
        Button button1 = (Button) findViewById(R.id.button6);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), NewClubActivity.class);
                v.getContext().startActivity(i);
            }
        });
     //This button leads to the editmyclubscreen
        Button button2 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Edit_My_Club.class);
                v.getContext().startActivity(i);
            }
        });
     //This button leads to the instructionsmenuscreen
        Button button3 = (Button) findViewById(R.id.button8);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Instructions_Menu.class);
                v.getContext().startActivity(i);
            }
        });

    }
}


