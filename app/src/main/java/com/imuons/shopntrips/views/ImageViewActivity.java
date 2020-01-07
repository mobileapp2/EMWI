package com.imuons.shopntrips.views;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.imuons.shopntrips.R;

public class ImageViewActivity extends AppCompatActivity {

    ImageView imageView;

    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        imageView = findViewById(R.id.img);
        String image_url = getIntent().getStringExtra("uploadImage");
        Glide.with(this).load(image_url).into(imageView);


    }


}
