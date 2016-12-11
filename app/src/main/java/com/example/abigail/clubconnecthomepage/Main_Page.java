package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by delcidb on 12/4/2016.
 */

public class Main_Page extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
     //Main_Page links to activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main is the id for the homepage

     //Links to clubinstructionscreen
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Club_Instruction.class);
                v.getContext().startActivity(i);
            }
        });
     //Links to studentconnectscreen
        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Student_Connect.class);
                v.getContext().startActivity(i);
            }
        });
     //Links to activity_main
        Button button3 = (Button) findViewById(R.id.button5);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(i);
            }
        });


    }
}
