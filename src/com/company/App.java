package com.company;

import com.company.controller.VendingController;
import com.company.dao.VendingDaoFileImpl;
import com.company.service.VendingServiceLayerImpl;
import com.company.view.VendingConsoleImpl;
import com.company.view.VendingView;

public class App {

    public static void main(String[] args) {
        VendingController controller = new VendingController(new VendingView(new VendingConsoleImpl()),
                new VendingServiceLayerImpl(new VendingDaoFileImpl())); // dependency injection
        controller.run();
    }
}
