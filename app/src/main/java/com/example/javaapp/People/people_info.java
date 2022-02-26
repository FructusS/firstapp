package com.example.javaapp.People;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javaapp.Build.search_building;
import com.example.javaapp.R;
import com.example.javaapp.findSuppliers;
import com.example.javaapp.searchTaskActivity;
import com.google.android.gms.maps.MapView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

public class people_info extends AppCompatActivity {
    TextView name,salary,experienceY,experienceM,description;
    Button continueBtn;
    ImageView imagePeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);
        continueBtn = findViewById(R.id.continueBtnPeople);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(people_info.this, findSuppliers.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

            Bundle arg = getIntent().getExtras();
            if (arg != null) {
                String nameText = arg.getString("name");
                int salaryText = arg.getInt("salary");
                String experienceText = arg.getString("getExperience");

                String age = arg.getString("age");
                String descrText = arg.getString("descr");
                String imageLink = arg.getString("image");

                name = findViewById(R.id.name);
                salary = findViewById(R.id.salary);
                experienceY = findViewById(R.id.experienceY);

                description = findViewById(R.id.description);
                imagePeople = findViewById(R.id.imagePeople);
                name.setText(nameText + " , " + age);
                salary.setText(salaryText + " ₽ в мес.");
                experienceY.setText(experienceText);




                description.setText(descrText);
                Picasso.with(this).load(imageLink).into(imagePeople);

            }

        }
}