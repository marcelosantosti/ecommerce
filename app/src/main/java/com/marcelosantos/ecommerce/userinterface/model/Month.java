package com.marcelosantos.ecommerce.userinterface.model;

/**
 * Created by Marcelo on 16/02/2016.
 */
public class Month {

    private int number;

    public Month(int number) {

        this.number = number;
    }

    @Override
    public String toString() {

        String month = String.valueOf(number);

        if (number < 10)
            month = "0" + month;

        return month;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
