package com.example.karan.bikerent.models;


public class BikeCategoryObject {

    private int id;
    public int imagePath;
    public String imageName;

    public BikeCategoryObject(int id, int imagePath, String imageName) {
        this.id = id;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public BikeCategoryObject(int imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }


    public int getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }
}
