package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class createaccount extends AppCompatActivity {

    EditText x;
    EditText x1;
    EditText x2;
    EditText x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        x = findViewById(R.id.editText);
        x1 = findViewById(R.id.editText2);
        x2 = findViewById(R.id.editText3);
        x3 = findViewById(R.id.editText4);

    }
    int u = 0;
    void setu()
    {
        u = 1;
    }
    void setdata(View view)
    {
        userdata m = new userdata();
        final String temp = x.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        String x3 = null, x1 = null, x2 = null,x4 = null;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot x : task.getResult())
                            {
                                System.out.println("SOMEGING");
                                if(x.getString("username").equals(temp))
                                {
                                    setu();
                                    break;
                                }
                            }

                        }
                        else
                        {
                            System.out.println("Eorrrosr");
                        }
                    }
                });

        if(x.getText().length() < 6)
        {
            Toast.makeText(this, "UserName is to Small", Toast.LENGTH_LONG).show();
        }
        else if(x1.getText().length() < 8 )
        {
            Toast.makeText(this, "Password is to Small", Toast.LENGTH_LONG).show();
        }
        else if(x3.getText().length() < 8)
        {
            Toast.makeText(this, "Enter Valid Number", Toast.LENGTH_LONG).show();
        }
        else if(android.util.Patterns.EMAIL_ADDRESS.matcher(x2.getText().toString()).matches() == false)
        {
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_LONG).show();
        }
        else if(u == 1 || temp.equals("logitech") || temp.equals("sony") || temp.equals("creative"))
        {
            Toast.makeText(this, "Username Already Exists", Toast.LENGTH_LONG).show();
            u = 0;
        }
        else
        {
            m.setdata(x.getText().toString(),x1.getText().toString(),x2.getText().toString(),x3.getText().toString());
            //FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("User").add(m);
            Intent p = new Intent(this,login.class);
            startActivity(p);
        }

    }

}
