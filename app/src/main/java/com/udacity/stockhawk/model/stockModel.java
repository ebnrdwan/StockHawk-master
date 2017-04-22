package com.udacity.stockhawk.model;

/**
 * Created by Abdulrhman on 22/04/2017.
 */

public class stockModel {
    private String symbol;
    private float price;
    private String change ;

    public stockModel(String symbol, float price, String change) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
