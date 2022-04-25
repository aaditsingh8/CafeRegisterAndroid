package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import java.sql.Array;
import java.util.List;

public class YourOrderActivity extends AppCompatActivity {

    private static final double SALES_TAX = .0625;
    private MainActivity mainActivity;
    private Order order;
    private List<MenuItem> orderItems;
    private ListView orderList;
    private Button removeItem = (Button)findViewById(R.id.removeItem);
    private Button placeOrder = (Button)findViewById(R.id.placeOrder);
    private TextView subTotalAmount1 = (TextView)findViewById(R.id.subTotalAmount1);
    private TextView salesTaxAmount1 = (TextView)findViewById(R.id.salesTaxAmount1);
    private TextView totalAmount1 = (TextView)findViewById(R.id.totalAmount1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        mainActivity = (MainActivity) getIntent().getSerializableExtra("MAIN");
        order = mainActivity.getOrderBasket();
        orderList = (ListView)findViewById(R.id.orderList);

        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<MenuItem>(
                this,
                android.R.layout.simple_list_item_1,
                orderItems);
        orderList.setAdapter(arrayAdapter);

        getPrices();
    }

    public void getCurrentOrder() {
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object currentOrder = orderList.getItemAtPosition(i);
            }
        });
    }

    /**
     * Handles the event that the "Remove Item" button is pressed.
     * Removes item from order.
     * @param view the implicit view object.
     */
    public void onClickRemoveOrder(View view) {
        MainActivity.orderBasket.remove(order);
        getPrices();
    }

    /**
     * Handles the event that the "Place Order" button is pressed.
     * Removes item from order.
     * @param view the implicit view object.
     */
    public void onClickPlaceOrder(View view) {
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object currentOrder = orderList.getItemAtPosition(i);
            }
        });
        MainActivity.placeOrder(order);
    }

    /**
     * Gets subtotal, tax, and total price. Updates respective labels.
     */
    private void getPrices() {
        double subTotal = order.getTotalAmount();
        double salesTax = subTotal * SALES_TAX;
        double total = subTotal + salesTax;
        order.setTotalWithTaxes(total);
        subTotalAmount1.setText(MainActivity.formatAmount(subTotal));
        salesTaxAmount1.setText(MainActivity.formatAmount(salesTax));
        totalAmount1.setText(MainActivity.formatAmount(total));
    }
}
