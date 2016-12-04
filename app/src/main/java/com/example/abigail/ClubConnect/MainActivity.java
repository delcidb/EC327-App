package com.example.abigail.ClubConnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main is the id for the homepage


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Club_Instruction.class);
                v.getContext().startActivity(i);
            }
        });

        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Student_Connect.class);
                v.getContext().startActivity(i);
            }
        });


    }

}

