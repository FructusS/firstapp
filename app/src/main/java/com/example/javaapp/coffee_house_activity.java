package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.javaapp.Build.search_building;
import com.example.javaapp.People.search_people;

public class coffee_house_activity extends AppCompatActivity {
    ImageButton imageButton;
    Button businessButton;
    TypeTextView coffeeTextStart , algorithmText, textbuild,textEmpl,textsup,textfinance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_house);
        imageButton = findViewById(R.id.backBtnCoffeeHouse);
        businessButton = findViewById(R.id.businessButton);
        coffeeTextStart = findViewById(R.id.coffeeTextStart);
        algorithmText = findViewById(R.id.algorithmText);
        textbuild = findViewById(R.id.textbuild);
        textEmpl = findViewById(R.id.textEmpl);
        textsup = findViewById(R.id.textsup);
        textfinance = findViewById(R.id.textfinance);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onBackPressed();

            }
        });
        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(coffee_house_activity.this, searchTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        coffeeTextStart.setCharacterDelay(30);
        coffeeTextStart.animateText(getString(R.string.coffeeTextStart));

        animateTextCoffee();



    }
    public void animateTextCoffee(){
        textbuild.setCharacterDelay(100);
        textEmpl.setCharacterDelay(100);
        textsup.setCharacterDelay(100);
        textfinance.setCharacterDelay(100);

        textbuild.animateText("Помещение");
        textEmpl.animateText("Работники");
        textsup.animateText("Поставщики");
        textfinance.animateText("Финансы");
        algorithmText();
    }
    public void algorithmText(){
        algorithmText.setCharacterDelay(30);
        algorithmText.animateText("Сейчас вам наш алгоритм соберт все необходимые данные");
    }

}