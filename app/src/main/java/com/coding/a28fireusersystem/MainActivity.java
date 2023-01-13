package com.coding.a28fireusersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference userRef = rootRef.child("Users");

    EditText username, name, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myUserName = username.getText().toString().trim();
                String myName = name.getText().toString().trim();
                String myEmail = email.getText().toString().trim();



//                theen me mujhe error msg show karna tha agar field empty chora hai to
//                but theen pe error show karne ke liye mujhe theen baar same code likhna para


                if(myUserName.length() == 0){
                    username.setError("No value");
                }
                else {
                    if(myName.length() == 0){
                        name.setError("No value");
                    }
                    else {
                        if(myEmail.length() == 0){
                            email.setError("No value");
                        }
                        else {



                    HashMap<String, String> userMap = new HashMap<String, String>();
                    userMap.put("UserName", myUserName);
                    userMap.put("Name", myName);
                    userMap.put("Email", myEmail);

                    userRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                } } }

            }
        });
    }
}