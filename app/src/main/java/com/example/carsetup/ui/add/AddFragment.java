package com.example.carsetup.ui.add;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.carsetup.R;
import com.example.carsetup.databinding.FragmentAddBinding;
import com.whiteelephant.monthpicker.MonthPickerDialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {

    // Global Variables
    Context context;
    private AddViewModel addViewModel;
    private FragmentAddBinding binding;
    EditText manufacturer;
    EditText model;
    Button year;
    Button insertcar;
    Button searchcar;
    int yearselected = 2000;
    List<CarsearchArray> carsearcheach = new ArrayList<>();
    ArrayList<String> carsearchdata = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getActivity();

        // Get Variables
        manufacturer = root.findViewById(R.id.manufacturer);
        model = root.findViewById(R.id.model);
        year = root.findViewById(R.id.yearselector);
        insertcar = root.findViewById(R.id.insertcar);
        searchcar = root.findViewById(R.id.searchcar);

        // Check Connection Database
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");

        // Get Year
        year.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                yearselected = setNormalPicker(yearselected);
                Log.d("LOGCAT", "Output: " + String.valueOf(yearselected));
            }
        });

        // Insert Car
        insertcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String query = "INSERT INTO cars VALUES (null, '" + manufacturer.getText() + "', '" +  model.getText() + "', '" + yearselected + "')";
                InsertCar insertcar = new InsertCar(query);
                insertcar.execute("");
            }
        });

        // Search Car
        searchcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String query = "SELECT make, model, year_from FROM car_db WHERE make LIKE UPPER('%" + manufacturer.getText() + "%') AND model LIKE UPPER('%" + model.getText() + "%') ORDER BY year_from LIMIT 10";
                carsearcheach.removeAll(carsearcheach);
                carsearchdata.removeAll(carsearchdata);
                SearchCar searchcar = new SearchCar(query);
                searchcar.execute("");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SearchCarList();
            }
        });

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

    private class InsertCar extends AsyncTask<String, Void, String> {

        private final String query;

        public InsertCar(String query) {
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
                int result = st.executeUpdate(query);
                if (result == 1) {
                    Log.d("LOGCAT", "Car Inserted");
                } else {
                    Log.d("LOGCAT", "Error while inserting car");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class SearchCar extends AsyncTask<String, Void, String> {

        private final String query;

        public SearchCar(String query) {
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
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/carbotdb", "test2", "123");
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery(query);
                //Log.d("LOGCAT", "Car Search:");
                while (result.next()){
                    //Log.d("LOGCAT", "Manufacturer: " + result.getString(1) + " | Model: " + result.getString(2) + " | Year: " + result.getString(3));
                    carsearcheach.add(new CarsearchArray(Integer.parseInt(result.getString(3)),result.getString(1), result.getString(2)));
                }
                for (int i = 0; i < carsearcheach.size(); i++) {
                    carsearchdata.add(new String(carsearcheach.get(i).toString()));
                    Log.d("LOGCAT", "List       Each: " + carsearcheach.get(i).toString());
                    Log.d("LOGCAT", "ArrayList  Each: " + carsearchdata.get(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int setNormalPicker(int activeyear) {
        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(context, new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                Log.d("LOGCAT", "Year Selected: " + selectedYear);
                yearselected = selectedYear;
            }
        }, today.get(Calendar.YEAR), 0);

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1970)
                    .setActivatedYear(activeyear)
                    .setMaxYear(2050)
                    .setMinMonth(Calendar.FEBRUARY)
                    .setTitle("Select Year")
                    .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                    .showYearOnly()
                    // .setMaxMonth(Calendar.OCTOBER)
                    // .setYearRange(1890, 1890)
                    // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                    // .showMonthOnly()
                    /* Everytime Year is Changed
                    .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                        @Override
                        public void onYearChanged(int selectedYear) {
                            Log.d("LOGCAT", "Selected year : " + selectedYear);
                        }
                    })
                    */
                    .build()
                    .show();

        return yearselected;
    }

    public void SearchCarList() {
        Intent carsearchIntent = new Intent(context, Carsearch.class);
        carsearchIntent.putStringArrayListExtra("cararray", (ArrayList<String>) carsearchdata);
        startActivity(carsearchIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}