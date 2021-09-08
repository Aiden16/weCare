package org.techgeorge.hosapp;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.techgeorge.hosapp.R;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView occupationTxtView, nameTxtView, workTxtView;
    private TextView emailTxtView, phoneTxtView, videoTxtView, facebookTxtView, twitterTxtView;
    private ImageView userImageView, emailImageView, phoneImageView, videoImageView;
    private ImageView facebookImageView, twitterImageView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private static final String USERS = "users";

    private Button registerButton,sign;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);

        Log.v("USERID", userRef.getKey());

        occupationTxtView = findViewById(R.id.occupation_textview);
        nameTxtView = findViewById(R.id.name_textview);
        workTxtView = findViewById(R.id.workplace_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
        videoTxtView = findViewById(R.id.video_textview);
        facebookTxtView = findViewById(R.id.facebook_textview);
        //twitterTxtView = findViewById(R.id.twitter_textview);

        userImageView = findViewById(R.id.user_imageview);
        emailImageView = findViewById(R.id.email_imageview);
        phoneImageView = findViewById(R.id.phone_imageview);
        videoImageView = findViewById(R.id.phone_imageview);
        facebookImageView = findViewById(R.id.facebook_imageview);
        registerButton=findViewById(R.id.button3);
        sign=findViewById(R.id.button4);
        // fb=findViewById(R.id.facebook_textview);
        //twitterImageView = findViewById(R.id.twitter_imageview);
        ;


        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            String fname, mail, profession, workplace, phone, fci, aci, doctor;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId : dataSnapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        fname = keyId.child("fullName").getValue(String.class);
                        profession = keyId.child("profession").getValue(String.class);
                        workplace = keyId.child("workplace").getValue(String.class);
                        phone = keyId.child("phone").getValue(String.class);
                        aci = keyId.child("aci").getValue(String.class);
                        fci = keyId.child("fci").getValue(String.class);
                        doctor = keyId.child("doctor").getValue(String.class);
                        break;
                    }
                }
                nameTxtView.setText(fname);
                emailTxtView.setText("A1C-" + aci);
                occupationTxtView.setText(profession);
                workTxtView.setText(workplace);
                phoneTxtView.setText("Fasting Blood Sugar-" + fci);
                videoTxtView.setText("Glucose Tolerance-" + phone);
                facebookTxtView.setText(doctor);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                //Intent loginIntent = new Intent(this, MainActivity1.class);
                Intent myIntent = new Intent(v.getContext(),View_PDF_File.class);
                startActivity(myIntent);

            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent re=new Intent(view.getContext(),MainActivity1.class);
                startActivity(re);

            }
        });
        //finish();

    }

}