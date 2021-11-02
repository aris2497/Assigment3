package com.company.service.Exceptions;

/**
 * Exception is triggered when user enter index that has no corresponding product
 *
 */
public class NoItemInventoryException extends IllegalArgumentException{

    public void howtoRecover(int noItemIndex) {
        System.out.println("There is not item with index " + noItemIndex + " ..Try again!");
    }
}
