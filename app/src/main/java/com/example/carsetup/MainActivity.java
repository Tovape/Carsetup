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

        // Get Theme
        theme = super.getTheme();
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


    private void BottomNavigator(BottomNavigationView navView) {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_tasks, R.id.navigation_add, R.id.navigation_notifications, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // In case actionbar is visible - NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}