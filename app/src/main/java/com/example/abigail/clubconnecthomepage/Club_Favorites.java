package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joshuasurette on 12/7/16.
 */

public class Club_Favorites extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_screen);

        Intent intent = getIntent();
        ArrayList<String> favArray = intent.getStringArrayListExtra("favArray");

        TextView setTextFavs = (TextView) findViewById(R.id.nameAdder);


        for (int i = 0; i < favArray.size(); i ++) {

            setTextFavs.append(favArray.get(i));

        }

    }

}
