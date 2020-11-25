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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    EditText e_email, e_password;
    Button b_sign_in;
    TextView b_sign_up;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        e_email = (EditText) findViewById(R.id.editTextEmailsignin);
        e_password = (EditText) findViewById(R.id.editTextPasswordsignin);
        b_sign_in = (Button) findViewById(R.id.cardView);
        mAuth = FirebaseAuth.getInstance();
       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        b_sign_up=(TextView)findViewById(R.id.textView2);
        b_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),signup.class);
                startActivity(intent);

            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(signin.this, MainActivity2.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out

        }

        b_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = e_email.getText().toString();
                final String password = e_password.getText().toString();
                if(e_email.getText().toString().equals("admin") && e_password.getText().toString().equals("admin"))
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else if(email.isEmpty())
                {
                    Toast.makeText(signin.this, "Please fill the empty text boxes", Toast.LENGTH_SHORT).show();
                }
                else if( password.isEmpty())
                {
                    Toast.makeText(signin.this, "Please fill the empty text boxes", Toast.LENGTH_SHORT).show();

                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        e_email.setText("");
                                        e_password.setText("");
                                        Toast.makeText(signin.this, "Signed in Sucessfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(signin.this, "Check Email Password", Toast.LENGTH_SHORT).show();
                                        e_email.setText("");
                                        e_password.setText("");
                                    }

                                    // ...
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(signin.this);
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