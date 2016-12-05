package com.example.abigail.clubconnecthomepage;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class Student_Search extends Activity{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentclubsearch);

        final ListView mListView = (ListView) findViewById(R.id.listview);
        mListView.setClickable(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("fir-practice-7403d");

        final FirebaseListAdapter mAdapter = new FirebaseListAdapter<UsersClubs>(this,UsersClubs.class,android.R.layout.two_line_list_item,ref) {
            @Override
            protected void populateView(View v, final UsersClubs usersClubs, int position) {

                ((TextView)v.findViewById(android.R.id.text1)).setText(usersClubs.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(usersClubs.getEmail());


            }
        };

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

                Object thing = mListView.getItemAtPosition(position);
                //Log.i("class",thing.getClass().toString());
                CharSequence message = (CharSequence)((UsersClubs) thing).getDescription();
                //Log.i("class",message.toString());
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

            }
        });

    }

}