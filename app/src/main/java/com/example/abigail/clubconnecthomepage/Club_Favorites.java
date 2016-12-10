package com.example.abigail.clubconnecthomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
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
        setTextFavs.setMovementMethod(new ScrollingMovementMethod());

        for (int i = 0; i < favArray.size(); i ++) {

            if(i == 0){
                setTextFavs.append("------------------------------------------------------");
            }
            setTextFavs.append('\n' + favArray.get(i));
            if(favArray.get(i).contains("@")){ // Used to separate each entry in name:email format
                setTextFavs.append('\n' + "------------------------------------------------------");
            }

        }

    }

}