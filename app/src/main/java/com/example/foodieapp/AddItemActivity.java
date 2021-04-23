package com.example.foodieapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.Foodie_App.extra.MESSAGE";
    private static final int GALLERY_REQUEST = 1;
    EditText newTitle;
    EditText newDesc;
    EditText newCal;
    EditText newIng;
    EditText newLink;
    ImageView newImg;
    Uri image;
    Bitmap bmp2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        newTitle = findViewById(R.id.et_ModifyTitle);
        newDesc = findViewById(R.id.et_ModifyDescription);
        newCal = findViewById(R.id.et_ModifyCalories);
        newIng = findViewById(R.id.et_ModifyIngredient);
        newLink = findViewById(R.id.et_ModifyLink);
        newImg = findViewById(R.id.modifyImage);

    }

    public void addImage(View view) {
        startActivityForResult(new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            image = data.getData();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) { getContentResolver().takePersistableUriPermission (image, Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION); }
            newImg.setImageURI(image);
            try{
                Bitmap bitImage = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                bmp2 = bitImage.copy(bitImage.getConfig(), true);
                newImg.setImageBitmap(bitImage);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void finished(View view) {
        Intent i = new Intent();
        i.putExtra(EXTRA_MESSAGE, newTitle.getText().toString());
        i.putExtra("description", newDesc.getText().toString());
        i.putExtra("calories", newCal.getText().toString());
        i.putExtra("ingredients", newIng.getText().toString());
        i.putExtra("link", newLink.getText().toString());
        i.putExtra("image_uri", image.toString());
        setResult(RESULT_OK, i);
        finish();
    }





}
