package com.filip.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Button findNearbyLunchButton;
    EditText findLunchEditText;
    RelativeLayout relativeLayout;
    ImageView mealImageView;
    Button addFoodButton;


    public void findLunch(View view){
        String typeOfFood = findLunchEditText.getText().toString();

        // Search for restaurants nearby
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants" + " " + typeOfFood);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findNearbyLunchButton = (Button) findViewById(R.id.findNearbyLunchButton);
        findLunchEditText = (EditText) findViewById(R.id.findLunchEditText);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        mealImageView = (ImageView) findViewById(R.id.mealImageView);
        addFoodButton = (Button) findViewById(R.id.addFoodButton);

        //set listeners to hide keyboard input
        relativeLayout.setOnClickListener(this);
        mealImageView.setOnClickListener(this);

        //set listener to add food type
        addFoodButton.setOnClickListener(this);

        //set on key listener to proceed when user tapped enter
        findLunchEditText.setOnKeyListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main || v.getId() == R.id.mealImageView){
            //hide keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } else if(v.getId() == R.id.addFoodButton){
            Intent intent = new Intent(getApplicationContext(), SearchFoodTypeActivity.class);
            startActivityForResult(intent,1);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
            findLunch(v);
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            findLunchEditText.setText(data.getStringExtra("chosenFoodType"));
        }
    }
}
