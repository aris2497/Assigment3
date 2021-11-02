package com.company.view;

/**
 * This interface defines the methods that must be implemented
 * by any class that wants to directly interact with the user
 * interface technology.
 */
public interface UserIO {
    void print(String msg);

    int readItemIndex(String prompt, int min, int max);



    String readString(String prompt);

    String readMoneyInput(String prompt);
}

