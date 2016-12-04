package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

import static android.R.attr.data;
import static android.R.attr.value;
import static android.R.id.edit;


/**
 * Created by delcidb on 12/1/2016. Functionality implemented by surettej on 12/3/2016
 */

public class Edit_My_Club extends Activity{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editmyclubscreen);

        // FireBase database initialization
        // ref points to the root node in the Firebase Database

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("fir-practice-7403d");

        // UI Retrieval and storage for writing to the database
        final EditText username = (EditText)findViewById(R.id.editText20);
        final EditText club_name = (EditText)findViewById(R.id.editText7);
        final EditText keywords = (EditText)findViewById(R.id.editText8);
        final EditText email = (EditText)findViewById(R.id.editText9);
        final EditText descrip = (EditText)findViewById(R.id.editText10);

        // Listen for SAVE clicks and write the edit text fields to Firebase
        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Overwrite to database on save click; Specific to username so will overwrite fields
                // based on entered username


                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(username.getText().toString()))
                        {
                            final DatabaseReference newClubusername = ref.child(username.getText().toString());
                            final DatabaseReference newClubNames = newClubusername.child("Name");
                            final DatabaseReference descripRef = newClubusername.child("Description");
                            final DatabaseReference emailRef = newClubusername.child("Email");
                            final DatabaseReference keywordsRef = newClubusername.child("Keywords");

                            newClubusername.setValue(username.getText().toString());
                            newClubNames.setValue(club_name.getText().toString());
                            descripRef.setValue(descrip.getText().toString());
                            emailRef.setValue(email.getText().toString());
                            keywordsRef.setValue(keywords.getText().toString());
                            Toast.makeText(getApplicationContext(),"Update Successful!",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Check username accuracy; must have accurate username that corresponds to club to overwrite",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
