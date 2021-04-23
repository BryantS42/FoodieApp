package com.example.foodieapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MealItemHolder> {
    private ArrayList<MealItem> mMealData;
    private Context mContext;


    MealItemAdapter(Context context, ArrayList<MealItem> mealData){
        this.mMealData = mealData;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MealItemAdapter.MealItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealItemHolder(LayoutInflater.from(mContext).inflate(R.layout.list_meal, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MealItemAdapter.MealItemHolder holder, int position) {
        MealItem currentMeal = mMealData.get(position);
        holder.bindTo(currentMeal);
    }

    @Override
    public int getItemCount() {
        return mMealData.size();
    }

     class MealItemHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private static final String LOG_TAG = "";
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mMealImage;

        MealItemHolder(View itemView){
            super(itemView);
            mTitleText = itemView.findViewById(R.id.mealTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mMealImage = itemView.findViewById(R.id.mealImage);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bindTo(MealItem currentMeal){
            mTitleText.setText(currentMeal.getTitle());
            mInfoText.setText(currentMeal.getInfo());
            Uri check = currentMeal.getUri();
            if(check == null) {
                Glide.with(mContext).load(currentMeal.getImageResource()).into(mMealImage);
            } else {
                Glide.with(mContext).load(check).into(mMealImage);

            }
        }

        @Override
        public void onClick(View v) {
            MealItem currentMeal = mMealData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("text", currentMeal.getTitle());
            detailIntent.putExtra("subText", currentMeal.getInfo());
            detailIntent.putExtra("calorieText", currentMeal.getCalories());
            detailIntent.putExtra("ingredientList", currentMeal.getIngredients());
            detailIntent.putExtra("linkText", currentMeal.getLink());
            if(currentMeal.getUri() == null) {
                detailIntent.putExtra("image_resource", currentMeal.getImageResource());
            } else {
                detailIntent.putExtra("image_uri", currentMeal.getUri().toString());
            }
            mContext.startActivity(detailIntent);
        }


        @Override
        public boolean onLongClick(View v) {
            AlertDialog askOption = Ask();
            askOption.show();
            return false;
        }

        private AlertDialog Ask(){
            AlertDialog ask = new AlertDialog.Builder(mContext)
                    .setTitle("Delete")
                    .setMessage("Would you like to delete?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mMealData.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            return ask;
        }
    }
}
