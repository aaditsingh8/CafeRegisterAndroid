package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The Donut Activity that lets the user order coffee.
 * @author Aadit Singh, Shivan Suratia
 */
public class DonutOrderActivity extends AppCompatActivity {

    private static final Integer[] INT_QUANTITIES = {1, 2, 3, 4, 5};

    private ImageView donutImage;
    private TextView donutName;
    private Spinner donutQuantity;
    private EditText donutSubtotal;

    private Donut donut;

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        DonutKind donutKind = (DonutKind) getIntent().getSerializableExtra("DONUT");
        donut = new Donut(donutKind);

        donutImage = findViewById(R.id.orderDonutImage);
        donutName = findViewById(R.id.orderDonutName);
        donutQuantity = findViewById(R.id.donutQuantity);
        donutSubtotal = findViewById(R.id.donutSubtotal);

        setDonutImage(donut.getType());
        donutName.setText(donut.getFlavor());
        handleQuantitySelection();
        displaySubtotal();
    }

    /**
     * Sets up the quantity spinner.
     * Handles the event that a quantity is selected from the quantity spinner.
     */
    private void handleQuantitySelection() {
        ArrayAdapter<Integer> quantities = new ArrayAdapter<>
                (this,
                        android.R.layout.simple_list_item_1,
                        INT_QUANTITIES);
        donutQuantity.setAdapter(quantities);

        donutQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles the event that a size is selected form the quantity spinner.
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
                donut.setQuantity(INT_QUANTITIES[position]);
                displaySubtotal();
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
     * Calculate and display the price of the current Donut order.
     * Display occurs in the subtotal text field.
     */
    private void displaySubtotal() {
        if (donut != null) {
            donutSubtotal.setText(MainActivity.formatAmount(donut.itemPrice()));
        }
    }

    /**
     * Sets the image in the activity screen based on donut type.
     * @param type the donut type as a DonutType object.
     */
    private void setDonutImage(DonutType type) {
        if (type.equals(DonutType.YEAST)) {
            donutImage.setImageResource(R.drawable.yeast);
        }
        else if (type.equals(DonutType.CAKE)) {
            donutImage.setImageResource(R.drawable.cake);
        }
        else {
            donutImage.setImageResource(R.drawable.holes);
        }
    }

    /**
     * Handles the event that the "ADD DONUT" button is pressed.
     * Adds the donut to the current order.
     * @param view the implicit view object.
     */
    public void onClickAddButton(View view) {
        MainActivity.orderBasket.add(donut);
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.donut_toast);
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
        finish();
    }
}