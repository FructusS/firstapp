package com.example.javaapp.Supplier;

public class Supplier {
    int cost;
    double rating;
    String description,title,image;
    public Supplier(int cost,double rating,String description,String title,String image){
        this.cost = cost;
        this.rating = rating;
        this.description = description;
        this.title = title;

        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

}

