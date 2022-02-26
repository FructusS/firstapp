package com.example.javaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.javaapp.Build.search_building;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class activity_business_area extends AppCompatActivity {
    CardView coffeeCard, carCard, onlineShopCard, restaurantCard, franchiseCard, readyBusinessCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_area);
        coffeeCard = findViewById(R.id.coffeeCardView);
        coffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_business_area.this, coffee_house_activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        TypeTextView businessStartTextView = findViewById(R.id.businessStartTextView);
        businessStartTextView.animateText(getString(R.string.businessTextStart));


    }
}