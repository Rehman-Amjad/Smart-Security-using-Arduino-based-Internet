package com.example.safesocietyalertsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class LeaserActivity extends AppCompatActivity {

    ImageView img_ultra_back,img_fire;


    FirebaseDatabase database;
    DatabaseReference myRef;
    String fireValue,img;

    TextView tv_fire,date_text,time_text;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaser);

        img_ultra_back=findViewById(R.id.img_ultra_back);
        img_fire=findViewById(R.id.img_fire);
        tv_fire=findViewById(R.id.tv_fire);
        btn_back=findViewById(R.id.btn_back);
        date_text=findViewById(R.id.date_text);
        time_text=findViewById(R.id.time_text);


        img_ultra_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(LeaserActivity.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(LeaserActivity.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CurrentData");
        DatabaseReference callref=myRef.child("1000");


        myRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("LaserSensor").getValue(String.class);
                String date =snapshot.child("Dated").getValue(String.class);
                String time =snapshot.child("Timed").getValue(String.class);

                date_text.setText("Date: "+date);
                time_text.setText("Time: "+time);


                img=snapshot.child("img").getValue(String.class);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(img, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                img_fire.setImageBitmap(decodedImage);

                int value = Integer.parseInt(fireValue);

                if(value == 1){
                    tv_fire.setText("Anyone person on Wall");
                }else{
                    tv_fire.setText("No any person on Wall");
                }








            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("LaserSensor").getValue(String.class);
                String date =snapshot.child("Dated").getValue(String.class);
                String time =snapshot.child("Timed").getValue(String.class);

                date_text.setText("Date: "+date);
                time_text.setText("Time: "+time);
                img=snapshot.child("img").getValue(String.class);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(img, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                img_fire.setImageBitmap(decodedImage);

                int value = Integer.parseInt(fireValue);

                if(value == 1){
                    tv_fire.setText("Anyone person on Wall");
                }else{
                    tv_fire.setText("No any person on Wall");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}