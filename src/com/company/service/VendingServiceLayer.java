package com.company.service;

import com.company.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author paulina
 */
public interface VendingServiceLayer {

    ArrayList<Product> getAllProducts();//list containing all products, including products where 0 available
    ArrayList<Product> getAvailableProducts(); //list containing only available products, excluding products where 0 available
    boolean isSufficient(String inputAmt, String price);
    void updateInventory(int id); //updating products availability parameter
    void loadProducts(); //reading list of products
    void saveProducts(); //writing list of products


}
