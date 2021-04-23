package com.example.foodieapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver MyReceiver;
    private RecyclerView mRecyclerView;
    private ArrayList<MealItem> mMealData;
    private MealItemAdapter mAdapter;
    private TextView mealTitle;
    private TextView mealDesc;
    private ImageView mealImg;
    private static final int INFO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mealTitle = findViewById(R.id.mealTitle);
        mealDesc = findViewById(R.id.subTitle);
        mealImg = findViewById(R.id.mealImage);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
        mMealData = new ArrayList<>();
        mAdapter = new MealItemAdapter(this, mMealData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(intent, INFO_REQUEST);
            }
        });

        IntentFilter intentFilter = new IntentFilter("com.example.I_AM_HOME");
        MyReceiver = new MyBroadcastReceiver();
        registerReceiver(MyReceiver, intentFilter);
    }

    private void initializeData() {
        String[] mealList = getResources().getStringArray(R.array.meal_titles);
        String[] mealInfo = getResources().getStringArray(R.array.meal_info);
        TypedArray mealImageResources = getResources().obtainTypedArray(R.array.meal_images);
        String[] mealCal = getResources().getStringArray(R.array.mealCalories);
        String[] mealIng = getResources().getStringArray(R.array.mealIngredients);
        String[] mealLink = getResources().getStringArray(R.array.mealLink);
        mMealData.clear();

        for(int i=0; i < mealList.length; i++){
            mMealData.add(new MealItem(mealList[i], mealInfo[i], mealImageResources.getResourceId(i,0), mealCal[i], mealIng[i], mealLink[i], null));
        }
        mealImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INFO_REQUEST){
            if(resultCode == RESULT_OK){
                TypedArray mealImageResources = getResources().obtainTypedArray(R.array.meal_images);
                String modifiedTitle = data.getStringExtra(AddItemActivity.EXTRA_MESSAGE);
                String modifyDesc = data.getStringExtra("description");
                String modCal = data.getStringExtra("calories");
                String modIng = data.getStringExtra("ingredients");
                String modLink = data.getStringExtra("link");
                String rUri = data.getStringExtra("image_uri");
                Uri newImage = Uri.parse(rUri);
                mMealData.add(new MealItem(modifiedTitle, modifyDesc, 1, modCal, modIng,modLink, newImage));
                mealImageResources.recycle();
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(MyReceiver != null)
            unregisterReceiver(MyReceiver);
    }

}