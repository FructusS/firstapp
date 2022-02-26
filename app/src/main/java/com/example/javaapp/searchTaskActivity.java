package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.javaapp.Build.search_building;
import com.example.javaapp.People.search_people;

public class searchTaskActivity extends AppCompatActivity {
    ImageButton imageButton;
    Button buttonSelectBuild;
    TypeTextView startTextSearchBuild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacrh_task);
        imageButton = findViewById(R.id.backBtnSearchBuildStart);
        buttonSelectBuild  = findViewById(R.id.buttonSelectBuild);
        startTextSearchBuild  = findViewById(R.id.startTextSearchBuild);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });

        buttonSelectBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(searchTaskActivity.this, search_building.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });
        startTextSearchBuild.animateText(getString(R.string.textFindBuild));
        startTextSearchBuild.setCharacterDelay(30);
    }
}