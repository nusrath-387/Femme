package com.example.femme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox checkbox;
    private Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    private TextView signupText;
    private EditText pass,email;
    private Button SignUp;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.signup);


        signupText=(TextView) findViewById(R.id.signupText);
        SignUp=(Button) findViewById(R.id.signUp);
        pass=(EditText) findViewById(R.id.pass);
        email=(EditText) findViewById(R.id.email);

        signupText.setOnClickListener((View.OnClickListener) this);
        SignUp.setOnClickListener((View.OnClickListener) this);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    //Hide Password:
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //Show Password:
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.signUp:
               UserRagister();
            break;

            case R.id.signupText:
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                break;
            default:
                break;



    }
}

    private void UserRagister() {
        String emailid=email.getText().toString().trim();
        String password=pass.getText().toString().trim();


        if(emailid.isEmpty()){
            email.setError("Enter an email addresss");
            email.requestFocus();
            return;

        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            email.setError("Enter an email addresss");
            email.requestFocus();
            return;
        }


        if(password.isEmpty()){
            pass.setError("Enter an vaild password");
            pass.requestFocus();
            return;

        }

        if(!PASSWORD_PATTERN.matcher(password).matches()){
        pass.setError("Enter an vaild password");
            pass.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //Task<Void> task doesnt return

//                             userID=firebaseAuth.getCurrentUser().getUid();
//                             FirebaseUser users=firebaseAuth.getCurrentUser();


                            Toast.makeText(MainActivity.this, "Successfully registered,Check your email for verification", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);

                            startActivity(intent1);

                        }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(MainActivity.this, "Fail to send verfication email", Toast.LENGTH_SHORT).show();


                        }
                    });


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "Already Registered", Toast.LENGTH_SHORT).show();

                }


            }
        });
}}