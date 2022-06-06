package com.example.carsetup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
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

    /*  Fixing Perms for Connection
    CREATE USER 'test2'@'%' IDENTIFIED BY '123';
    FLUSH PRIVILEGES;
    Blob texts
    Toast.makeText(MainActivity.this, "Database Connection success", Toast.LENGTH_SHORT).show();
    */

    // Variables
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Database
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_tasks).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // Set Background In Fragment
        /*
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        fragment.getView().setBackgroundColor(Color.RED);
        */

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
}