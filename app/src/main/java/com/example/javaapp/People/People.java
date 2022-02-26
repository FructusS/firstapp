package com.example.javaapp.People;

public class People {
    String name,description,image;
//
    int age,salary,experienceY,experienceM;



    public People(String name, int salary, int experienceY,int experienceM,  int age, String description, String image) {
        this.name = name;

        this.salary = salary;
        this.experienceY= experienceY;
        this.experienceM= experienceM;
        this.age= age;
        this.description = description;
        this.image = image;

    }



    public String getName(){
        return name;
    }

    public int getSalary(){
        return salary;
    }
    public int getExperienceY(){
        return experienceY;
    }
    public int getExperienceM(){
        return experienceM;
    }
    public int getAge(){
        return age;
    }
    public String getDescription(){
        return description;
    }


    public String getImage(){
        return image;
    }


}
