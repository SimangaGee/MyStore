package com.example.hi.my;

public class item {

    private String ProductName;
    private String Productprice;
    private String Productbuy;
    private String Productdate;

    public item(String Name, String Price) {
        ProductName = Name;
        Productprice = Price;
    }

    public item(String date,String Name, String Price, String buy) {
        ProductName = Name;
        Productprice = Price;
        Productbuy  = buy;
        Productdate  = date;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductdate() {
        return Productdate;
    }

    public String getProductPrice() {
        return Productprice;
    }

    public String getProductbuy() {
        return Productbuy;
    }

}

