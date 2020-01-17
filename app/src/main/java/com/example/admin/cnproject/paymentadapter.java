package com.example.admin.cnproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;






public class paymentadapter extends RecyclerView.Adapter<paymentadapter.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> itemName =new ArrayList<>();
    double g;
    private Context mContext;

    public paymentadapter(Context mContext, ArrayList<String> b) {

        this.mContext = mContext;
        this.itemName =b;

    }

    public paymentadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentlayout,parent,false);
        paymentadapter.ViewHolder holder=new paymentadapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.itemName.setText(itemName.get(position));


    }
    void reload()
    {
        Intent p = new Intent(mContext,Cart.class);
        mContext.startActivity(p);
    }
    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView itemName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);

            itemName=itemView.findViewById(R.id.image_name);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
