package com.company.service.Exceptions;

/**
 * Exception is triggered when user enters "0" as entered money value
 */
public class ZeroFundsException extends Throwable{
    public void howToRecover() {
        System.out.println("0 is invalid. You have enter money to start...Try again!");
    }
}
