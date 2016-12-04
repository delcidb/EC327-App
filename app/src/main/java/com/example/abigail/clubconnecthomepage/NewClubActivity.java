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

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChild(username.getText().toString())) { // Checks to be sure user only owns one club
                            final DatabaseReference newClubusername = ref.child(username.getText().toString());
                            final DatabaseReference newClubNames = newClubusername.child("Name");
                            final DatabaseReference descripRef = newClubusername.child("Description");
                            final DatabaseReference emailRef = newClubusername.child("Email");
                            final DatabaseReference keywordsRef = newClubusername.child("Keywords");

                            if(club_name.toString().equals("")) {
                                newClubNames.setValue(club_name.getText().toString());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Must add a club name",Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(descrip.toString().equals("")) {
                                descripRef.setValue(descrip.getText().toString());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Must add a description",Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(email.toString().equals("")) {
                                emailRef.setValue(email.getText().toString());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Must add an email",Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(keywords.toString().equals("")) {
                                keywordsRef.setValue(keywords.getText().toString());
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Must add keywords",Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(getApplicationContext(),"Congrats and welcome to ClubConnect!",Toast.LENGTH_LONG).show();
                            return;

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Sorry, same username owns another club; can only own one club",Toast.LENGTH_LONG).show();
                            username.setText(""); // clear the text
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                /* Write to database on save click
                final DatabaseReference newClubusername = ref.child(username.getText().toString());
                final DatabaseReference newClubNames = newClubusername.child("Name");
                final DatabaseReference descripRef = newClubusername.child("Description");
                final DatabaseReference emailRef = newClubusername.child("Email");
                final DatabaseReference keywordsRef = newClubusername.child("Keywords");

                newClubusername.setValue(username.getText().toString());
                newClubNames.setValue(club_name.getText().toString());
                descripRef.setValue(descrip.getText().toString());
                emailRef.setValue(email.getText().toString());
                keywordsRef.setValue(keywords.getText().toString());*/
            }
        });


    }


}