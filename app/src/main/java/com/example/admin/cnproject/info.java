package com.example.admin.cnproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class info
{
    public String message;
    public String a;
    public int  temp = 0;
    public int  temp1 = 0;
    public String t;
    public String sender;
    public String reciver;
    void add(String x,String x1,String x2,String x3,String x4)
    {
        int p = Integer.parseInt(x4);
        p++;
        this.t = ""+p;
        this.a = x3;
        this.message = x;
        this.sender = x1;
        this.reciver = x2;
    }
}


