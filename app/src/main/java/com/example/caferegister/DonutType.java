package com.example.caferegister;

/**
 * Enum class that enlists the types of donuts offered by RU Café.
 * Types consist of: Yeast Donut, Cake Donut, Donut Hole.
 * @author Aadit Singh, Shivan Suratia
 */
public enum DonutType {
    YEAST (1.59, "Yeast Donut"),
    CAKE (1.79, "Cake Donut"),
    HOLE (0.39, "Donut Hole");

    private final double price;
    private final String name;

    /**
     * The implicit Enum constructor that assigns Donut price based on type.
     * @param price the price as a double.
     * @param name the type name as a string.
     */
    DonutType(double price, String name) {
        this.price = price;
        this.name = name;
    }

    /**
     * Returns the price of the Donut based on the type.
     * @return the price as a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the name of the Donut type.
     * @return the Donut type name as a string.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Parses input donut type as a string to return the correct DonutType enum object.
     * @param str the input donut type as a string.
     * @return the correct type as a DonutType enum object.
     */
    public static DonutType getType(String str) {
        return switch (str) {
            case "Yeast Donut" -> YEAST;
            case "Cake Donut" -> CAKE;
            case "Donut Hole" -> HOLE;
            default -> null;
        };
    }
}