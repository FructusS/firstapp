package com.example.javaapp.People;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class search_people extends AppCompatActivity {
    RecyclerView recyclerViewPeople;
    SearchView searchViewPeople;
    private ArrayList<People> peopleArrayList;
    private people_adapter peopleAdapter;
    ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);
        recyclerViewPeople  = findViewById(R.id.rvPeople);
        searchViewPeople = findViewById(R.id.searchViewPeople);
        backBtn = findViewById(R.id.backBtnSearchPeople);
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
        peopleArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPeople.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset("people.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("people");
            for(int i =0;i<jsonArray.length();i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                String name = userData.getString("name");
                int age = userData.getInt("age");
                int salary = userData.getInt("salary");
                int experienceYear = userData.getInt("experienceYear");
                int experienceMonth = userData.getInt("experienceMonth");
                String description = userData.getString("description");
                String image = userData.getString("image");
                if (image == null){
                    image = "https://i.imgur.com/5saXjI7.jpg";
                }

                peopleArrayList.add(new People(name,salary,experienceYear,experienceMonth,age,description,image));

            }
        }catch (JSONException e){
            e.printStackTrace();

        }
        peopleAdapter = new people_adapter(peopleArrayList, search_people.this);
        recyclerViewPeople.setAdapter(peopleAdapter);
        search(searchViewPeople);
    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<People> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (People item : peopleArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }

        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            peopleAdapter.filterList(filteredlist);
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            peopleAdapter.filterList(filteredlist);
        }
    }



}