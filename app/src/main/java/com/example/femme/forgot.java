package com.example.femme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {
    public EditText forgotEmail;
    public Button forgotButton;
    private ProgressBar determinateBar2;
    private TextView backLogin;

    //// Initialize Firebase Auth
    private FirebaseAuth mAuth;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mAuth = FirebaseAuth.getInstance();

        forgotEmail = (EditText) findViewById(R.id.forgotEmail);
        determinateBar2=(ProgressBar)findViewById(R.id.determinateBar2);
        backLogin=(TextView)findViewById(R.id.backLogin);

        forgotButton = (Button) findViewById(R.id.forgotButton);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(forgot.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validData();
            }
        });


    }

    private void validData() {
        String email=forgotEmail.getText().toString();
        if(email.isEmpty()){
            forgotEmail.setError("Enter correct email");
        }


        else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener((new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {



                        Toast.makeText(forgot.this, "Sucessfully ", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(forgot.this,MainActivity2.class));


                    } else {

                        Toast.makeText(forgot.this, "Try aagin", Toast.LENGTH_SHORT).show();

                    }

                }
            }));
        };

    }
}