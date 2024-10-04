package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.Fragment.CalculatorFragment;
import com.example.bottomnavigation.Fragment.ProfileFragment;
import com.example.bottomnavigation.Fragment.ToDoListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Load the ProfileFragment by default when the app opens
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }

        // Set up bottom navigation item selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_calculator:
                    selectedFragment = new CalculatorFragment();
                    break;
                case R.id.navigation_list:
                    selectedFragment = new ToDoListFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Set the profile tab as the default selected tab
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
    }
}
