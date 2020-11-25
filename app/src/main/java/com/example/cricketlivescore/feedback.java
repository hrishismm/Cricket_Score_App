package com.example.cricketlivescore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class feedback extends Fragment {
    EditText e1,e2,e3,e4;
    Button b1;
    FirebaseFirestore fstore;
    String userId;
    FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_feedback,container,false);  /*Infating the activity with a fragment on the container created View v=*/
        e1=(EditText)v.findViewById(R.id.name);
        e2=(EditText)v.findViewById(R.id.email);
        e3=(EditText)v.findViewById(R.id.phone);
        e4=(EditText)v.findViewById(R.id.message);
        fstore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        userId=mAuth.getCurrentUser().getUid();
        b1=(Button)v.findViewById(R.id.submit);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final String name = e1.getText().toString();
                final String email = e2.getText().toString();
                final String number = e3.getText().toString();
                final String message = e4.getText().toString();

                if(name.isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill the name field", Toast.LENGTH_SHORT).show();

                }
                else if(email.isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill the email field", Toast.LENGTH_SHORT).show();

                }
                else if(number.isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill number field", Toast.LENGTH_SHORT).show();

                }
                else if(message.isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill the message field", Toast.LENGTH_SHORT).show();

                }
                else {
                    DocumentReference documentReference = fstore.collection("Feedback").document(String.valueOf(userId));
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", name);
                    user.put("Email", email);
                    user.put("phoneno", number);
                    user.put("message", message);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Added Sucessfully", Toast.LENGTH_SHORT).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");

                        }
                    });

                }
            }
        });
        return v;
    }
}
