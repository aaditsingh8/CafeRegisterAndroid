package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class DonutActivity extends AppCompatActivity {

    private RecyclerView view;
    private ArrayList<DonutKind> donuts = new ArrayList<>(Arrays.asList(DonutKind.values()));
    private Donut donut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        view = findViewById(R.id.listDonuts);
        DonutsAdapter adapter = new DonutsAdapter(this, donuts);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }

//    public void onSelectingDonut(View view) {
//
//        Intent intent = new Intent(this, DonutOrderActivity.class);
//        startActivity(intent);
//    }
}