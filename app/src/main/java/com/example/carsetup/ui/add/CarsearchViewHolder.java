package com.example.carsetup.ui.add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;
import java.util.ArrayList;

public class CarsearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView lineTextview;
    private final ImageView cartypeimage;
    private final RecyclerViewOnItemClickListener listener;
    private Activity activity;

    public CarsearchViewHolder(Activity activity, @NonNull View itemView, @NonNull RecyclerViewOnItemClickListener listener) {
        super(itemView);
        lineTextview = itemView.findViewById(R.id.lineTextview);
        cartypeimage = itemView.findViewById(R.id.cartypeimage);
        this.listener = listener;
        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bindRow(@NonNull String stringdata) {
        String[] splited = stringdata.split("\\s+");
        lineTextview.setText(splited[1] + " " + splited[2] + " " + splited[3]);
        if (splited[4].equals("Cabriolet")) {
            cartypeimage.setBackgroundResource(R.drawable.cabriolet);
        } else if (splited[4].equals("Coupe")) {
            cartypeimage.setBackgroundResource(R.drawable.coupe);
        } else if (splited[4].equals("Roadster")) {
            cartypeimage.setBackgroundResource(R.drawable.roadster);
        } else if (splited[4].equals("Sedan")) {
            cartypeimage.setBackgroundResource(R.drawable.sedan);
        } else if (splited[4].equals("Crossover")) {
            cartypeimage.setBackgroundResource(R.drawable.crossover);
        } else if (splited[4].equals("Hatchback")) {
            cartypeimage.setBackgroundResource(R.drawable.hatchback);
        } else if (splited[4].equals("Hatchback 3 doors")) {
            cartypeimage.setBackgroundResource(R.drawable.hatchback);
        } else if (splited[4].equals("Liftback")) {
            cartypeimage.setBackgroundResource(R.drawable.liftback);
        } else if (splited[4].equals("Wagon")) {
            cartypeimage.setBackgroundResource(R.drawable.wagon);
        } else if (splited[4].equals("Minivan")) {
            cartypeimage.setBackgroundResource(R.drawable.minivan);
        } else if (splited[4].equals("Fastback")) {
            cartypeimage.setBackgroundResource(R.drawable.fastback);
        } else if (splited[4].equals("Pickup")) {
            cartypeimage.setBackgroundResource(R.drawable.pickup);
        } else if (splited[4].equals("Targa")) {
            cartypeimage.setBackgroundResource(R.drawable.targa);
        } else if (splited[4].equals("hardtop")) {
            cartypeimage.setBackgroundResource(R.drawable.hardtop);
        } else if (splited[4].equals("Limousine")) {
            cartypeimage.setBackgroundResource(R.drawable.limousine);
        }
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
        Log.d("LOGCAT", "Selected " + getAdapterPosition() + " option");
        String position = String.valueOf(getAdapterPosition());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("position",position);
        activity.setResult(activity.RESULT_OK,returnIntent);
        Toast.makeText(activity, "Car Added", Toast.LENGTH_SHORT).show();
        activity.finish();
    }

}