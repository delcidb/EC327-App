package com.example.abigail.clubconnecthomepage;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Student_Search extends Activity{

    //Defines objects and variables
    public static class UsersClubs {

        private String Description;
        private String Email;
        private String Keywords;
        private String Name;

        public UsersClubs(){
        }

        public UsersClubs(String description, String email, String keywords, String name){
            this.Description = description;
            this.Email = email;
            this.Keywords = keywords;
            this.Name = name;
        }

        public String getDescription(){
            return Description;
        }

        public String getEmail() {
            return Email;
        }

        public String getKeywords(){
            return Keywords;
        }

        public String getName() {
            return Name;
        }

    }

    //Student_Search connects to studentclubsearch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentclubsearch);

        final ListView mListView = (ListView) findViewById(R.id.listview);
        mListView.setClickable(true); // Sets so we can later click items to get club descriptions

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("fir-practice-7403d");

        // Setting a FirebaseAdapter to translate JSON data into the ListView
        final FirebaseListAdapter mAdapter = new FirebaseListAdapter<UsersClubs>(this,UsersClubs.class,android.R.layout.two_line_list_item,ref) {

            protected void populateView(View v, final UsersClubs usersClubs, int position) {

                ((TextView)v.findViewById(android.R.id.text1)).setText(usersClubs.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(usersClubs.getEmail());
                // Also creating local storage to add search functionality

            }

        };

        mListView.setAdapter(mAdapter);

        // Allocate space for an array list to hold favorites that will be shown on another favorites screen
        final ArrayList<CharSequence> favArray = new ArrayList<>();

        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> p, View v,final int po, long id) {


                Object desired = mListView.getItemAtPosition(po);

                // Locally store the favorited club in the ArrayList
                CharSequence nameToSave = ((UsersClubs) desired).getName();
                CharSequence emailToSave = ((UsersClubs) desired).getEmail();

                favArray.add(nameToSave);
                favArray.add(emailToSave);

                Toast.makeText(getApplicationContext(), "Added to Favorites!", Toast.LENGTH_LONG).show();
                return true; // Need this statement to be sure that onItemClick is not invoked too

            }
        });

        Button button = (Button) findViewById(R.id.favButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Club_Favorites.class);

                // Passing the favorites from long click to the favorites activity
                i.putCharSequenceArrayListExtra("favArray",favArray);
                startActivity(i);

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

                Object thing = mListView.getItemAtPosition(position);
                //Log.i("class",thing.getClass().toString());
                CharSequence message = ((UsersClubs) thing).getDescription();
                //Log.i("class",message.toString());
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

            }

        });





        /* The following changes the ListView to adapt to keyword search functionality
        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mListView.setAdapter(null); // Set the List View to store nothing to prep for search match
                //String keywordEntered = ""; // Clear the keyword entered to hold new EditText entry
                ArrayList<Integer> intArray = new ArrayList<>();
                EditText keyTextField = (EditText) findViewById(R.id.editText2);
                String keywordEntered = keyTextField.getText().toString();
                Log.i("size",String.valueOf(keyArr.size()));
                Log.i("Entered keyword",keywordEntered);
                for(int i = 0; i < keyArr.size()-8; i++){ // hard coded so we need to find a way to get num of list items
                    Object newThing = mListView.getItemAtPosition(i);
                    String keywordAtIndex = ((UsersClubs) newThing).getKeywords();
                    Log.i("Key at INDEX",keywordEntered);
                    if(keywordAtIndex.matches(keywordEntered))
                    {
                        Log.i("Indexed keyword IF",keywordAtIndex);
                    }
                    else
                    {
                        intArray.add(i); // Add the object index into the intArray to indicate it wont populate list
                        Log.i("Indexed keyword ELSE",keywordAtIndex);
                    }
                }
                Log.i("SIZEAFTER",String.valueOf(intArray.size()));
            }
        });*/


    }

}