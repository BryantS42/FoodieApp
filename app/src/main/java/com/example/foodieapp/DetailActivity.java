package com.example.foodieapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String uri = getIntent().getStringExtra("image_uri");
        TextView subTitle = findViewById(R.id.subTitle);
        TextView mealTitle = findViewById(R.id.mealTitle_detail);
        ImageView mealImage = findViewById(R.id.mealImage_detail);
        TextView cals = findViewById(R.id.mealCalorie_detail);
        TextView ing = findViewById(R.id.mealIng_detail);
        TextView link = findViewById(R.id.mealLink_detail);
        mealTitle.setText(getIntent().getStringExtra("text"));
        subTitle.setText(getIntent().getStringExtra("subText"));
        cals.setText(getIntent().getStringExtra("calorieText"));
        ing.setText(getIntent().getStringExtra("ingredientList"));
        link.setText(getIntent().getStringExtra("linkText"));
        if(uri == null) {
            Glide.with(this).load(getIntent().getIntExtra("image_resource", 0)).into(mealImage);
        } else{
            Uri newUri = Uri.parse(uri);
            Glide.with(this).load(newUri).into(mealImage);
    }

} }
