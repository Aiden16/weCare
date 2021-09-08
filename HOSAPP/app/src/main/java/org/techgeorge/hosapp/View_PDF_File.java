package org.techgeorge.hosapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class View_PDF_File extends AppCompatActivity {
    ListView myPDfListView;
    DatabaseReference databaseReference;
    List<uploadPDF> uploadPDFS;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        myPDfListView=findViewById(R.id.myListView);
        uploadPDFS=new ArrayList<>();
        viewAllFiles();
        myPDfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                uploadPDF uploadPDF=uploadPDFS.get(position);
//                Intent intent=new Intent();
//                intent.setType(Intent.ACTION_VIEW,);
//                intent.setData(Uri.parse(uploadPDF.getUrl()));
//                startActivity(intent);
//                String fullUrl =uploadPDF.getUrl();
//                try {
//                Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
//                sharingIntent.setClassName("org.techgeorge.paymenthospital",
//                        "org.techgeorge.paymenthospital.View_PDF_File");
//                sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
//                startActivity(sharingIntent);
//                }catch (Exception e){
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(fullUrl));
//                    startActivity(i);
//                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(uploadPDF.getUrl()), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Intent newIntent = Intent.createChooser(intent, "Open File");
                try {
                    startActivity(newIntent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }

            }
        });


    }

    private void viewAllFiles() {
        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference userRef = rootRef.child("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    uploadPDF uploadPDF=postSnapshot.getValue(org.techgeorge.hosapp.uploadPDF.class);
                    uploadPDFS.add(uploadPDF);




                }
                String[] uploads=new String[uploadPDFS.size()];
                for (int i=0;i<uploads.length;i++){
                    uploads[i]=uploadPDFS.get(i).getName();

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploads){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        TextView myText=(TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                myPDfListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
