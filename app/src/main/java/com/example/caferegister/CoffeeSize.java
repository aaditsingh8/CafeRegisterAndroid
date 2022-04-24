package com.example.caferegister;

/**
 * Models the size for a Coffee order at RU Café.
 * Sizes include Short, Tall, Grande, and Venti. Each size has its own price.
 * @author Aadit Singh, Shivan Suratia.
 */
public enum CoffeeSize {
    SHORT(0, "Short"),
    TALL(1, "Tall"),
    GRANDE(2, "Grande"),
    VENTI(3, "Venti");

    private final int index;
    private final String name;

    /**
     * The implicit Enum constructor that assigns Coffee price based on size.
     * @param index the index as an integer.
     * @param name the size name as a string.
     */
    CoffeeSize(int index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * Returns the index of the Coffee based on the size.
     * The index represents the magnitude of the arithmetic increase in the price
     * of a Coffee based on the size.
     * @return the index as an integer.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the name of the Coffee size.
     * @return the Coffee size name as a string.
     */
    @Override
    public String toString() {
        return name;
    }
}
