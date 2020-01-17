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

public class cartadapter extends RecyclerView.Adapter<cartadapter.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> itemName =new ArrayList<>();
    double g;
    private ArrayList<String> PriceNo=new ArrayList<>();
    private ArrayList<String> url=new ArrayList<>();
    private ArrayList<String> QuantityNo=new ArrayList<>();
    private Context mContext;

    public cartadapter(Context mContext, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d,ArrayList<String> e,int g) {

        this.mContext = mContext;
        this.itemName =b;
        this.url = e;
        this.PriceNo=c;
        this.g = g;
        this.QuantityNo=d;
    }

    public cartadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlayout,parent,false);
        cartadapter.ViewHolder holder=new cartadapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)

                .load(url.get(position))
                .into(holder.image);

        holder.itemName.setText(itemName.get(position));
        holder.PriceNo.setText(PriceNo.get(position));
        holder.QuantityNo.setText(QuantityNo.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                openDialog(position);

            }
        });

    }
    public void openDialog(final int position1) {

        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

        // Set Custom Title
        TextView title = new TextView(mContext);
        // Title Properties
        title.setText("Delete Item");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(mContext);
        // Message Properties
        msg.setText("Do you wish to delete this item?");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Yes", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                  FirebaseFirestore db = FirebaseFirestore.getInstance();

                  db.collection("Cart")
                          .get()
                          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                              @Override
                              public void onComplete(Task<QuerySnapshot> task) {
                                  String x3 = null, x1 = null, x2 = null,x4 = null,x5=null;
                                  data o = new data();
                                  x3 = o.getName();
                                  String temp = PriceNo.get(position1);
                                  if (task.isSuccessful()) {
                                      for (QueryDocumentSnapshot x : task.getResult())
                                      {
                                          x1 = x.getString("name");

                                          if(x1.equals(temp)){
                                                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                                              db1.collection("Cart").document(x.getId()).delete();
                                              reload();
                                                  break;
                                          }
                                          x1 = null;
                                      }

                                  }
                                  else
                                  {
                                      System.out.println("Eorrrosr");
                                  }
                              }
                          });
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        new Dialog(mContext);
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
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
        CircleImageView image;
        TextView itemName;
        TextView Price;
        TextView PriceNo;
        TextView Quantity;
        TextView QuantityNo;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            //Price=itemView.findViewById(R.id.textView2);
            itemName=itemView.findViewById(R.id.itemName);
            PriceNo=itemView.findViewById(R.id.textView3);
            image=itemView.findViewById(R.id.image);
            //Quantity=itemView.findViewById(R.id.textView4);
            QuantityNo=itemView.findViewById(R.id.textView5);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
