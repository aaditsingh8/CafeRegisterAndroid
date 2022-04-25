package com.example.caferegister;

/**
 * Enum class that enlists the kinds of donuts offered by the Café.
 * Donut kinds are a combination of type and flavor.
 * @author Aadit Singh, Shivan Suratia
 */
public enum DonutKind {
    GLAZED (DonutType.YEAST, "Glazed"),
    CHOCOLATE_FROSTED (DonutType.YEAST, "Chocolate Frosted"),
    STRAWBERRY_FROSTED (DonutType.YEAST, "Strawberry Frosted"),
    CRULLER (DonutType.YEAST, "Cruller"),
    CINNAMON_SUGAR (DonutType.CAKE, "Cinnamon Sugar"),
    OLD_FASHIONED (DonutType.CAKE, "Old Fashioned"),
    BLUEBERRY (DonutType.CAKE, "Blueberry"),
    JELLY (DonutType.CAKE, "Jelly"),
    CREAM_FILLED (DonutType.HOLE, "Cream Filled"),
    BOSTON_KREME (DonutType.HOLE, "Boston Kreme"),
    SOUR_CREAM (DonutType.HOLE, "Sour Cream"),
    APPLE_CRUMB(DonutType.HOLE, "Apple-Krumb");

    private final DonutType type;
    private final String flavor;

    /**
     * The implicit Enum constructor that assigns Donut type and flavor name.
     * @param type the type as a DonutType object.
     * @param flavorName the flavor name as a string.
     */
    private DonutKind(DonutType type, String flavorName) {
        this.type = type;
        this.flavor = flavorName;
    }

    /**
     * Return the required DonutKind object based on the flavor.
     * @param flavor the flavor as a string.
     * @return the required DonutKind object.
     */
    public static DonutKind getDonutKind(String flavor) {
        DonutKind donutKind = null;
        for(DonutKind kind : values()) {
            if (kind.flavor.equals(flavor)) {
                donutKind = kind;
            }
        }
        return donutKind;
    }

    /**
     * Returns the donut type.
     * @return the donut type as a DonutType object.
     */
    public DonutType getType() {
        return type;
    }

    /**
     * Returns the name of the Donut flavor.
     * @return the Donut flavor name as a string.
     */
    @Override
    public String toString() {
        return flavor;
    }
}
