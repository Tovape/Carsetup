package com.example.carsetup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.carsetup.ui.add.AddFragment;
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
    public int user_id = 0;
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

        // Load login page
        LinearLayout layoutlogin = findViewById(R.id.layout_login_container);
        LinearLayout layoutfragments = findViewById(R.id.layout_fragment_container);
        Button loginbutton = findViewById(R.id.loginbutton);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("LOGCAT", "Logging with: " + username.getText() + " " + password.getText());
                String query = "SELECT id_users FROM users WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
                Log.d("LOGCAT", "Query: " + query);
                LoginUser loginuser = new LoginUser(String.valueOf(username.getText()), String.valueOf(password.getText()), query);
                loginuser.execute("");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("LOGCAT", "Logged As: " + user_id);

                // Hide Login Page and reveal the fragments
                if (user_id != 0) {
                    layoutlogin.setVisibility(View.GONE);
                    layoutfragments.setVisibility(View.VISIBLE);
                } else {
                    Log.d("LOGCAT", "Put a Toast Here");
                }
            }
        });
    }

    // Constructor
    public MainActivity() {}

    // Getters and Setters
    public int getUserId() {
        return user_id;
    }

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

    // Classes
    private class LoginUser extends AsyncTask<String, Void, String> {

        private final int status = 0;
        private String username;
        private String password;
        private String query;

        public LoginUser(String username, String password, String query) {
            this.username = username;
            this.password = password;
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
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Log.d("LOGCAT", "ID User: " + rs.getString(1));
                    user_id = Integer.parseInt(rs.getString(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.toString();
            }
            return null;
        }
    }

}