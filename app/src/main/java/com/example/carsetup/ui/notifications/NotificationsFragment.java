package com.example.carsetup.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsetup.R;
import com.example.carsetup.databinding.FragmentNotificationsBinding;
import com.example.carsetup.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    // Variables
    Context context;
    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    @Override
    public void onAttach(Context temp) {
        super.onAttach(temp);
        context = temp;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setting Notifications
        notificationRecycleView(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void notificationRecycleView(View root) {

        // Get Notifications View Layout
        View notificationsLayout = getLayoutInflater().inflate(R.layout.fragment_notifications, null);
        // Getting Instance
        RecyclerView recyclerView = root.findViewById(R.id.notification_list);

        // Setting Up List
        final List<NotificationsArray> notification_each = getNotifications();

        recyclerView.setAdapter(new NotificationsAdapter(notification_each, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                String text = position + " " + notification_each.get(position).getTitle();
                //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }));

        // Vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // Horizontal
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @SuppressWarnings("ResourceType")
    private List<NotificationsArray> getNotifications() {
        List<NotificationsArray> notification_each = new ArrayList<>();
        notification_each.add(new NotificationsArray(1,"Title", "Subtitle"));
        notification_each.add(new NotificationsArray(2,"Hello", "You've even"));
        notification_each.add(new NotificationsArray(3,"Alan", "In the end"));
        return notification_each;
    }

}