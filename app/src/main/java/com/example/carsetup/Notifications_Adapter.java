package com.example.carsetup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Notifications_Adapter extends RecyclerView.Adapter<Notifications_ViewHolder> {

    private final List<Notifications> data;
    private final RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public Notifications_Adapter(@NonNull List<Notifications> data,
                                  @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    @NonNull
    public Notifications_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row, parent, false);
        return new Notifications_ViewHolder(row, recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(Notifications_ViewHolder holder, int position) {
        holder.bindRow(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}




