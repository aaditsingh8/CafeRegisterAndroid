package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

/**
 * The Main Activity that starts up when the app launches. Shows Cafe Register options
 * @author Aadit Singh, Shivan Suratia
 */
public class MainActivity extends AppCompatActivity implements Serializable {

    public Order orderBasket;
    public StoreOrders storeOrders;

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * The event that the donut button is clicked. Starts the Donut Activity.
     * @param view the implicit View object.
     */
    public void onClickDonut(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        intent.putExtra("MAIN", this);
        startActivity(intent);
    }

    /**
     * The event that the coffee button is clicked. Starts the Coffee Activity.
     * @param view the implicit View object.
     */
    public void onClickCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        intent.putExtra("MAIN", this);
        startActivity(intent);
    }

    /**
     * The event that the your order button is clicked. Starts the Your Order Activity.
     * @param view the implicit View object.
     */
    public void onClickYourOrder(View view) {
        Intent intent = new Intent(this, YourOrderActivity.class);
        intent.putExtra("MAIN", this);
        startActivity(intent);
    }

    /**
     * The event that the store orders button is clicked. Starts the Store Orders Activity.
     * @param view the implicit View object.
     */
    public void onClickStoreOrders(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        intent.putExtra("MAIN", this);
        startActivity(intent);
    }
}