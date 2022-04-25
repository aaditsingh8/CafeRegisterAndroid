package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity {

    private MainActivity mainActivity;
    private Order order;
    private List<Order> allOrdersList;
    private ListView allOrdersListView;
    private TextView totalAmount;
    private Button cancelOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        mainActivity = (MainActivity) getIntent().getSerializableExtra("MAIN");
    }

    /**
     * Handles the event that the "Remove Item" button is pressed.
     * Removes item from order.
     * @param view the implicit view object.
     */
    public void onClickCancelOrder(View view) {
        MainActivity.orderBasket.add(1);

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.coffee_toast);
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }
}