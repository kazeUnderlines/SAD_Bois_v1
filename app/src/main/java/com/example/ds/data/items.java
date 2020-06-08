package com.example.ds.data;

public class items {
    String productName, sweetness, ice;
    int amount, price, singlePrice;
    public items (String pN, String S, String I, int A, int sP){
        productName = pN;
        sweetness = S;
        ice = I;
        amount = A;
        singlePrice = sP;
        price = sP*A;

    }

    public String getProductName() {
        return productName;
    }

    public String getSweetness() {
        return sweetness;
    }

    public String getIce() {
        return ice;
    }

    public int getAmount() {
        return amount;
    }

    public int getSinglePrice() {
        return singlePrice;
    }

    public int getPrice() {
        return price;
    }
}
