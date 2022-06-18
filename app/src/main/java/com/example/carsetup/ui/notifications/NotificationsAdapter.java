package com.example.carsetup.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsViewHolder> {

    private final List<NotificationsArray> data;
    private final RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public NotificationsAdapter(@NonNull List<NotificationsArray> data,
                                @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    @NonNull
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row, parent, false);
        return new NotificationsViewHolder(row, recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        holder.bindRow(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}




