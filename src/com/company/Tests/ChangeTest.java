package com.company.Tests;

import com.company.service.Change;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTest {

    @org.junit.jupiter.api.Test
    void calculateReminderTest1() {
        BigDecimal reminder = BigDecimal.valueOf(0.99);
        ArrayList<Integer> changeList = Change.calculateReminder(reminder);
        int[] coins = new int[] {3, 2, 0, 4};
        int[] arr = changeList.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(coins, arr);
    }
    @org.junit.jupiter.api.Test
    void calculateReminderTest2() {
        BigDecimal reminder = BigDecimal.valueOf(5.50);
        ArrayList<Integer> changeList = Change.calculateReminder(reminder);
        int[] coins = new int[] {22,0, 0, 0};
        int[] arr = changeList.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(coins, arr);
    }
    @org.junit.jupiter.api.Test
    void calculateReminderTest3() {
        BigDecimal reminder = BigDecimal.valueOf(0.24);
        ArrayList<Integer> changeList = Change.calculateReminder(reminder);
        int[] coins = new int[] {0, 2, 0, 4};
        int[] arr = changeList.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(coins, arr);
    }
    @org.junit.jupiter.api.Test
    void calculateReminderTest4() {
        BigDecimal reminder = BigDecimal.valueOf(0.06);
        ArrayList<Integer> changeList = Change.calculateReminder(reminder);
        int[] coins = new int[] {0, 0, 1, 1};
        int[] arr = changeList.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(coins, arr);
    }



}