package com.example.admin.cnproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class messenger extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public String th;
    EditText p;
    int order = 0;
    void getstr(String a )
    {
        int temp = Integer.parseInt(a);
        if(order < temp)
        {
            order = temp;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        doit();
    }
    void doit()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Chats").orderBy("t",Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<ExampleItem> exampleList = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int count = 0;
                                String a = document.getString("message");
                                String b = document.getString("sender");
                                String a1 = document.getString("a");
                                String t = document.getString("t");
                                getstr(t);
                                //int q=Integer.parseInt(a1);
                                long i = (long)(System.currentTimeMillis()/1000);
                                final SimpleDateFormat s =new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
                                String y = s.format(new Date());
                                //Timestamp asd = new Timestamp(System.currentTimeMillis());
                                if(b.equals("logitech"))
                                {
                                    exampleList.add(new ExampleItem(R.drawable.logitech,a,b,1));
                                }
                                else if(b.equals("sony"))
                                {
                                    exampleList.add(new ExampleItem(R.drawable.sony,a,b,1));
                                }
                                else if(b.equals("creative"))
                                {
                                    exampleList.add(new ExampleItem(R.drawable.creative,a,b,1));
                                }
                                else
                                {
                                    exampleList.add(new ExampleItem(R.drawable.add,a,b,1));
                                }





                            }
                            //Collections.sort(exampleList.);
                            mRecyclerView=findViewById(R.id.recyclerView);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager=new LinearLayoutManager(messenger.this);
                            mAdapter=new ExampleAdapter(exampleList);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            System.out.println("Error");
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("Chats").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    DocumentSnapshot documentSnapshot = dc.getDocument();
                    String id = documentSnapshot.getId();
                    int oldIndex = dc.getOldIndex();
                    int newIndex = dc.getNewIndex();

                    switch (dc.getType()) {
                        case ADDED:
                            doit();
                            break;
                        case MODIFIED:
                            doit();
                            //textViewData.append("\nModified: " + id +
                            //      "\nOld Index: " + oldIndex + "New Index: " + newIndex);
                            break;
                        case REMOVED:
                            //textViewData.append("\nRemoved: " + id +
                            //      "\nOld Index: " + oldIndex + "New Index: " + newIndex);
                            break;
                    }
                }
            }
        });
    }
    public void store(View view)
    {
        data x = new data();
        p = findViewById(R.id.editText);
        String temp = p.getText().toString();
        info test = new info();
        final SimpleDateFormat s =new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
        String y = s.format(new Date());
        finder i = new finder();
        System.out.println(i.getname());
        test.add(temp,x.name,"ateeb",y,""+order);
        System.out.println(i.name);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Chats").add(test);
        p.getText().clear();
        Intent o = new Intent(this,MainActivity.class);
        //startActivity(o);
    }





}
