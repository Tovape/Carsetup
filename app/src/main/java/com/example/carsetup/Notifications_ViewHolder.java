package com.example.carsetup;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Notifications_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final View circleView;
    private final TextView titleTextView;
    private final TextView subtitleTextView;
    private final RecyclerViewOnItemClickListener listener;

    public Notifications_ViewHolder(@NonNull View itemView, @NonNull RecyclerViewOnItemClickListener listener) {
        super(itemView);
        circleView = itemView.findViewById(R.id.circleView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    public void bindRow(@NonNull Notifications color) {
        titleTextView.setText(color.getTitle());
        subtitleTextView.setText(color.getSubtitle());
        GradientDrawable gradientDrawable = (GradientDrawable) circleView.getBackground();
        gradientDrawable.setColor(Integer.parseInt("-1"));
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}