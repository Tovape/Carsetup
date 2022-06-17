package com.example.carsetup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.carsetup.databinding.ActivityMainBinding;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    // Variables
    Context context = this;
    private ActivityMainBinding binding;
    static public Resources.Theme theme;
    static public Fragment fragments;
    static public FragmentActivity mainActivity = new FragmentActivity();
    static public BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Bottom Navigator
        navView = findViewById(R.id.nav_view);
        BottomNavigator(navView);

        // Fragment Getters
        fragments = ((Fragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main));

        // Connect Database
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

        // Get Theme
        theme = super.getTheme();

        // Set Real Buttons Background
        //getWindow().setNavigationBarColor(ContextCompat.getColor(context, R.color.white));
        // Set Bottom Nav Colors
        //navView.setItemIconTintList(ContextCompat.getDrawable(context, R.drawable.));

    }

    public MainActivity() {}

    // Theme Activators
    static public void darkTheme() {
        theme.applyStyle(R.style.Theme_Dark, true);
        navView.setBackgroundResource(R.color.fourthColor);
    }

    static public void lightTheme() {
        theme.applyStyle(R.style.Theme_Light, true);
        navView.setBackgroundResource(R.color.white);
    }

    // Functions
    static public void refreshFragments() {
        Log.d("LOGCAT", "FIX TODO");
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .detach(fragments)
                .attach(fragments)
                .commit();
    }

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
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/decosol", "test2", "123");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM config LIMIT 1");
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

    private void BottomNavigator(BottomNavigationView navView) {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_tasks, R.id.navigation_add, R.id.navigation_notifications, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // In case actionbar is visible - NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}