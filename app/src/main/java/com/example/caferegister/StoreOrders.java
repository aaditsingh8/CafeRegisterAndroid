package com.example.caferegister;

import java.util.ArrayList;
import java.util.List;

/**
 * Models an RU Café orders register.
 * Contains a list of placed orders.
 * @author Aadit Singh, Shivan Suratia.
 */
public class StoreOrders implements Customizable {

    public static final int FIRST_ORDER_INDEX = 0;
    private List<Order> orders;

    /**
     * Creates a StoreOrders object.
     * Orders can be added to the register after the object has been instantiated.
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Returns the list of places store orders.
     * @return the list of placed store orders as Order objects.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Return the first order that is to be displayed in the Store Orders view.
     * @return the first Order object.
     */
    public Order getFirstOrder() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(FIRST_ORDER_INDEX);
    }

    /**
     * Adds an order to the register.
     * @param obj the order as an Order object.
     * @return true if the input was successfully added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            Order order = (Order) obj;
            return orders.add(order);
        }
        return false;
    }

    /**
     * Removes an order item from the register.
     * @param obj the order as an Order object.
     * @return true if the input was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order) {
            Order order = (Order) obj;
            return orders.remove(order);
        }
        return false;
    }
}

