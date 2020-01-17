package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class editaccount extends AppCompatActivity {

    EditText x;
    EditText x1;
    EditText x2;
    EditText x3;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaccount);
        x = findViewById(R.id.editText);
        x1 = findViewById(R.id.editText2);
        x2 = findViewById(R.id.editText3);
        x3 = findViewById(R.id.editText4);

        x.setFocusable(false);
        x.setClickable(false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null,x5=null;
                        data o = new data();
                        x3 = o.getName();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                x1 = x.getString("username");
                                x2 = x.getString("password");
                                x4 = x.getString("email");
                                x5 = x.getString("number");
                                if(x1.equals(x3)){
                                    getdata(x1,x2,x4,x5);
                                    setid(x.getId());
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
    void getdata(String n,String p,String e,String num)
    {
        x.setText(n);
        x1.setText(p);
        x2.setText(e);
        x3.setText(num);
    }
    int u = 0;
    void  setu()
    {
        u = 1;
    }
    void setid(String id)
    {
        this.id = id;
    }
    void setitems(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(x1.getText().length() < 6)
        {
            Toast.makeText(this, "Password is to Small", Toast.LENGTH_LONG).show();
        }
        else if(x3.getText().length() < 8 )
        {
            Toast.makeText(this, "Enter Valid number", Toast.LENGTH_LONG).show();
        }
        else if(android.util.Patterns.EMAIL_ADDRESS.matcher(x2.getText().toString()).matches() == false)
        {
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_LONG).show();
        }
        else
        {


            db.collection("User").document(id).update("password",x1.getText().toString());
            db.collection("User").document(id).update("email",x2.getText().toString());
            db.collection("User").document(id).update("number",x3.getText().toString());

            Intent p = new Intent(this,login.class);
            startActivity(p);
        }
    }
}
