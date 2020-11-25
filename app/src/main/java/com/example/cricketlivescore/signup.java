package com.example.cricketlivescore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

    public class signup extends AppCompatActivity {
        EditText e_email, e_password;
        Button b_sign_up, b_sign_in;
        TextView forgotp;
        private FirebaseAuth mAuth;
        FirebaseFirestore  fstore;
        String userId;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_signup);
            e_email = (EditText) findViewById(R.id.editTextEmail);
            e_password = (EditText) findViewById(R.id.editTextPassword);
            b_sign_up = (Button) findViewById(R.id.buttonSignup);
            b_sign_in = (Button) findViewById(R.id.buttonSignin);
            forgotp=(TextView)findViewById(R.id.forgotpassword);
            mAuth = FirebaseAuth.getInstance();
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("message");
            fstore=FirebaseFirestore.getInstance();
            forgotp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(),forgot.class);
                    startActivity(i);
                }
            });
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                // User is signed in
                Intent i = new Intent(signup.this, MainActivity2.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                // User is signed out

            }

            b_sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String email = e_email.getText().toString();
                    final String password = e_password.getText().toString();
                    e_email.setText("");
                    e_password.setText("");
                    if (email.isEmpty()) {
                        Toast.makeText(signup.this, "Please fill the empty text boxes", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.isEmpty()) {
                        Toast.makeText(signup.this, "Please fill the empty text boxes", Toast.LENGTH_SHORT).show();

                    } else {
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {


                                        if (task.isSuccessful()) {
                                            Toast.makeText(signup.this, "Success", Toast.LENGTH_SHORT).show();
                                            userId = mAuth.getCurrentUser().getUid();
                                            DocumentReference documentReference = fstore.collection("users").document(userId);
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("emailname", email);
                                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(signup.this, "Added Sucessfully", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            Intent intent = new Intent(getApplicationContext(), signin.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(signup.this, "Error", Toast.LENGTH_SHORT).show();
                                        }

                                        // ...
                                    }
                                });


                    }
                }
            });
            b_sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    e_email.setText("");
                    e_password.setText("");
                    Intent intentt = new Intent(getApplicationContext(), signin.class);
                    startActivity(intentt);


                }
            });
        }

        @Override
        public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
            builder.setTitle("Confirmation PopUp!").
                    setMessage("Are you sure, that you want close the app?");
            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent a = new Intent(Intent.ACTION_MAIN);
                            a.addCategory(Intent.CATEGORY_HOME);
                            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(a);

                        }
                    });
            builder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder.create();
            alert11.show();

        }
    }