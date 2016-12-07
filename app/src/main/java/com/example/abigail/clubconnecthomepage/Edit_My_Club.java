package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        final EditText email = (EditText)findViewById(R.id.editText9);
        final EditText descrip = (EditText)findViewById(R.id.editText10);

        // Listen for SAVE clicks and write the edit text fields to Firebase
        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
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


                            newClubusername.setValue(username.getText().toString());
                            if(club_name.getText().toString().matches("")) {
                                // If nothing was entered, keep the current value in the name key:value
                                String val = dataSnapshot.child(username.getText().toString()).child("Name").getValue().toString();
                                newClubNames.setValue(val);
                            }
                            else {
                                newClubNames.setValue(club_name.getText().toString());
                            }
                            if(email.getText().toString().matches("")) {
                                // If nothing was entered, keep the current value in the name key:value
                                String val_n = dataSnapshot.child(username.getText().toString()).child("Email").getValue().toString();
                                emailRef.setValue(val_n);
                            }
                            else {
                                emailRef.setValue(email.getText().toString());
                            }
                            if(descrip.getText().toString().matches("")) {
                                // If nothing was entered, keep the current value in the name key:value
                                String val_d = dataSnapshot.child(username.getText().toString()).child("Description").getValue().toString();
                                descripRef.setValue(val_d);
                            }
                            else {
                                descripRef.setValue(descrip.getText().toString());
                            }

                            // Inform the user of a succesful update provided the current username admins a club
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
