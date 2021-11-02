package com.company.service;

import com.company.dao.VendingDao;
import com.company.dao.VendingDaoFileImpl;
import com.company.dto.Product;
import com.company.service.Exceptions.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class VendingServiceLayerImpl implements VendingServiceLayer {
    VendingDao dao;

    public VendingServiceLayerImpl(VendingDao dao) {
        this.dao = dao;
    }

    /**
     *
     * @return list containing all products, including products where 0 available
     */
    @Override
    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) dao.getAllProducts();

    }

    /**
     * filtering through the array of all products to get only
     * available products to display them to the user
     *
     * @return list containing only available products, excluding products where 0 available
     *
     */
    @Override
    public ArrayList<Product> getAvailableProducts() {

        List<Product> availableProducts = dao.getAllProducts().stream()
                .filter(product -> product.getNumber()>0)
                .collect(Collectors.toList());
        return (ArrayList<Product>) availableProducts;
    }


    /**
     *
     * @param inputAmt - money entered
     * @param price - price of selected item
     * @return if enough money entered
     * @throws RuntimeException - not enough money
     */
    @Override
    public boolean isSufficient(String inputAmt, String price) throws RuntimeException {
        boolean isSufficient = true;
        try {
            if (Double.parseDouble(price) > Double.parseDouble(inputAmt)) {
                isSufficient = false;
                throw new InsufficientFundsException();

            }
        } catch (InsufficientFundsException e) //specific exception first
        {
            Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.INFO,
                    "Insufficient Funds exception. Please enter valid amount.");
            e.howtoRecover(inputAmt);

        }
        return isSufficient;
    }


    @Override
    public void updateInventory(int id) {
        dao.removeProduct(id);
    }

    @Override
    public void loadProducts() {
        dao.readProducts();
    }

    @Override
    public void saveProducts() { dao.writeFile(); }




    /*
    int pounds = inputAmt / 100;
    int remainder1 = inputAmt % 100;
    int fifties = remainder / 50;
    int remainder2 = remainder1 % 50;
    int twenties = remainder2 / 20;

     */


}
