package com.example.foodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] mealList = context.getResources().getStringArray(R.array.meal_titles);
        String[] mealInfo = context.getResources().getStringArray(R.array.meal_info);
        TypedArray mealImageResources = context.getResources().obtainTypedArray(R.array.meal_images);
        String[] mealCal = context.getResources().getStringArray(R.array.mealCalories);
        String[] mealIng = context.getResources().getStringArray(R.array.mealIngredients);
        String[] mealLink = context.getResources().getStringArray(R.array.mealLink);

        Random random = new Random();
        int length = mealList.length;
        int meal = random.nextInt(length);

        Intent detail = new Intent(context ,DetailActivity.class);
        detail.putExtra("text", mealList[meal]);
        detail.putExtra("subText", mealInfo[meal]);
        detail.putExtra("calorieText", mealCal[meal]);
        detail.putExtra("ingredientList", mealIng[meal]);
        detail.putExtra("linkText", mealLink[meal]);
        detail.putExtra("image_resource", mealImageResources.getResourceId(meal, 0));
        context.startActivity(detail);
        mealImageResources.recycle();
        String mealTitle = mealList[meal];
        Toast.makeText(context, "Happy Cooking! " + mealTitle, Toast.LENGTH_SHORT).show();
    }
}