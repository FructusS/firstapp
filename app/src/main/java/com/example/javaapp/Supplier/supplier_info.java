package com.example.javaapp.Supplier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javaapp.R;
import com.example.javaapp.searchTaskActivity;
import com.google.android.gms.maps.MapView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

public class supplier_info extends AppCompatActivity {
    TextView titleSupplier,costSupplier,ratingSupplier,street,descr;
    ImageView image;
    Button continueBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_info);






            continueBtn = findViewById(R.id.continueBtnBuild);

            Bundle arg = getIntent().getExtras();
            if (arg != null) {
                String titleText = arg.getString("title");
                int costText = arg.getInt("cost");

                double ratingText = arg.getDouble("rating");
                String descrText = arg.getString("descr");
                String imageLink = arg.getString("image");

                titleSupplier = findViewById(R.id.titleSupplier);
                costSupplier = findViewById(R.id.costSupplier);
                ratingSupplier = findViewById(R.id.ratingSupplier);

                descr = findViewById(R.id.descriptionSupplier);
                image = findViewById(R.id.imageSupplier);
                titleSupplier.setText(titleText);
                costSupplier.setText(costText + " ₽");
                ratingSupplier.setText(String.valueOf(ratingText));

                descr.setText(descrText);
                Picasso.with(this).load(imageLink).into(image);

            }




//            continueBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), searchTaskActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                    startActivity(intent);
//                }
//            });
        }
    }
