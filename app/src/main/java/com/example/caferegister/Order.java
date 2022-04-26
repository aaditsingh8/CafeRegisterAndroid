package com.example.caferegister;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Models an RU Café order.
 * Contains a unique ID and a list of menu items.
 * @author Aadit Singh, Shivan Suratia.
 */
public class Order implements Customizable {

    private static final double SALES_TAX = .06625;
    private static final double INIT_AMOUNT = 0.0;

    private int orderNum;
    private List<MenuItem> items;
    private double total;
    private double taxes;
    private double totalWithTaxes;

    /**
     * Creates an Order object using an input order number.
     * The order number needs to be unique and will not be self-generated.
     * Items can be added to the order after the object has been instantiated.
     * @param orderNum the unique order number.
     */
    public Order(int orderNum) {
        this.orderNum = orderNum;
        this.items = new ArrayList<>();
        this.total = INIT_AMOUNT;
        this.totalWithTaxes = INIT_AMOUNT;
    }

    /**
     * Adds a menu item to the order.
     * @param obj the menu item as a subclass.
     * @return true if the input was successfully added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            for (MenuItem menuItem : items) {
                if (menuItem.equals(item)) {
                    menuItem.increaseQuantity(item.getQuantity());
                    recalculatePrices();
                    return true;
                }
            }
            items.add(item);
            recalculatePrices();
            return true;
        }
        return false;
    }

    /**
     * Removes a menu item from the order.
     * @param obj the menu item as a subclass.
     * @return true if the input was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            boolean flag = items.remove(item);
            if (flag) {
                recalculatePrices();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the order number the order.
     * @return the order number the order as an integer.
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * Returns the list of MenuItem objects in the current un-placed order.
     * @return the list of MenuItem objects.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Sets the required amounts of the menu items in the given order.
     */
    private void recalculatePrices() {
        double amount = 0.0;
        for (MenuItem menuItem : items) {
            amount += menuItem.itemPrice();
        }
        total = amount;
        taxes = total * SALES_TAX;
        totalWithTaxes = total + taxes;
    }

    /**
     * Get the total amount without taxes for the Order Basked Activity.
     * @return the total as a double
     */
    public double getTotal() {
        return total;
    }

    /**
     * Get the taxes on the total amount for the Order Basked Activity.
     * @return the taxes as a double
     */
    public double getTaxes() {
        return taxes;
    }

    /**
     * Get the total amount with taxes for the Order Basked Activity.
     * @return the total with taxes as a double
     */
    public double getTotalWithTaxes() {
        return totalWithTaxes;
    }

    /**
     * Checks if two orders are equal by comparing their order numbers.
     * @param obj the Object to be compared against.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            return orderNum == order.orderNum;
        }
        return false;
    }

    /**
     * Creates a string representation of the order.
     * @return the string representation of the order, which is the order number.
     */
    @NonNull
    @Override
    public String toString() {
        return String.valueOf(orderNum);
    }
}
