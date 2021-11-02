package com.company.controller;

import com.company.service.Change;
import com.company.service.VendingServiceLayer;
import com.company.view.VendingView;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Controller class of the program
 * has a member of view
 * has a member of service
 *
 * @author paulina
 */

public class VendingController {
    VendingView view;
    VendingServiceLayer service;

    /**
     *
     * @param view
     * @param service
     */
    public VendingController(VendingView view, VendingServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    /**
     * method invoked in App.java
     */
     public void run(){
        service.loadProducts(); //reading from the file
        while(!view.printAvailableItems(service.getAvailableProducts()).equals("x")){
            view.printStringToContinue(); //display "Continue..."
            boolean isSufficient = false; //for checking if the entered value is less than product cost
            String selectedPrice = ""; //for the price of the selected product
            String inputAmt = ""; //amount of money entered by the user
            int selected = 0; //index of the selected item

            while(!isSufficient){
                inputAmt = view.readInputAmt(); //reading the value of entered money
                selected = view.getSelectedProduct() - 1; //array indexing starting from 0
                selectedPrice = service.getAllProducts().get(selected).getPrice(); //getting the price of the selected item
                isSufficient = service.isSufficient(inputAmt, selectedPrice); //checking if the amount of money is less then selected product price
            }
            BigDecimal change = Change.calculateChange(inputAmt, selectedPrice); //calculates how much change is due
            view.printChange(change.toString()); //displaying amount of users change in total
            ArrayList<Integer> coins = Change.calculateReminder(change); //getting number of coins

            view.toStringCoins(coins.get(0).toString(), //displaying change in coins
                    coins.get(1).toString(), coins.get(2).toString(),
                    coins.get(3).toString());
            service.updateInventory(selected); //updating the number of available product
            view.printStringToEnd(); //display "Enjoy!"
        }
        service.saveProducts(); //writing to the file
     }
}
