package com.example.carsetup.ui.tasks;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;
import com.example.carsetup.ui.add.CarsearchViewHolder;
import com.example.carsetup.ui.notifications.NotificationsArray;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksViewHolder> {

    private final ArrayList<String> data;
    private final RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private Activity activity;

    public TasksAdapter(Activity activity, @NonNull ArrayList<String> data,
                            @NonNull RecyclerViewOnItemClickListener
                                    recyclerViewOnItemClickListener) {
        this.activity = activity;
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    @NonNull
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.carsearch_row, parent, false);
        return new TasksViewHolder(activity, row, recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        holder.bindRow(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}




