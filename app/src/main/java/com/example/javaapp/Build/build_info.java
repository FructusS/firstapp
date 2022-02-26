package com.example.javaapp.Build;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaapp.LoginActivity;
import com.example.javaapp.People.People;
import com.example.javaapp.People.search_people;
import com.example.javaapp.R;
import com.example.javaapp.RegistrationActivity;
import com.example.javaapp.activity_business_area;

import com.example.javaapp.searchEmployee;
import com.example.javaapp.searchTaskActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

public class build_info extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAPVIEW_BUNDLE_KEY = "AIzaSyAnxJxJVUEe358c5DKdSwtbPz_oILvK7Dk";
    TextView title,cost,metro,street,descr;
    ImageView image;
    double coords1,coords2;
    private MapView mMapView;
    Button continueBtn;
    ImageButton backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);


        setContentView(R.layout.activity_build_info);
        continueBtn = findViewById(R.id.continueBtnBuild);
        backBtn = findViewById(R.id.backBtnBuild);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            String titleText = arg.getString("title");
            String costText = arg.getString("cost");
            String metroText = arg.getString("metro");
            String streetText = arg.getString("street");
            String descrText = arg.getString("descr");
            String imageLink = arg.getString("image");
            coords1 = arg.getDouble("coords1");
            coords2 = arg.getDouble("coords2");
            title = findViewById(R.id.title);
            cost = findViewById(R.id.cost);
            metro = findViewById(R.id.metro);
            street = findViewById(R.id.street);
            descr = findViewById(R.id.description);
            image = findViewById(R.id.imageBuild);
            title.setText(titleText);
            cost.setText(costText + " ₽ в мес.");
            metro.setText(metroText);
            street.setText(streetText);
            descr.setText(descrText);
            Picasso.with(this).load(imageLink).into(image);

        }


        mMapView = findViewById(R.id.mapView);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), searchEmployee.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {


        LatLng market = new LatLng(coords1, coords2);
        map.animateCamera(CameraUpdateFactory.newLatLng(market));

        map.setMaxZoomPreference(18.0f);
        map.setMinZoomPreference(6.0f);
        map.addMarker(new MarkerOptions()


                .position(market)
                .draggable(true)
                .flat(true)

        );
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}