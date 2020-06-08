package com.example.ds.data;

import android.provider.BaseColumns;


public class rdbContract {

    public static final class orderEntry implements BaseColumns{
        public static final String TABLE_NAME = "orders";
        public static final String ORDER_NUMBER = "orderNumber";
        public static final String DATE = "date";
        public static final String PAYMENT= "payment";
        public static final String TOTAL_PRICE= "totalPrice";
    }

    public static final class itemEntry implements BaseColumns{
        public static final String TABLE_NAME = "items";
        public static final String ORDER_NUMBER = "orderNumber";
        public static final String SERIAL_NUMBER= "serialNumber";
        //public static final String PRODUCT_NUMBER= "productNumber";
        public static final String PRODUCT_NAME= "productName";
        //public static final String SIZE= "size";
        public static final String SWEETNESS= "sweetness";
        public static final String ICE= "ice";
        public static final String AMOUNT= "amount";
        public static final String SINGLE_PRICE= "singlePrice";
        public static final String PRICE= "price";
    }
}
