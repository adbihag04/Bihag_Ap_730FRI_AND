package com.bihag.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set title and subtitle for the Action Bar
        getSupportActionBar().setTitle("My App Title");
        getSupportActionBar().setSubtitle("My App Subtitle");
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:

                AnotherFragment fragment = new AnotherFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.menu_item2:

                MyDialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialog");
                return true;

            case R.id.menu_item3:

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
