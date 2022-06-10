package com.example.carsetup;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.carsetup.databinding.ActivityMainBinding;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    // SQL Config
    /*
    CREATE USER 'test2'@'%' IDENTIFIED BY '123';
    FLUSH PRIVILEGES;
    Blob texts
    Toast.makeText(MainActivity.this, "Database Connection success", Toast.LENGTH_SHORT).show();
    */

    // Variables
    Context context = this;
    private ActivityMainBinding binding;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Connect Database
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

        // Design
        /* Absolute Positioning
        FrameLayout root = (FrameLayout)findViewById(R.id.test);
        View img = new View(this);
        img.setBackground(ContextCompat.getDrawable(context, R.drawable.greenstatus));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(20, 20);
        params.leftMargin = 20;
        params.topMargin  = 20;
        root.addView(img, params);
        */

        // Drawer
        /*
        drawerLayout = findViewById(R.id.container);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        // Bottom Navigator
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_tasks, R.id.navigation_add, R.id.navigation_notifications, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // In case actionbar is visible - NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Set Backgrounds In Fragment
        /*
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        fragment.getView().setBackgroundColor(Color.RED);
        */

        // Set StatusBar Color - Just use Themes.xml
        /*
        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.mainColor));
        */

        // General


    }

    // Drawer Function
    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

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
}