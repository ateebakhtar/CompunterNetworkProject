package com.example.admin.cnproject;

public class Itemdata
{
    String name;
    String price;
    String url;
    String brand;
    String catagory;

    void setdata(String n,String p, String u, String b,String c)
    {
        this.name = n;
        this.brand = b;
        this.url = u;
        this.price = p;
        this.catagory = c;
    }
}
