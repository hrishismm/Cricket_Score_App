package com.example.cricketlivescore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {
    EditText e1;
    Button b1;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        e1=(EditText)findViewById(R.id.editTextEmailsignin);
        b1=(Button)findViewById(R.id.cardView);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(e1.getText().toString().isEmpty())
                {
                    Toast.makeText(forgot.this, "Please fill the field", Toast.LENGTH_SHORT).show();

                }
                else {
                    auth.sendPasswordResetEmail(e1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(forgot.this, "Message sent to the email id", Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });



    }
}