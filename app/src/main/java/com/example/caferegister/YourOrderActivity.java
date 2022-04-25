package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class YourOrderActivity extends AppCompatActivity {

    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        mainActivity = (MainActivity) getIntent().getSerializableExtra("MAIN");
    }
}