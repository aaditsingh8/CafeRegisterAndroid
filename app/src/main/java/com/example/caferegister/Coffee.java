package com.example.caferegister;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a Coffee as a menu item in the RU Café order.
 * Contains a size and add-ins. Price is determined by size and number of add-ins.
 * @author Aadit Singh, Shivan Suratia.
 */
public class Coffee extends MenuItem implements Customizable {
    private static final double SIZE_BASE_PRICE = 1.69;
    private static final double SIZE_INCR_PRICE = 0.40;

    private static final double ADD_IN_PRICE = 0.30;
    private static final int INIT_ORDER_NUM = 1;

    private CoffeeSize size;
    private List<CoffeeAddition> additions;

    /**
     * Creates a Coffee object with the input size.
     * @param size the size as a string.
     */
    public Coffee(CoffeeSize size) {
        this.size = size;
        this.additions = new ArrayList<>();
        this.quantity = INIT_ORDER_NUM;
    }

    /**
     * Creates a clone of the input Coffee object.
     * @param coffee the Coffee object to be cloned.
     */
    public Coffee(Coffee coffee) {
        this.size = coffee.getSize();
        this.additions = coffee.cloneAdditions();
        this.quantity = coffee.getQuantity();
    }

    /**
     * Returns the size of the Coffee object.
     * @return the size of the Coffee object as a CoffeeSize object.
     */
    public CoffeeSize getSize() {
        return size;
    }

    /**
     * Changes the size of the Coffee after an object has been created.
     * @param size the new size as a CoffeeSize object.
     */
    public void setSize(CoffeeSize size) {
        this.size = size;
    }

    /**
     * Returns a clone of the list of additions of the Coffee object.
     * @return the clone of additions as a List of CoffeeAddition objects.
     */
    public List<CoffeeAddition> cloneAdditions() {
        return new ArrayList<>(additions);
    }

    /**
     * Adds an add-in to the coffee.
     * Add-ins are arranged chronologically.
     * @param obj the add-in name as a string.
     * @return true if the input is a valid string, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof CoffeeAddition) {
            CoffeeAddition addition = (CoffeeAddition) obj;
            for(int i = 0; i < additions.size(); i++) {
                if(additions.get(i).compareTo(addition) > 0) {
                    additions.add(i, addition);
                    return true;
                }
            }
            return additions.add(addition);
        }
        return false;
    };

    /**
     * Removes an add-in from the coffee.
     * @param obj the add-in name as a string.
     * @return true if the input is a valid string, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof CoffeeAddition) {
            CoffeeAddition addition = (CoffeeAddition) obj;
            return additions.remove(addition);
        }
        return false;
    };

    /**
     * Returns the price of the Coffee menu item as a double.
     * @return the price of the Coffee as a double.
     */
    @Override
    public double itemPrice() {
        double sizePrice = SIZE_BASE_PRICE + size.getIndex() * SIZE_INCR_PRICE;
        return (sizePrice + (additions.size() * ADD_IN_PRICE)) * quantity;
    }

    /**
     * Returns the string representation of the Coffee as a menu item.
     * @return the string representation of the Coffee.
     */
    @NonNull
    @Override
    public String toString() {
        String addons = "";
        if(!additions.isEmpty()) {
            addons = " " + additions;
        }
        return String.format("%s %s Coffee%s", super.toString(), size, addons);
    }

    /**
     * Compares if the parameter object is equal to Coffee object.
     * Equality is considered on the basis of size and add-ins list.
     * @param obj the object to be compared
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coffee) {
            Coffee coffee = (Coffee) obj;
            return size.equals(coffee.size) && additions.equals(coffee.additions);
        }
        return false;
    }
}
