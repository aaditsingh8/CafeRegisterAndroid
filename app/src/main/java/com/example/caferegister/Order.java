package com.example.caferegister;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Models an RU Café order.
 * Contains a unique ID and a list of menu items.
 * @author Aadit Singh, Shivan Suratia.
 */
public class Order implements Customizable, Serializable {

    public static final double DEFAULT_TOTAL_WITH_TAXES = 0.0;
    private int orderNum;
    private List<MenuItem> items;
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
        this.totalWithTaxes = DEFAULT_TOTAL_WITH_TAXES;
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
                    return true;
                }
            }
            items.add(item);
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
            return items.remove(item);
        }
        return false;
    }

    /**
     * Returns only the donuts in the items list of the current order.
     * @return the list of Donut objects in the current order.
     */
    public List<Donut> getDonuts() {
        List<Donut> donuts = new ArrayList<>();
        for (MenuItem item: items) {
            if (item instanceof Donut) {
                donuts.add((Donut) item);
            }
        }
        return donuts;
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
     * Return the total amount of the menu items in the given order.
     * @return the total amount as a double.
     */
    public double getTotalAmount() {
        double amount = 0.0;
        for (MenuItem menuItem : items) {
            amount += menuItem.itemPrice();
        }
        return amount;
    }

    /**
     * Get the total amount with taxes when the Order Basked View.
     */
    public double getTotalWithTaxes() {
        return totalWithTaxes;
    }

    /**
     * Set the total amount with taxes when the Order Basked View is opened.
     * @param totalWithTaxes the total amount with taxes as a double.
     */
    public void setTotalWithTaxes(double totalWithTaxes) {
        this.totalWithTaxes = totalWithTaxes;
    }

    /**
     * Creates a string representation of the order.
     * @return the string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order #").append(orderNum).append(":\n");
        for(MenuItem item : items) {
            builder.append("\t").append(item).append("\n");
        }
        return builder.toString();
    }
}
