package com.company.view;

import com.company.dao.VendingDaoFileImpl;
import com.company.service.Exceptions.NoItemInventoryException;
import com.company.service.Exceptions.ZeroFundsException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendingConsoleImpl implements UserIO{

    final private Scanner console = new Scanner(System.in);

    /**
     * A very simple method that takes in a message to display on the console
     * and then waits for a integer answer from the user to return.
     *
     * @param msg - String of information to display to the user.
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and then waits for an answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    /**
     * A simple method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter an integer
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    @Override
    public String readMoneyInput(String msgPrompt) {
        boolean invalidInput = true;
        Double num = 0.0;

        while (invalidInput) {
            try {
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Double.parseDouble(stringValue); // if it's 'bob' it'll break
                if (num==0){
                    invalidInput = true;
                    throw new ZeroFundsException();
                }
                else {
                    invalidInput = false;
                }
            } catch (NumberFormatException ex){
                invalidInput = true;
                Logger.getLogger(VendingConsoleImpl.class.getName()).log(Level.INFO,
                        "Money format exception. Please enter valid amount.");

            }
            catch (ZeroFundsException ex){
                Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.INFO,
                        "Zero Money exception.");
                ex.howToRecover();
            }
        }
        String output = Double.toString(num);
        return output;
    }

    /**
     * A slightly more complex method that takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter an integer
     * within the specified min/max range to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min       - minimum acceptable value for return
     * @param max       - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the min/max range
     */
    @Override
    public int readItemIndex(String msgPrompt, int min, int max) {
        boolean invalidInput = true;
        int num = 0;

        while (invalidInput) {
            try {
                String stringValue = this.readString(msgPrompt);
                num = Integer.parseInt(stringValue);  // Get the input line, and try and parse
                if (num < min || num > max) {
                    invalidInput = true;
                    throw new NoItemInventoryException();
                } else {
                    invalidInput = false;
                }
            } catch (NoItemInventoryException err) {
                Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.SEVERE,
                        "NoItemInventoryException");
                err.howtoRecover(num);
            } catch (NumberFormatException e){
                Logger.getLogger(VendingDaoFileImpl.class.getName()).log(Level.SEVERE,
                        "NumberFormatException");
            }
        }
        return num;
    }

}
