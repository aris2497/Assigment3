package com.company.view;

import com.company.dto.Product;

import java.util.ArrayList;

public class VendingView {
    UserIO io;
    public VendingView(UserIO io) {
        this.io = io;
    }

    public void printChange(String stringToPrint){
        io.print("Change: " + stringToPrint + "$");
    }

    public String printAvailableItems(ArrayList<Product> products){
        io.print("\n---Available Items---");

        for (Product product : products) {
            String productInfo = toString(product);
            io.print(productInfo);
        }
        return io.readString("Press [x] to exit the program or press enter to continue.");
    }

    public void printStringToContinue(){
        System.out.println("Continue...");
    }
    public void printStringToEnd(){
        System.out.println("Enjoy!");
    }

    public int getSelectedProduct() {
        return io.readItemIndex("Please select product number",1,5);
    }

    public String toString(Product product) {
        return String.format("#%s %s: %s$, %s available",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getNumber());
    }

    public void toStringCoins(String quarters, String dimes, String nickles, String pennies){
        System.out.println(String.format("Quarters: " + quarters +
                "\nDimes: " + dimes +
                "\nNickles: " + nickles +
                "\nPennies: " + pennies));
    }

    public String readInputAmt() {
        return io.readMoneyInput("Please enter money before selecting");
    }
}
