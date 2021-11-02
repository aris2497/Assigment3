package com.company.dao;

import com.company.dto.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendingDaoFileImpl implements VendingDao {

    private static final File fileName = new File("Products.csv"); //file with DVD
    private final ArrayList<Product> allProducts = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return allProducts;
    }

    @Override
    public Product getProduct(int id) {
        return allProducts.get(id);
    }

    @Override
    public void removeProduct(int id) {
        Product product = allProducts.get(id);
        product.setNumber(product.getNumber() - 1);

    }

    /**
     * File Reading Logic
     */

    public void readProducts() {

        try {
            FileReader file = new FileReader(fileName);//reading the file
            BufferedReader lines = new BufferedReader(file);
            String newLine = lines.readLine();
            while (newLine != null) { //reading values until end of file is reached
                String[] values = newLine.split(","); // Splits string into an array and store it in

                Product p = new Product(Integer.parseInt(values[0]), values[1], values[2], //creating new DVD object from the file line
                        Integer.parseInt(values[3]));
                allProducts.add(p);
                newLine = lines.readLine();

            }
            file.close(); //Closing the file

        } catch (IOException ex) {
            Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.SEVERE,
                    "File Reading Logic - IOException", ex);
        }
    }

    /**
     * File Writing Logic
     */
    public void writeFile() {

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            //getting values from the array and writing into file with comas to separate values
            for (int i = 0; i < getAllProducts().size(); i++) {
                Product p = getAllProducts().get(i);
                bw.write(p.getId() + "," + p.getName() + "," + p.getPrice()
                        + "," + p.getNumber() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.SEVERE, "File Writing Logic - IOException", e);
        }

    }
}
