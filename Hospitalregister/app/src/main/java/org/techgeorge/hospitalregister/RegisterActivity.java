package org.techgeorge.hospitalregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, professionEditText, workEditText, passwordEditText,aciEdit,fciEdit,doctoredit;
    private EditText phoneEditText, emailEditText;
    private ImageView picImageView;
    private CheckBox maleCheckBox, femaleCheckBox;
    private Button registerButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private String TAG = "RegisterActivity";
    private String username, fname, email, profession, phone, workplace,aci,fci,doctor;
    private String password;
    private org.techgeorge.hospitalregister.User1 user1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.fullname_edittext);
        professionEditText = findViewById(R.id.profession_edittext);
        workEditText = findViewById(R.id.workplace_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);
        passwordEditText = findViewById(R.id.enterpass_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        picImageView = findViewById(R.id.pic_imageview);
        aciEdit= findViewById(R.id.twitter_edittext);
        fciEdit=findViewById(R.id.Gulucaose);
        doctoredit=findViewById(R.id.DoctorComment);
        maleCheckBox = findViewById(R.id.male_checkbox);
        femaleCheckBox = findViewById(R.id.female_checkbox);
        registerButton = findViewById(R.id.register_button);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if(emailEditText.getText().toString() != null && passwordEditText.getText().toString() != null) {
                    fname = nameEditText.getText().toString();
                    email = emailEditText.getText().toString();
                    phone = phoneEditText.getText().toString();
                    profession = professionEditText.getText().toString();
                    workplace = workEditText.getText().toString();
                    aci=aciEdit.getText().toString();
                    fci=fciEdit.getText().toString();
                    doctor=doctoredit.getText().toString();
                    password = passwordEditText.getText().toString();
                    user1 = new org.techgeorge.hospitalregister.User1(fname, email, profession, workplace, phone,fci,aci,doctor);
                    registerUser();
                }
            }
        });

    }

    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

    /**
     * adding user information to database and redirect to login screen
     * @param currentUser
     */
    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user1); //adding user info to database
        Intent loginIntent = new Intent(this, MainActivity2.class);
        startActivity(loginIntent);
    }
}
