package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by joshuasurette on 11/30/16.
 */

public class NewClubActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newclubcreationscreen);

        // FireBase database initialization
        // ref points to the root node in the Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("fir-practice-7403d");

        // Creating child node references to write data to
        final DatabaseReference newClubRef = ref.child("Clubs");

        // UI Retrieval and storage for writing to the database
        final EditText club_name = (EditText)findViewById(R.id.editText7);
        final EditText keywords = (EditText)findViewById(R.id.editText8);
        final EditText email = (EditText)findViewById(R.id.editText9);
        final EditText descrip = (EditText)findViewById(R.id.editText10);

        final DatabaseReference newClubNames = newClubRef.child("Names").push();
        final DatabaseReference descripRef = newClubNames.child("Description").push();
        final DatabaseReference emailRef = newClubNames.child("Email").push();
        final DatabaseReference keywordsRef = newClubNames.child("Keywords").push();

        // Listen for SAVE clicks and write the edit text fields to Firebase
        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Write to database on save click
                newClubNames.setValue(club_name.getText().toString());
                descripRef.setValue(descrip.getText().toString());
                emailRef.setValue(email.getText().toString());
                keywordsRef.setValue(keywords.getText().toString());
            }
        });


    }


}