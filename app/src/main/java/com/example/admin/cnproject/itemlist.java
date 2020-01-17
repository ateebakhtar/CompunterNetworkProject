package com.example.admin.cnproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class itemlist extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static int temp1 = 0;
    String pos;

    void logout(View view)
    {

        Intent p = new Intent(itemlist.this,viewaccount.class);
        startActivity(p);
    }
    void mess(View view)
    {
        Intent p = new Intent(this,messenger.class);
        startActivity(p);
    }
    void opencart(View view)
    {
        Intent p = new Intent(this,Cart.class);
        startActivity(p);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"All", "keyboard", "mouse","storage","sound"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        Bundle q = getIntent().getExtras();
        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null,x6 = null;
                        ArrayList<String> mNames = new ArrayList<String>();
                        ArrayList<String> mImageUrls = new ArrayList<String>();
                        ArrayList<String> xn = new ArrayList<String>();
                        ArrayList<String> brand = new ArrayList<String>();
                        Spinner dropdown = findViewById(R.id.spinner);
                        if (task.isSuccessful())
                        {

                            String x5 = dropdown.getSelectedItem().toString();
                            for (QueryDocumentSnapshot x : task.getResult()) {
                                x6 = x.getString("brand");
                                x3 = x.getString("url");
                                x2 = x.getString("name");
                                x1 = x.getString("price");
                                x4 = x.getString("catagory");


                                System.out.println(x5+x4);
                                if(x4.equals(x5) || x5.equals("All"))
                                {
                                    mNames.add(x2);
                                    mImageUrls.add(x3);
                                    xn.add(x1);
                                    brand.add(x6);
                                }
                            }
                        }
                        itemadapter adapter = new itemadapter(itemlist.this, mImageUrls, mNames, xn,brand);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(itemlist.this));
                        System.out.println("this is a tryyy" + xn.isEmpty());


                    }

                });
    }
    void opener(View view)
    {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        Bundle q = getIntent().getExtras();
        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null,x6 = null;
                        ArrayList<String> mNames = new ArrayList<String>();
                        ArrayList<String> mImageUrls = new ArrayList<String>();
                        ArrayList<String> xn = new ArrayList<String>();
                        ArrayList<String> brand = new ArrayList<String>();
                        Spinner dropdown = findViewById(R.id.spinner);
                        if (task.isSuccessful())
                        {
                            String x5 = dropdown.getSelectedItem().toString();
                            for (QueryDocumentSnapshot x : task.getResult()) {
                                x6 = x.getString("brand");
                                x3 = x.getString("url");
                                x2 = x.getString("name");
                                x1 = x.getString("price");
                                x4 = x.getString("catagory");
                                System.out.println(x5+x4);
                                if(x4.equals(x5) || x5.equals("All") )
                                {
                                    mNames.add(x2);
                                    mImageUrls.add(x3);
                                    xn.add(x1);
                                    brand.add(x6);
                                }
                            }
                        }
                        itemadapter adapter = new itemadapter(itemlist.this, mImageUrls, mNames, xn,brand);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(itemlist.this));
                        System.out.println("this is a tryyy" + xn.isEmpty());
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

        Intent p = new Intent(this,login.class);
        startActivity(p);
    }
}
