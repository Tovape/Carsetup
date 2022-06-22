package com.example.carsetup.ui.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsetup.MainActivity;
import com.example.carsetup.R;
import com.example.carsetup.RecyclerViewOnItemClickListener;
import com.example.carsetup.databinding.FragmentTasksBinding;
import com.example.carsetup.ui.add.AddFragment;
import com.example.carsetup.ui.add.CarsearchAdapter;
import com.example.carsetup.ui.add.CarsearchArray;
import com.example.carsetup.ui.add.CarsearchViewModel;
import com.example.carsetup.ui.notifications.NotificationsAdapter;
import com.example.carsetup.ui.notifications.NotificationsArray;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    // Global Variables
    Context context;
    Activity activity;
    private int user_id = 0;
    private com.example.carsetup.ui.tasks.TasksViewModel tasksViewModel;
    private FragmentTasksBinding binding;
    List<TasksArray> garageeach = new ArrayList<>();
    ArrayList<String> garagedata = new ArrayList<String>();
    String garage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel = new ViewModelProvider(this).get(com.example.carsetup.ui.tasks.TasksViewModel.class);
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getActivity();
        activity = getActivity();

        // Get User Login
        MainActivity mainActivity = new MainActivity();
        user_id = mainActivity.getUserId();
        Log.d("LOGCAT", "Garage Fragment User ID: " + user_id);

        // Check Connection Database
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

        // Getting Cars from Garage
        View tasksLayout = getLayoutInflater().inflate(R.layout.fragment_tasks, null);
        // Getting Instance
        RecyclerView recyclerView = root.findViewById(R.id.garage_list);

        // Getting Garage
        String query = "SELECT garage FROM users WHERE id_users = " + user_id + " LIMIT 1";
        ParseGarage parsegarage = new ParseGarage(query);
        parsegarage.execute("");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Getting Cars from Database

        String[] splited = garage.split("\\s+");
        int size = splited.length;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            stringBuilder.append("id = " + splited[i] + " OR ");
        }

        String finalString = stringBuilder.toString();

        finalString = (finalString.substring(0, finalString.length() - 4));

        String finalquery = "SELECT id, make, model, year_from, body_type FROM car_db WHERE " + finalString + " ORDER BY year_from LIMIT 10";
        Log.d("LOGCAT", "finalquery" + finalquery);
        GarageNumber garagenumber = new GarageNumber(garage, finalquery);
        garagenumber.execute("");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(new TasksAdapter(activity, garagedata, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //a
            }
        }));

        // Vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // Horizontal
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        return root;
    }

    // Functions
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String checkconnection = "0";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/carsetup", "test2", "123");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM connection LIMIT 1");
                ResultSetMetaData rsmd = rs.getMetaData();
                checkconnection = "1";
            } catch (Exception e) {
                e.printStackTrace();
                e.toString();
            }
            return checkconnection;
        }

        @Override
        protected void onPostExecute(String temp) {
            int checkconnectionInt = Integer.parseInt(temp);
            if (checkconnectionInt == 1) {
                Log.d("LOGCAT", "Connection Successfully");
            } else {
                Log.d("LOGCAT", "Connection Error");
            }
        }
    }

    private class ParseGarage extends AsyncTask<String, Void, String> {

        private final String query;

        public ParseGarage(String query) {
            this.query = query;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/carsetup", "test2", "123");
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery(query);
                Log.d("LOGCAT", "Garage Search:");
                while (result.next()){
                    garage = result.getString(1);
                    Log.d("LOGCAT", "Garage Keys: " + garage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GarageNumber extends AsyncTask<String, Void, String> {

        private final String garage;
        private final String finalquery;

        public GarageNumber(String garage, String finalquery) {
            this.garage = garage;
            this.finalquery = finalquery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/carbotdb", "test2", "123");
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery(finalquery);
                Log.d("LOGCAT", "Key Search:");
                while (result.next()){
                    garageeach.add(new TasksArray(Integer.parseInt(result.getString(1)),result.getString(2).replaceAll("\\s", ""),result.getString(3),Integer.parseInt(result.getString(4)),result.getString(5)));
                }
                for (int i = 0; i < garageeach.size(); i++) {
                    garagedata.add(new String(garageeach.get(i).toString()));
                    Log.d("LOGCAT", "List       Each: " + garageeach.get(i).toString());
                    Log.d("LOGCAT", "ArrayList  Each: " + garagedata.get(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}