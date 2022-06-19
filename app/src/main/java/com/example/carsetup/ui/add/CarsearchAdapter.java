package com.example.carsetup.ui.add;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class CarsearchAdapter extends RecyclerView.Adapter<CarsearchViewHolder> {

    private final ArrayList<String> data;
    private final RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private Activity activity;

    public CarsearchAdapter(Activity activity, @NonNull ArrayList<String> data,
                            @NonNull RecyclerViewOnItemClickListener
                                    recyclerViewOnItemClickListener) {
        this.activity = activity;
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    @NonNull
    public CarsearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.carsearch_row, parent, false);
        return new CarsearchViewHolder(activity, row, recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(CarsearchViewHolder holder, int position) {
        holder.bindRow(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}




