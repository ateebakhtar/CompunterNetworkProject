package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class payment extends AppCompatActivity {
    int total =0;
    void open(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent p = new Intent(this,thankyou.class);
        db.collection("Cart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null;
                        ArrayList<String> names= new ArrayList<>();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                x1 = x.getString("user");
                                if(x1.equals(data.name))
                                {
                                    db.collection("Cart").document(x.getId()).delete();
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Eorrrosr");
                        }
                    }
                });
        startActivity(p);
    }
    void addtotal(int m)
    {
        total = total + m;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null;
                        ArrayList<String> names= new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                x1 = x.getString("name");
                                x2 = x.getString("price");

                                String x5 = x.getString("user");
                                if(x5.equals(data.name))
                                {
                                    int temp = Integer.parseInt(x2);
                                    addtotal(temp);
                                    System.out.println("Name"+x2);
                                    names.add(x1);
                                }



                            }
                            TextView p = findViewById(R.id.textView13);
                            p.setText(""+total);
                            paymentadapter adapter = new paymentadapter( payment.this,names);
                            RecyclerView recyclerView=findViewById(R.id.recyclerView3);

                            if(adapter != null || recyclerView !=null)
                            {
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(payment.this));
                            }
                        }
                        else
                        {
                            System.out.println("Eorrrosr");
                        }
                    }
                });

    }
}
