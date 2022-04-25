package com.example.caferegister;

/**
 * Models the add-ins for a Coffee order at RU Café.
 * Add-ins include Cream, Syrup, Milk, Caramel, and Whipped Cream.
 * Each add-in is priced at $0.30.
 * @author Aadit Singh, Shivan Suratia.
 */
public enum CoffeeAddition {
    CARAMEL("Caramel"),
    CREAM("Cream"),
    MILK("Milk"),
    SYRUP("Syrup"),
    WHIPPED_CREAM("Whipped Cream");

    private final String name;

    /**
     * The implicit Enum constructor for Coffee add-ins.
     * @param name the add-in name as a string.
     */
    CoffeeAddition(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Coffee add-in.
     * @return the Coffee add-in name as a string.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Parses input Coffee add-in as a string to return the correct CoffeeAddition enum object.
     * @param str the input Coffee add-in as a string.
     * @return the correct add-in as a CoffeeAddition enum object.
     */
    public static CoffeeAddition getAddition(String str) {
        CoffeeAddition addition = null;
        switch (str) {
            case "Cream":
                addition = CREAM;
                break;
            case "Syrup":
                addition = SYRUP;
                break;
            case "Milk":
                addition = MILK;
                break;
            case "Caramel":
                addition = CARAMEL;
                break;
            case "Whipped Cream":
                addition = WHIPPED_CREAM;
                break;
            default:
                break;
        }
        return addition;
    }
}
