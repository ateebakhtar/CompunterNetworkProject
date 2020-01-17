package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class confirmitem extends AppCompatActivity {
    int total;
    int count = 1;
    int sum;
    TextView s1;
    TextView s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmitem);

        ImageView q = findViewById(R.id.image);
    de.hdodenhof.circleimageview.CircleImageView image = findViewById(R.id.image);
    Bundle x = getIntent().getExtras();

    s1 = findViewById(R.id.textView3);
    s2 = findViewById(R.id.textView5);

    String t1 = x.getString("Name");
    String t2 = x.getString("Price");
    String t3 = x.getString("url");

        Glide.with(this)

                .load(t3)
                .into(image);


    total =  Integer.parseInt(t2);
        s1.setText(t1);
        s2.setText(t2);
}
    public void add(View view)
    {
        count++;
        sum = total * count;
        s2.setText(" "+sum);
    }


    public void subract(View view) {
        if(count > 1)
        {
            count--;
        }
        else
        {
            Toast.makeText(this, "Cant be lower", Toast.LENGTH_SHORT).show();
        }
        sum = total * count;
        s2.setText(" "+sum);
    }
    public void addtocart(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        cartdata  x1 = new cartdata();

        Bundle x = getIntent().getExtras();
        String t1 = x.getString("Name");
        String t2 = x.getString("Price");
        String t3 = x.getString("url");
        String t4 = x.getString("ingre");
        String t5 = ""+count;
        x1.setdata(t1,t2,t5,t3,data.name);

        db.collection("Cart").add(x1);
    }
    public void opencart(View view)
    {
        Intent x = new Intent(this,Cart.class);
        startActivity(x);
    }
}
