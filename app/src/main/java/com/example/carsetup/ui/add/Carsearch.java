package com.example.carsetup.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;
import com.example.carsetup.ui.notifications.NotificationsViewModel;

import java.util.ArrayList;

public class Carsearch extends AppCompatActivity {

    // Variables
    Context context = this;
    Activity activity = this;
    private CarsearchViewModel carsearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carsearch);

        Bundle intent = getIntent().getExtras();
        ArrayList<String> carsearchdata = intent.getStringArrayList("cararray");

        for (int i = 0; i < carsearchdata.size(); i++) {
            Log.d("LOGCAT", "Car Search Getter: " + carsearchdata.get(i));
        }

        if (carsearchdata.size() == 0) {
            Toast.makeText(context, "No Cars Found", Toast.LENGTH_SHORT).show();
        }

        // Getting Instance
        RecyclerView recyclerView = findViewById(R.id.car_results);

        carsearchViewModel = new ViewModelProvider(this).get(CarsearchViewModel.class);
        recyclerView.setAdapter(new CarsearchAdapter(activity,carsearchdata, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                String text = position + " " + carsearchdata.get(position).toString();
            }
        }));

        // Go Back
        ImageButton goBack = findViewById(R.id.goback);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // Horizontal
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}