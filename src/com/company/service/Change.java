package com.company.service;

import com.company.dto.Coins;
import com.company.service.Exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * class that takes the amount of change due to the user (in pennies)
 * and then calculates the number of quarters, dimes, nickels,
 * and pennies due back to the user.
 */

public class Change {

    public static BigDecimal calculateChange(String inputAmt, String selectedPrice) {
        BigDecimal change = toBigDecimal(inputAmt).subtract(toBigDecimal(selectedPrice));
        /*try {
            if (Integer.parseInt(inputAmt) < Integer.parseInt(selectedPrice)) {
                throw new InsufficientFundsException();
            }

        } catch (InsufficientFundsException e) {
            e.howToRecover(inputAmt);
            System.out.println(e.getCause());
        }*/

        return change;
    }

    public static BigDecimal toBigDecimal(String numberStr) {
        BigDecimal number = new BigDecimal(numberStr);
        BigDecimal numberMoney = number.setScale(2, RoundingMode.HALF_UP);
        return numberMoney;
    }

    public static ArrayList<Integer> calculateReminder(BigDecimal reminder) {
        ArrayList<Integer> coins = new ArrayList<>();
        BigDecimal quarters = BigDecimal.valueOf(0);
        BigDecimal dimes = BigDecimal.valueOf(0);
        BigDecimal nickles = BigDecimal.valueOf(0);

        BigDecimal reminderInPennies = reminder.multiply(BigDecimal.valueOf(100));

        if (reminderInPennies.compareTo(BigDecimal.valueOf(Coins.QUARTER.getValue())) >= 0) {
            quarters = calculateCoins(reminderInPennies, Coins.QUARTER);
            reminderInPennies = reminderInPennies.subtract(quarters.multiply(toBigDecimal(Coins.QUARTER.getValue().toString())));
        }

        if (reminderInPennies.compareTo(BigDecimal.valueOf(Coins.DIME.getValue())) >= 0) {
            dimes = calculateCoins(reminderInPennies, Coins.DIME);
            reminderInPennies = reminderInPennies.subtract(dimes.multiply(toBigDecimal(Coins.DIME.getValue().toString())));
        }

        if (reminderInPennies.compareTo(BigDecimal.valueOf(Coins.NICKLE.getValue())) >= 0) {
            System.out.println("NICKLES");
            nickles = calculateCoins(reminderInPennies, Coins.NICKLE);
            reminderInPennies = reminderInPennies.subtract(nickles.multiply(toBigDecimal(Coins.NICKLE.getValue().toString())));
        }

        BigDecimal pennies = reminderInPennies.setScale(0, RoundingMode.DOWN);

        coins.add(quarters.intValue());
        coins.add(dimes.intValue());
        coins.add(nickles.intValue());
        coins.add(pennies.intValue());

        return coins;
    }

    public static BigDecimal calculateCoins(BigDecimal reminder, Coins coins) {
        return reminder.divide(toBigDecimal(coins.getValue().toString()), 0, RoundingMode.DOWN);
    }
}
