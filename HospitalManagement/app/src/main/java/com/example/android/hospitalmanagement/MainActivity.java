package com.example.android.hospitalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,age,gender,rating;

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Upload upload;
    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.editTextTextPersonName3);
        age=findViewById(R.id.editTextTextPersonName);
        gender=findViewById(R.id.editTextTextPersonName2);
        rating=findViewById(R.id.editTextTextPersonName4);
        upload=new Upload();
        recyclerView=findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference=firebaseDatabase.getInstance().getReference().child("Data");
        Button button_sav=findViewById(R.id.button_save);;
        button_sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setName(name.getText().toString());
                upload.setAge(age.getText().toString());
                upload.setGender(gender.getText().toString());
                upload.setRating(rating.getText().toString());
                String id=databaseReference.push().getKey();
                databaseReference.child(id).setValue(upload);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Upload> options=new FirebaseRecyclerOptions.Builder<Upload>()
                .setQuery(databaseReference, Upload.class)
                .build();
        FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Upload, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row, parent,false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Upload model) {
                holder.setData(getApplicationContext(),model.getName(),model.getAge(), model.getGender(),model.getRating());
                holder.setOnClickListener(new ViewHolder.Clicklistener() {
                    @Override
                    public void onItemlongClick(View view, int position) {
                       sname = getItem(position).getName();
                       showDeleteDataDialog(sname);
                    }
                });
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDeleteDataDialog(String sname) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Query query=databaseReference.orderByChild("name").equalTo(sname);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}