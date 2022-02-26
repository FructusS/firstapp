package com.example.javaapp.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.javaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class search_building extends AppCompatActivity {

    RecyclerView recyclerViewBuild;
    SearchView searchViewBuild;
    private ArrayList<Buildings> buildingsArrayList;
    private build_adapter buildAdapter;
    ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_building);
        recyclerViewBuild  = findViewById(R.id.rvBuild);
        backBtn = findViewById(R.id.backBtnSearchBuild);
        searchViewBuild = findViewById(R.id.searchViewBuild);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buildRV();

    }

    private String JsonDataFromAsset(String fileName) {
        String json = null;
        try{
            InputStream inputStream = getAssets().open(fileName);
            int sizeofFile = inputStream.available();
            byte[] buffer = new byte[sizeofFile];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");


        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return  json;
    }
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }

    private void buildRV(){
        buildingsArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewBuild.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset("build.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("build");
            for(int i =0;i<jsonArray.length();i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                String title = userData.getString("title");
                String cost = userData.getString("cost");
                String street = userData.getString("street");
                String metro = userData.getString("metro");
                String description = userData.getString("description");
                String image = userData.getString("image");
                Double coords1 = userData.getDouble("coords1");
                Double coords2 = userData.getDouble("coords2");
                buildingsArrayList.add(new Buildings(title,cost,street,metro,description,image,coords1,coords2));

            }
        }catch (JSONException e){
            e.printStackTrace();

        }
        buildAdapter = new build_adapter(buildingsArrayList,search_building.this);
        recyclerViewBuild.setAdapter(buildAdapter);
        search(searchViewBuild);
    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Buildings> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Buildings item : buildingsArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
            else if (item.getMetro().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
            else if (item.getStreet().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            buildAdapter.filterList(filteredlist);
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            buildAdapter.filterList(filteredlist);
        }
    }



}