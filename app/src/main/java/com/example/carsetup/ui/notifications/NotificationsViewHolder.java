package com.example.carsetup.ui.notifications;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;

public class NotificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final View circleView;
    private final TextView titleTextView;
    private final TextView subtitleTextView;
    private final RecyclerViewOnItemClickListener listener;

    public NotificationsViewHolder(@NonNull View itemView, @NonNull RecyclerViewOnItemClickListener listener) {
        super(itemView);
        circleView = itemView.findViewById(R.id.circleView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    public void bindRow(@NonNull NotificationsArray color) {
        titleTextView.setText(color.getTitle());
        subtitleTextView.setText(color.getSubtitle());
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}