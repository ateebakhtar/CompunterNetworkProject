package com.example.admin.cnproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class viewaccount extends AppCompatActivity {
    EditText x;
    EditText x1;
    EditText x2;
    EditText x3;
    void logout(View view)
    {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you Sure you want to Logout.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent p = new Intent(viewaccount.this,login.class);
                        startActivity(p);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewaccount);
        x = findViewById(R.id.editText);
        x1 = findViewById(R.id.editText2);
        x2 = findViewById(R.id.editText3);
        x3 = findViewById(R.id.editText4);

        x.setClickable(false);
        x.setFocusable(false);
        x1.setFocusable(false);
        x2.setFocusable(false);
        x3.setFocusable(false);

        x1.setClickable(false);
        x2.setClickable(false);
        x3.setClickable(false);

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
    void opener(View view)
    {
        Intent p = new Intent(this,editaccount.class);
        startActivity(p);
    }
}
