package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DonutActivity extends AppCompatActivity {

    private RecyclerView view;
    private Donut donut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        view = findViewById(R.id.listDonuts);
    }

    public void onSelectingDonut(View view) {

        Intent intent = new Intent(this, DonutOrderActivity.class);
        startActivity(intent);
    }
}