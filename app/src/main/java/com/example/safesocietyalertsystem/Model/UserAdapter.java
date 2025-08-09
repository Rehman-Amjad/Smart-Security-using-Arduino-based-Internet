package com.example.safesocietyalertsystem.Model;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safesocietyalertsystem.R;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<User> mDatalist;

    public UserAdapter(Context context, List<User> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(myview);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user=mDatalist.get(position);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(user.getImg(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        holder.imageView.setImageBitmap(decodedImage);

        holder.tvId.setText("ID: "+user.getId());

        if (user.getDoorSensor().equals("1"))
        {
            holder.tvFire_Sensor.setText("Door Sensor: Door is Open");
        }
        else
        {
            holder.tvFire_Sensor.setText("Door Sensor: Door is Close");
        }

        if (user.getGasSensor().equals("1"))
        {
            holder.tvsmoke_Sensor.setText("Gas Sensor: Gas is leak");
        }
        else
        {
            holder.tvsmoke_Sensor.setText("Gas Sensor: No gas leak");
        }

        if (user.getRainSensor().equals("1"))
        {
            holder.tvsmoke_Sensor.setText("No Rain at Home");
        }
        else
        {
            holder.tvsmoke_Sensor.setText("Rain at Home");
        }

        if (user.getLaserSensor().equals("1"))
        {
            holder.tvsmoke_Sensor.setText("Anyone person on Wall");
        }
        else
        {
            holder.tvsmoke_Sensor.setText("No any person on Wall");
        }

        holder.tv_temp.setText("Temperature Sensor: "+ user.getTempSensor() + " C");
        holder.tv_humidity.setText("Humidity Sensor: "+ user.getHumiditySensor() + " %");

        if (user.getWindowSensor().equals("1"))
        {
            holder.tvUltrasonicSensor.setText("Window Sensor: Window Open");
        }
        else
        {
            holder.tvUltrasonicSensor.setText("Window Sensor: Window Close");
        }




      /*  holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(holder.tvId.getContext());
                builder.setTitle("Delete Teacher Data");
                builder.setMessage("Press Yes or No?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {



                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

       */




    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tvFire_Sensor,tv_LDR,tvsmoke_Sensor,tvUltrasonicSensor,tv_temp,tv_humidity
                ,tv_rain,tv_leaser;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.card_image);
            tvId=itemView.findViewById(R.id.tvId);
            tvFire_Sensor=itemView.findViewById(R.id.tvFire_Sensor);
            tv_LDR=itemView.findViewById(R.id.tv_LDR);
            tvsmoke_Sensor=itemView.findViewById(R.id.tvsmoke_Sensor);
            tvUltrasonicSensor=itemView.findViewById(R.id.tvUltrasonicSensor);
            tv_temp=itemView.findViewById(R.id.tv_temp);
            tv_humidity=itemView.findViewById(R.id.tv_humidity);
            tv_rain=itemView.findViewById(R.id.tv_rain);
            tv_leaser=itemView.findViewById(R.id.tv_leaser);



        }
    }
}
