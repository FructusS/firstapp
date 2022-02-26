package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.javaapp.People.search_people;
import com.example.javaapp.Supplier.search_supplier;

public class searchEmployee extends AppCompatActivity {
    ImageButton backbtn;
    Button buttonSelectEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);
        backbtn = findViewById(R.id.backBtnSearchEmployeeStart);
        buttonSelectEmployee = findViewById(R.id.buttonSelectEmployee);
        buttonSelectEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchEmployee.this, search_people.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
        TypeTextView textView = findViewById(R.id.startTextSearchEmployee);
        textView.animateText(getText(R.string.textFindEmployee));
    }
}