package com.example.javaapp.Build;

import java.util.ArrayList;
import java.util.List;

public class Buildings {
    String title,cost,street,metro,description,image;
    Double coords1,coords2;



    public Buildings(String title, String cost, String street, String metro, String description, String image, Double coords1, Double coords2) {
        this.title = title;

        this.cost = cost;
        this.street= street;
        this.metro= metro;
        this.description = description;
        this.image = image;
        this.coords1 = coords1;
        this.coords2 = coords2;
    }



    public String getTitle(){
        return title;
    }

    public String getCost(){
        return cost;
    }
    public String getStreet(){
        return street;
    }
    public String getMetro(){
        return metro;
    }
    public String getDescription(){
        return description;
    }


    public String getImage(){
        return image;
    }

    public double getCoords1(){
        return coords1;
    }
    public double getCoords2(){
        return coords2;
    }



}