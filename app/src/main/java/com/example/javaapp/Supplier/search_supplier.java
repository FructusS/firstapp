package com.example.javaapp.Supplier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.javaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class search_supplier extends AppCompatActivity {
    RecyclerView recyclerViewSupplier;
    SearchView searchViewSupplier;
    ImageButton backBtn;
    private ArrayList<Supplier> supplierArrayList;
    private supplier_adapter supplierAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_supplier);
        recyclerViewSupplier = findViewById(R.id.rvSupplier);
        searchViewSupplier = findViewById(R.id.searchViewSupplier);
        backBtn = findViewById(R.id.backBtnSearchSupplier);
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
        supplierArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSupplier.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset("supplier.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("supplier");
            for(int i =0;i<jsonArray.length();i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                String title = userData.getString("title");
                int cost = userData.getInt("cost");
                double rating = userData.getDouble("rating");

                String description = userData.getString("description");
                String image = userData.getString("image");
                if (image == null){
                    image = "https://i.imgur.com/5saXjI7.jpg";
                }

                supplierArrayList.add(new Supplier(cost,rating,description,title,image));

            }
        }catch (JSONException e){
            e.printStackTrace();

        }
        supplierAdapter = new supplier_adapter(supplierArrayList, search_supplier.this);
        recyclerViewSupplier.setAdapter(supplierAdapter);
        search(searchViewSupplier);
    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Supplier> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Supplier item : supplierArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }

        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            supplierAdapter.filterList(filteredlist);
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            supplierAdapter.filterList(filteredlist);
        }
    }
}