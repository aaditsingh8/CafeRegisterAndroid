package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Donut Activity that lets the user order donuts.
 * @author Aadit Singh, Shivan Suratia
 */
public class DonutActivity extends AppCompatActivity {

    private RecyclerView view;
    private ArrayList<DonutKind> donuts = new ArrayList<>(Arrays.asList(DonutKind.values()));

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        view = findViewById(R.id.listDonuts);
        DonutsAdapter adapter = new DonutsAdapter(this, donuts);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
    }
}