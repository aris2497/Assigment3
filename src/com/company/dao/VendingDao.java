package com.company.dao;

import com.company.dto.Product;

import java.util.List;



public interface VendingDao {
    List<Product> getAllProducts(); //list containing all products, including products where 0 available
    Product getProduct(int id);
    void removeProduct(int id);
    void readProducts();
    void writeFile();
}
