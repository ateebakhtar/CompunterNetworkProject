package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private ArrayList<String> pic= new ArrayList<>();
    private ArrayList<String> names= new ArrayList<>();
    private ArrayList<String> qty= new ArrayList<>();
    private ArrayList<String> price= new ArrayList<>();
    void payment(View view)
    {
        Intent p = new Intent(this,payment.class);
        startActivity(p);

    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        names.add("burger");
        qty.add("2");
        price.add("200");
        pic.add("https://oukosher.org/content/uploads/2018/06/IF-Burger-1.jpg");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null;
                        ArrayList<String> pic= new ArrayList<>();
                        ArrayList<String> names= new ArrayList<>();
                        ArrayList<String> qty= new ArrayList<>();
                        ArrayList<String> price= new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                x1 = x.getString("name");
                                x2 = x.getString("price");
                                x3 = x.getString("quantity");
                                x4 = x.getString("url");
                                String x5 = x.getString("user");
                                if(x5.equals(data.name))
                                {
                                    int temp1 = Integer.parseInt(x2);
                                    int tmep2 = Integer.parseInt(x3);
                                    temp1 = temp1 * tmep2;
                                    x2 = ""+temp1;
                                    System.out.println("Name"+x2);
                                    names.add(x1);
                                    qty.add(x3);
                                    price.add(x2);
                                    pic.add(x4);
                                }
                            }
                            cartadapter adapter = new cartadapter( Cart.this,price,names,qty,pic,15);
                            RecyclerView recyclerView=findViewById(R.id.recyclerView2);

                            if(adapter != null || recyclerView !=null)
                            {
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
                            }
                        }
                        else
                        {
                            System.out.println("Eorrrosr");
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        //Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(this,itemlist.class);
        startActivity(setIntent);
    }
}
