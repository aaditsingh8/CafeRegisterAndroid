package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * The Store Orders Activity that lets the user manage placed orders.
 * @author Aadit Singh, Shivan Suratia
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ArrayAdapter<Order> orders;

    private List<Order> storeOrders;
    private Spinner orderNumberSelect;
    private ListView allOrdersListView;
    private TextView totalAmount;
    private TextView noOrdersPrompt;

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        storeOrders = MainActivity.storeOrders.getOrders();

        allOrdersListView = findViewById(R.id.allOrdersList);
        orderNumberSelect = findViewById(R.id.orderNumberSelect);
        totalAmount = findViewById(R.id.totalAmount);
        noOrdersPrompt = findViewById(R.id.noOrdersPrompt);

        handleOrderSelection();
    }

    /**
     * Sets up the order selection spinner.
     * Handles the event that an order is selected from the order selection spinner.
     */
    private void handleOrderSelection() {
        if (setNoOrdersPrompt()) {
            return;
        }
        orders = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                storeOrders);
        orderNumberSelect.setAdapter(orders);

        orderNumberSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles the event that an order is selected form the order selection spinner.
             * @param adapterView the AdapterView where the selection happened.
             * @param view the view within the AdapterView that was clicked.
             * @param position the position of the view in the adapter.
             * @param l the row id of the item that is selected.
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {
                Order order = storeOrders.get(position);
                ArrayAdapter<MenuItem> items = new ArrayAdapter<>(adapterView.getContext(),
                        android.R.layout.simple_list_item_1,
                        order.getItems());
                allOrdersListView.setAdapter(items);
                totalAmount.setText(MainActivity.formatAmount(order.getTotalWithTaxes()));
            }

            /**
             * Handles the event that nothing is changed in the quantity spinner.
             * @param adapterView the AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    /**
     * Displays a prompt if there are no orders to display.
     */
    private boolean setNoOrdersPrompt() {
        if (storeOrders.isEmpty()) {
            noOrdersPrompt.setText(getString(R.string.no_orders_prompt));
            return true;
        }
        return false;
    }

    /**
     * Handles the event that the "Remove Item" button is pressed.
     * Removes item from order.
     * @param view the implicit view object.
     */
    public void onClickCancelOrder(View view) {
        if (storeOrders.isEmpty()) {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.no_orders);
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.cancel_order_alert_text));
        alert.setMessage(getString(R.string.cancel_order_alert_message));
        setDeleteOrderAlert(view, alert);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Sets the events for the Place Order alert box.
     * @param view the implicit view object.
     * @param alert the alert object.
     */
    private void setDeleteOrderAlert(View view, AlertDialog.Builder alert) {
        alert.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Order order = (Order) orderNumberSelect.getSelectedItem();
                        orders.remove(order);
                        Order newOrder = MainActivity.cancelOrder(order);
                        if (newOrder == null) {
                            ArrayAdapter<MenuItem> blankItemsArrayAdapter = new ArrayAdapter<>
                                    (view.getContext(),
                                    android.R.layout.simple_list_item_1,
                                    new ArrayList<>());
                            allOrdersListView.setAdapter(blankItemsArrayAdapter);
                            setNoOrdersPrompt();
                            totalAmount.setText(MainActivity.formatAmount(0));
                            return;
                        }
                        Toast.makeText(view.getContext(),
                                getString(R.string.order_cancelled),
                                Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getString(R.string.order_not_cancelled),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}