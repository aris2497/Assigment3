package com.company.service.Exceptions;

/**
 * Exception is triggered when user enters less money then selected product price
 */
public class InsufficientFundsException extends RuntimeException{

    public void howtoRecover(String inputAmt) {
        System.out.println(inputAmt + " is insufficient...Try again!");
    }
}
