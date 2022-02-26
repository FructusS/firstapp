package com.example.javaapp.People;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.example.javaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class people_adapter extends RecyclerView.Adapter<people_adapter.MyViewClass>{
    ArrayList<People> peopleArrayList;
    Context context;

    public people_adapter(ArrayList<People> people,
                         Context context) {
        this.peopleArrayList = people;
        this.context = context;
    }
    public void filterList(ArrayList<People> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        peopleArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public people_adapter.MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_row, parent, false);
        people_adapter.MyViewClass myViewClass = new people_adapter.MyViewClass(v);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        final int pos = position;
        holder.namePeople.setText(peopleArrayList.get(position).getName() + ", " + checkAge(peopleArrayList.get(position).getAge()));
        holder.salaryPeople.setText(peopleArrayList.get(position).getSalary() + "₽");

        holder.experienceYPeople.setText(checkExperience(peopleArrayList.get(position).getExperienceY() ,peopleArrayList.get(position).getExperienceM()));
//        holder.experienceMPeople.setText(peopleArrayList.get(position).getExperienceM());

        Picasso.with(context).load(peopleArrayList.get(position).getImage()).into(holder.imagePeopleRow);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), people_info.class);
                intent.putExtra("name", peopleArrayList.get(pos).getName());
                intent.putExtra("salary", peopleArrayList.get(pos).getSalary());
                intent.putExtra("getExperience", checkExperience(peopleArrayList.get(pos).getExperienceY(),peopleArrayList.get(pos).getExperienceY()));

                intent.putExtra("descr", peopleArrayList.get(pos).getDescription());

                intent.putExtra("age", checkAge(peopleArrayList.get(pos).getAge()));
                intent.putExtra("image", peopleArrayList.get(pos).getImage());
                context.startActivity(intent);



            }

        });
    }
    public String checkAge(int age){
        String ageText;


        if ((age >= 5 && age <= 20) || (age >=25 && age <= 30) || ((age >=35 && age <= 40) || (age >=45)) ){
            ageText = "лет";
        }
        else if(age == 1){
            ageText = "год";
        }
        else{
            ageText = "года";
        }

        return  (age + " " + ageText).toString();
    }
    public String checkExperience(int expY , int expM){
        String month;







        if(expM <= 1){
            month = "месяц";
        }
        else if(expM >= 2 && expM <=4){
            month = "месяца";
        }
        else {
            month = "месяцев";
        }
        return   checkAge(expY) +", " + expM + " "+ month;


    }




    @Override
    public int getItemCount() {
        return peopleArrayList.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView namePeople,salaryPeople,experienceYPeople,experienceMPeople,agePeopleRow;

        ImageView imagePeopleRow;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            namePeople = itemView.findViewById(R.id.namePeopleRow);
            salaryPeople = itemView.findViewById(R.id.salaryPeopleRow);
            agePeopleRow = itemView.findViewById(R.id.agePeopleRow);
            experienceYPeople = itemView.findViewById(R.id.experienceMPeopleRow);
            experienceMPeople = itemView.findViewById(R.id.experienceMPeopleRow);
            imagePeopleRow = itemView.findViewById(R.id.imagePeopleRow);

        }
    }
}

