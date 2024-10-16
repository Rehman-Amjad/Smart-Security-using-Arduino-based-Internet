package com.example.safesocietyalertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class DoorActivity extends AppCompatActivity {

    MediaPlayer player;

    ImageView img_smoke_back,img_fire;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String fireValue,img;

    TextView tv_fire,date_text,time_text,tvTimer;
    GifImageView gif;

    Button btn_back,btnOpen;

    private CountDownTimer countDownTimer;
    private static final long START_TIME_IN_MILLIS = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        img_smoke_back=findViewById(R.id.img_smoke_back);

        img_fire=findViewById(R.id.img_fire);
        tv_fire=findViewById(R.id.tv_fire);
        btn_back=findViewById(R.id.btn_back);
        gif=findViewById(R.id.gif);
        date_text=findViewById(R.id.date_text);
        time_text=findViewById(R.id.time_text);
        btnOpen=findViewById(R.id.btnOpen);
        tvTimer=findViewById(R.id.tvTimer);

        img_smoke_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(DoorActivity.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(DoorActivity.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });




        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CurrentData");
        DatabaseReference callref=myRef.child("1000");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("DoorSensor").getValue(String.class);
                img=snapshot.child("img").getValue(String.class);
                String date =snapshot.child("Dated").getValue(String.class);
                String time =snapshot.child("Timed").getValue(String.class);

                date_text.setText("Date: "+date);
                time_text.setText("Time: "+time);



                if (fireValue.equals("1"))
                {


                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] imageBytes = baos.toByteArray();
                    imageBytes = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    img_fire.setImageBitmap(decodedImage);

                    tv_fire.setText("Door is Open");
                    gif.setVisibility(View.VISIBLE);

                    play();

                }
                else
                {
                    tv_fire.setText("Door is close");
                    gif.setVisibility(View.INVISIBLE);
                    img_fire.setImageResource(R.drawable.okimage);
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("DoorSensor").getValue(String.class);

                String date =snapshot.child("Dated").getValue(String.class);
                String time =snapshot.child("Timed").getValue(String.class);

                date_text.setText("Date: "+date);
                time_text.setText("Time: "+time);


                if (fireValue.equals("1"))
                {


                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] imageBytes = baos.toByteArray();
                    imageBytes = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    img_fire.setImageBitmap(decodedImage);

                    tv_fire.setText("Door is Open");
                    gif.setVisibility(View.VISIBLE);

                    play();

                }
                else
                {
                    tv_fire.setText("Door is close");
                    gif.setVisibility(View.INVISIBLE);
                    img_fire.setImageResource(R.drawable.okimage);
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

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a map to hold the updates
                Map<String, Object> map = new HashMap<>();
                map.put("DoorInfo", "0");

                // Update the Firebase database reference
                FirebaseDatabase.getInstance().getReference().updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Update was successful
                                    Toast.makeText(getApplicationContext(), "Door is Open", Toast.LENGTH_SHORT).show();
                                    startTimer();
                                } else {
                                    // Update failed
                                    Toast.makeText(getApplicationContext(), "Failed to update door status", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
    public void play()
    {
        if (player == null)
        {
            player = MediaPlayer.create(DoorActivity.this, R.raw.smokealaem);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }

    private void startTimer() {
        tvTimer.setVisibility(View.VISIBLE);
        btnOpen.setVisibility(View.GONE);
        countDownTimer = new CountDownTimer(START_TIME_IN_MILLIS, 1000) {

            // Called every second
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) (millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(secondsLeft));
            }

            // Called when the timer finishes
            @Override
            public void onFinish() {
                tvTimer.setText("0");
                onTimerFinish();  // Call the function when the timer ends
            }
        }.start();
    }

    // Function to run when the timer finishes
    private void onTimerFinish() {
        tvTimer.setVisibility(View.GONE);
        btnOpen.setVisibility(View.VISIBLE);
        // Create a map to hold the updates
        Map<String, Object> map = new HashMap<>();
        map.put("DoorInfo", "1");

        // Update the Firebase database reference
        FirebaseDatabase.getInstance().getReference().updateChildren(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Update was successful
                            Toast.makeText(getApplicationContext(), "Door is Close Automatically", Toast.LENGTH_SHORT).show();
                        } else {
                            // Update failed
                            Toast.makeText(getApplicationContext(), "Failed to update door status", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        // Add any other functionality here
    }
}