package com.example.femme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    RelativeLayout  contact,help,sirenn,safe,signout,current,tip,view_off,yoga,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        contact = ( RelativeLayout) findViewById(R.id.contact);
        sirenn = ( RelativeLayout) findViewById(R.id.sirenn);

        current = ( RelativeLayout) findViewById(R.id.current);
        view_off = ( RelativeLayout) findViewById(R.id.view_off);

        tip = ( RelativeLayout) findViewById(R.id.tip);
        yoga = ( RelativeLayout) findViewById(R.id.yoga);
        help = ( RelativeLayout) findViewById(R.id.help);
        view = ( RelativeLayout) findViewById(R.id.view);

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,instanthealthcare.class));

            }
        });



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,nuitrition.class));

            }
        });


        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,skinmaintain.class));

            }
        });





        sirenn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,vaccine.class));
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,report.class));

            }
        });






        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),chatbot.class);
                startActivity(intent);


            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),consultants.class);
                startActivity(intent);

            }
        });

        view_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity2.class);

                startActivity(intent2);

            }
        });
    }

    }
