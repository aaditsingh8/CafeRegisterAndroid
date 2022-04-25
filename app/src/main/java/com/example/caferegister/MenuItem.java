package com.example.caferegister;

import java.io.Serializable;

/**
 * Models a menu item in the orders placed at RU Café.
 * Every menu item has a price as a double value and implements a getPrice() method.
 * @author Aadit Singh, Shivan Suratia
 */
public class MenuItem implements Serializable {

    protected int quantity;

    /**
     * Returns the price of the menu item as a double.
     * @return the price of the menu item as a double.
     */
    public double itemPrice() {
        return 0.0;
    }

    /**
     * Returns the quantity of the menu item.
     * @return the quantity of the menu item as an integer.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Directly changes the quantity of the menu item.
     * @param quantity the new quantity as an integer.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the string representation of the menu item.
     * @return the string representation of the menu item.
     */
    @Override
    public String toString() {
        return quantity + " ×";
    }

    /**
     * Increases the quantity of the same menu item by the input amount.
     * @param increment the increment in quantity as an integer.
     */
    public void increaseQuantity(int increment) {
        quantity += increment;
    }
}
