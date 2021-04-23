package com.example.foodieapp;

import android.net.Uri;

import java.net.URI;

public class MealItem {
    private String title;
    private  String info;
    private final int imageResource;
    private String calories;
    private String ingredients;
    private String link;
    private Uri uri;

    MealItem(String title, String info, int imageResource, String cal, String ing, String link, Uri uri){
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.calories = cal;
        this.ingredients = ing;
        this.link = link;
        this.uri = uri;
    }

    public int getImageResource(){ return imageResource;}
    String getTitle() { return title;}
    String getInfo() { return info;}
    String getCalories(){ return  calories;}
    String getIngredients(){ return ingredients;}
    String getLink(){ return link;}
    Uri getUri(){ return uri;}
}
