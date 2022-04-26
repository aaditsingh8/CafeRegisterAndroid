package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The Your Order Activity that gives the user current order options.
 * @author Aadit Singh, Shivan Suratia
 */
public class YourOrderActivity extends AppCompatActivity {

    private Order order;

    private ListView orderList;
    private TextView prompt;
    private TextView subTotalAmount1;
    private TextView salesTaxAmount1;
    private TextView totalAmount1;

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        order = MainActivity.orderBasket;

        orderList = findViewById(R.id.orderList);
        prompt = findViewById(R.id.prompt);
        subTotalAmount1 = findViewById(R.id.subTotalAmount1);
        salesTaxAmount1 = findViewById(R.id.salesTaxAmount1);
        totalAmount1 = findViewById(R.id.totalAmount1);

        handleItemClick();
        displayPrices();
    }

    /**
     * Handles the event that an item is clicked in the order items list.
     * Deleted the item from the order if a confirmation is given.
     */
    public void handleItemClick() {
        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<>
                (this,
                        android.R.layout.simple_list_item_1,
                        order.getItems());
        orderList.setAdapter(arrayAdapter);
        if (arrayAdapter.isEmpty()) {
            prompt.setText(getString(R.string.no_items_prompt));
        }

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Handles the event that an item is clicked form the items list.
             * @param adapterView the AdapterView where the click happened.
             * @param view the view within the AdapterView that was clicked.
             * @param i the position of the view in the adapter.
             * @param l the row id of the item that is clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(adapterView.getContext());
                alert.setTitle(getString(R.string.delete_item_text));
                alert.setMessage(getString(R.string.delete_item_message) +
                        arrayAdapter.getItem(i));
                setDeleteAlert(adapterView, alert, arrayAdapter, i);
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Sets the events for the Delete Order alert box.
     * @param adapterView the AdapterView object which results in the event.
     * @param alert the alert object.
     * @param arrayAdapter the array adapter object for the adapter view.
     * @param i the position of the item to be deleted.
     */
    private void setDeleteAlert(AdapterView<?> adapterView, AlertDialog.Builder alert,
                                ArrayAdapter<MenuItem> arrayAdapter, int i) {
        // On click "YES"
        alert.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.orderBasket.remove(order);
                    arrayAdapter.remove(arrayAdapter.getItem(i));
                    displayPrices();
                    if (arrayAdapter.isEmpty()) {
                        prompt.setText(getString(R.string.no_items_prompt));
                    }
                    Toast.makeText(adapterView.getContext(),
                            getString(R.string.deleted_message),
                            Toast.LENGTH_SHORT).show();
                }
        // On click "NO"
        }).setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(adapterView.getContext(),
                            getString(R.string.deleted_not_message),
                            Toast.LENGTH_SHORT).show();
                }
        });
    }

    /**
     * Handles the event that the "Place Order" button is pressed.
     * Removes item from order.
     */
    public void onClickPlaceOrder(View view) {
        if (order.getItems().isEmpty()) {
            Toast.makeText(this,
                    getString(R.string.order_is_empty),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.place_order_alert_text));
        alert.setMessage(getString(R.string.place_order_alert_message));
        setPlaceAlert(view, alert);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Sets the events for the Place Order alert box.
     * @param view the implicit view object.
     * @param alert the alert object.
     */
    private void setPlaceAlert(View view, AlertDialog.Builder alert) {
        alert.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        order = MainActivity.placeOrder(order);
                        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<>
                                (view.getContext(),
                                android.R.layout.simple_list_item_1,
                                new ArrayList<MenuItem>());
                        orderList.setAdapter(arrayAdapter);
                        prompt.setText(getString(R.string.no_items_prompt));
                        displayPrices();
                        Toast.makeText(view.getContext(),
                                getString(R.string.order_placed),
                                Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getString(R.string.order_not_placed),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Gets subtotal, tax, and total price. Updates respective labels.
     */
    private void displayPrices() {
        double subTotal = order.getTotal();
        double salesTax = order.getTaxes();
        double total = order.getTotalWithTaxes();
        subTotalAmount1.setText(MainActivity.formatAmount(subTotal));
        salesTaxAmount1.setText(MainActivity.formatAmount(salesTax));
        totalAmount1.setText(MainActivity.formatAmount(total));
    }
}