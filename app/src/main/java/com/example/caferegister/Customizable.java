package com.example.caferegister;

/**
 * Interface that models any customizable entity in the café system.
 * Implements add() and remove() methods for list-keeping.
 * @author Aadit Singh, Shivan Suratia.
 */
public interface Customizable {
    /**
     * Adding an item to the maintained list.
     * @param obj the object to be added.
     * @return true if the object was added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Removing an item from the maintained list.
     * @param obj the object to be removed.
     * @return true if the object was removed, false otherwise.
     */
    boolean remove(Object obj);
}
