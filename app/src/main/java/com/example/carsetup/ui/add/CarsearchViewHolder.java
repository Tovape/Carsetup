package com.example.carsetup.ui.add;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;

import java.util.ArrayList;

public class CarsearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView lineTextview;
    private final RecyclerViewOnItemClickListener listener;

    public CarsearchViewHolder(@NonNull View itemView, @NonNull RecyclerViewOnItemClickListener listener) {
        super(itemView);
        lineTextview = itemView.findViewById(R.id.lineTextview);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    public void bindRow(@NonNull String stringdata) {
        Log.d("LOGCAT", "CAME" + stringdata);
        lineTextview.setText(stringdata);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}