package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class edititems extends AppCompatActivity {
    String q;
    String id;
    EditText w1,w2,w3,w4,w5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititems);
        w1 = findViewById(R.id.editText7);
        w2 = findViewById(R.id.editText8);
        w3 = findViewById(R.id.editText9);
        w4 = findViewById(R.id.editText10);
        w5 = findViewById(R.id.editText11);

        Bundle x = getIntent().getExtras();
        q = x.getString("Name");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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

                            for (QueryDocumentSnapshot x : task.getResult()) {
                                x6 = x.getString("brand");
                                x3 = x.getString("url");
                                x2 = x.getString("name");
                                x1 = x.getString("price");
                                x4 = x.getString("catagory");


                                System.out.println(q+x4);
                                if(x2.equals(q) )
                                {
                                    w1.setText(x2);
                                    w2.setText(x4);
                                    w3.setText(x6);
                                    w4.setText(x1);
                                    w5.setText(x3);
                                    id = x.getId();
                                    break;
                                }
                            }
                        }

                    }

                });
    }
    void update(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Items").document(id).update("name",w1.getText().toString());
        db.collection("Items").document(id).update("brand",w3.getText().toString());
        db.collection("Items").document(id).update("price",w4.getText().toString());
        db.collection("Items").document(id).update("catagory",w2.getText().toString());
        db.collection("Items").document(id).update("url",w5.getText().toString());
        Intent p = new Intent(this,itemlist.class);
        startActivity(p);

    }
}
