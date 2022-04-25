package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StoreOrdersActivity extends AppCompatActivity {

    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        mainActivity = (MainActivity) getIntent().getSerializableExtra("MAIN");
    }
}