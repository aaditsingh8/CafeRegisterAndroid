package com.example.caferegister;

import java.io.Serializable;

/**
 * Models a Donut as a menu item in the RU Café order.
 * Contains a type and a flavor. Price is determined by type and quantity.
 * @author Aadit Singh, Shivan Suratia.
 */
public class Donut extends MenuItem implements Serializable {
    private DonutType type;
    private String flavor;

    /**
     * Creates a Donut object with the input type, flavor, and quantity.
     * @param type the donut type as a string.
     * @param flavor the donut flavor as a string.
     * @param quantity the number of such donuts as an integer.
     */
    public Donut(DonutType type, String flavor, int quantity) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Creates a clone of the input Donut object.
     * @param donut the Donut object to be cloned.
     */
    public Donut(Donut donut) {
        this.type = donut.getType();
        this.flavor = donut.getFlavor();
        this.quantity = donut.getQuantity();
    }

    /**
     * Returns the type of the Donut object.
     * @return the type of the Donut object as a DonutType object.
     */
    public DonutType getType() {
        return type;
    }

    /**
     * Sets the type of the Donut object.
     */
    public void setType(DonutType type) {
        this.type = type;
    }

    /**
     * Returns the flavor of the Donut object.
     * @return the flavor of the Donut object as a string.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of the Donut object.
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * Returns the price of the Donut menu item as a double.
     * @return the price of the Donut as a double.
     */
    @Override
    public double itemPrice() {
        return type.getPrice() * quantity;
    }

    /**
     * Returns the string representation of the Donut as a menu item.
     * @return the string representation of the Donut.
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", super.toString(), flavor, type);
    }

    /**
     * Compares if the parameter object is equal to Donut object.
     * Equality is considered on the basis of type and flavor.
     * @param obj the object to be compared
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Donut) {
            Donut donut = (Donut) obj;
            return type.equals(donut.type) && flavor.equals(donut.flavor);
        }
        return false;
    }
}
