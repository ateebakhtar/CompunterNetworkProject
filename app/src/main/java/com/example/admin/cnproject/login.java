package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    EditText x;
    String io;
    EditText x1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x = findViewById(R.id.editText5);
        x1 = findViewById(R.id.editText6);
    }
    void openacc(View view)
    {
        Intent p = new Intent(this,createaccount.class);

        startActivity(p);
    }
    String getname()
    {
        return x.getText().toString();
    }
    String getpass()
    {
        return x1.getText().toString();
    }
    void confirm(int x)
    {
        if(x == 1)
        {
            Intent p = new Intent(this,itemlist.class);
            finder o = new finder();
            o.setname(io);
            startActivity(p);
        }
        else
        {
            Toast.makeText(this, "Incorrect Password or Username", Toast.LENGTH_LONG).show();
        }
    }
    void loginer(View view)
    {
        if(x.getText().toString().equals("logitech") && x1.getText().toString().equals("lotus"))
        {
            data.name = x.getText().toString();
           Intent p = new Intent(this,messenger.class);
           startActivity(p);
        }
        else if(x.getText().toString().equals("sony") && x1.getText().toString().equals("lotus"))
        {
            data.name = x.getText().toString();
            Intent p = new Intent(this,messenger.class);
            startActivity(p);
        }
        else if(x.getText().toString().equals("manager") && x1.getText().toString().equals("lotus"))
        {
            data.name = x.getText().toString();
            Intent p = new Intent(this,itemdet.class);
            startActivity(p);
        }
        else if(x.getText().toString().equals("creative") && x1.getText().toString().equals("lotus"))
        {
            data.name = x.getText().toString();
            Intent p = new Intent(this,messenger.class);
            startActivity(p);
        }
        else
        {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("User")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            String x3 = null, x1 = null, x2 = null,x4 = null;
                            x3 = getname();
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot x : task.getResult())
                                {
                                    x1 = x.getString("username");
                                    x2 = x.getString("password");

                                    if(x1.equals(x3)){
                                        break;
                                    }
                                    x1 = null;
                                }
                                if(x2.equals(getpass()) && x1 != null)
                                {
                                    finder p = new finder();
                                    p.setname("test");
                                    data o = new data();
                                    o.name = x1;
                                    o.setName(x1);
                                    confirm(1);
                                }
                                else
                                {
                                    confirm(0);
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
        Intent setIntent = new Intent(this,MainActivity.class);
        startActivity(setIntent);
    }
}
