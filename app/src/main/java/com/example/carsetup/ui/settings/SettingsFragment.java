package com.example.carsetup.ui.settings;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carsetup.MainActivity;
import com.example.carsetup.R;
import com.example.carsetup.databinding.FragmentSettingsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.tools.ant.Main;

public class SettingsFragment extends Fragment {

    // Variables
    private int user_id = 0;
    private int user_theme = 0;
    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;
    public Resources.Theme themeSender;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get User Login
        MainActivity mainActivity = new MainActivity();
        user_id = mainActivity.getUserId();
        user_theme = mainActivity.getUserTheme();

        Log.d("LOGCAT", "Settings Fragment User ID: " + user_id);
        Log.d("LOGCAT", "Theme Saved in Fragment " + user_theme);

        // Get Ids
        SwitchCompat themeswitch = root.findViewById(R.id.themeswitch);

        if (user_theme == 0) {
            themeswitch.setChecked(false);
            themeswitch.setText("Light Theme");
        } else if (user_theme == 1) {
            themeswitch.setChecked(true);
            themeswitch.setText("Dark Theme");
        } else {}

        // Theme Listener
        Resources.Theme theme;
        themeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("LOGCAT", String.valueOf(isChecked));
                if (isChecked == true) {
                    // Dark Theme
                    Log.d("LOGCAT", "Dark Theme On");
                    mainActivity.darkTheme();
                    themeswitch.setText("Dark Theme");
                    //mainActivity.refreshFragments();
                } else if (isChecked == false) {
                    // Light Theme
                    Log.d("LOGCAT", "Light Theme On");
                    mainActivity.lightTheme();
                    themeswitch.setText("Light Theme");
                    //mainActivity.refreshFragments();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}