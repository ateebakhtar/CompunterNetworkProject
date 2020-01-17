package com.example.admin.cnproject;


public class ExampleItem
{
    private int mImageResource;
    private String mText1;
    private String mText2;
    private  int a;

    public ExampleItem(int imageResource, String text1, String text2,int a) {
        mImageResource = imageResource;
        mText1 = text1;
        this.a = a;
        mText2 = text2;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public int geta() {
        return a;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
