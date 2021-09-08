package org.techgeorge.hosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button registerButton;
    private Button registerButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerButton = findViewById(R.id.button);
        registerButton2 = findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                //Intent loginIntent = new Intent(this, MainActivity1.class);
                Intent myIntent = new Intent(v.getContext(),MainActivity4.class);
                startActivity(myIntent);

            }
        });
        registerButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                Intent loginIntent = new Intent(v.getContext(), MainActivity1.class);
                startActivity(loginIntent);

            }
        });

    }
}