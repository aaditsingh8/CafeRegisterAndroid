package com.example.caferegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The Coffee Activity that lets the user order coffee.
 * @author Aadit Singh, Shivan Suratia
 */
public class CoffeeActivity extends AppCompatActivity {

    private static final Integer[] INT_QUANTITIES = {1, 2, 3, 4, 5};

    private Coffee coffee;

    private CheckBox addition1, addition2, addition3, addition4, addition5;
    private Spinner quantity, size;
    private EditText subtotal;
    private Button buttonAdd;

    /**
     * The onCreate function for the Activity.
     * @param savedInstanceState the implicit Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        // Create coffee instance for customization
        coffee = new Coffee(CoffeeSize.SHORT);

        // Set resources
        addition1 = findViewById(R.id.addition1);
        addition2 = findViewById(R.id.addition2);
        addition3 = findViewById(R.id.addition3);
        addition4 = findViewById(R.id.addition4);
        addition5 = findViewById(R.id.addition5);
        quantity = findViewById(R.id.quantity);
        size = findViewById(R.id.size);
        subtotal = findViewById(R.id.subtotal);
        buttonAdd = findViewById(R.id.buttonAdd);

        // Display options
        handleQuantitySelection();
        handleSizeSelection();
        displaySubtotal();
    }

    // COFFEE ORDER SPECIFICATION METHODS

    /**
     * Sets up the size spinner.
     * Handles the event that a size is selected from the size spinner.
     */
    private void handleSizeSelection() {
        ArrayAdapter<CoffeeSize> sizes = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                CoffeeSize.values());
        size.setAdapter(sizes);

        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles the event that a quantity is selected form the quantity spinner.
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
                coffee.setSize(sizes.getItem(position));
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
     * Sets up the quantity spinner.
     * Handles the event that a quantity is selected from the quantity spinner.
     */
    private void handleQuantitySelection() {
        ArrayAdapter<Integer> quantities = new ArrayAdapter<>
                (this,
                android.R.layout.simple_list_item_1,
                INT_QUANTITIES);
        quantity.setAdapter(quantities);

        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                coffee.setQuantity(INT_QUANTITIES[position]);
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
     * Calculate and display the price of the current Coffee order.
     * Display occurs in the subtotal text field.
     */
    private void displaySubtotal() {
        if (coffee != null) {
            subtotal.setText(MainActivity.formatAmount(coffee.itemPrice()));
        }
    }
    
    // COFFEE ADDITIONS METHODS

    /**
     * Depending on whether any addition checkbox is selected or not, add or
     * remove it from the Coffee object respectively.
     * @param checkBox the CheckBoc object to be inspected.
     */
    private void toggleAddition(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            coffee.add(CoffeeAddition.getAddition(checkBox.getText().toString()));
        }
        else {
            coffee.remove(CoffeeAddition.getAddition(checkBox.getText().toString()));
        }
        displaySubtotal();
    }

    /**
     * Depending on whether the Cream checkbox is selected or not, add or
     * remove the Cream addition from the Coffee object respectively.
     * @param view the implicit view object.
     */

    public void onAddingCream(View view) {
        toggleAddition(addition1);
    }

    /**
     * Depending on whether the Syrup checkbox is selected or not, add or
     * remove the Syrup addition from the Coffee object respectively.
     * @param view the implicit view object.
     */

    public void onAddingSyrup(View view) {
        toggleAddition(addition2);
    }

    /**
     * Depending on whether the Milk checkbox is selected or not, add or
     * remove the Milk addition from the Coffee object respectively.
     * @param view the implicit view object.
     */

    public void onAddingMilk(View view) {
        toggleAddition(addition3);
    }

    /**
     * Depending on whether the Caramel checkbox is selected or not, add or
     * remove the Caramel addition from the Coffee object respectively.
     * @param view the implicit view object.
     */

    public void onAddingCaramel(View view) {
        toggleAddition(addition4);
    }

    /**
     * Depending on whether the Whipped Cream checkbox is selected or not, add or
     * remove the Whipped Cream addition from the Coffee object respectively.
     * @param view the implicit view object.
     */
    
    public void onAddingWhippedCream(View view) {
        toggleAddition(addition5);
    }

    // ADDING COFFEE TO ORDER

    /**
     * Handles the event that the "ADD COFFEE" button is pressed.
     * Adds the coffee to the current order.
     * @param view the implicit view object.
     */
    public void onClickAddButton(View view) {
        MainActivity.orderBasket.add(coffee);
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.coffee_toast);
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }
}