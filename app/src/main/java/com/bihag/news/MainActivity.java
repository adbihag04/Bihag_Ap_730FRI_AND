package com.bihag.news;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> headlines = new ArrayList<>();
    private ArrayList<String> newsContents = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private boolean showingHeadlines = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);

        if (savedInstanceState != null) {
            headlines = savedInstanceState.getStringArrayList("headlines");
            newsContents = savedInstanceState.getStringArrayList("newsContents");
            showingHeadlines = savedInstanceState.getBoolean("showingHeadlines");
            if (showingHeadlines) {
                displayHeadlines();
            } else {
                displayNewsContents();
            }
        }

        findViewById(R.id.btnAddHeadline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new add_headline());
            }
        });

        findViewById(R.id.btnShowHeadlines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showingHeadlines) {
                    showingHeadlines = true;
                    displayHeadlines();
                }
            }
        });

        findViewById(R.id.btnShowNewsContent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingHeadlines) {
                    showingHeadlines = false;
                    displayNewsContents();
                }
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            showingHeadlines = !showingHeadlines;
            if (showingHeadlines) {
                displayHeadlines();
            } else {
                displayNewsContents();
            }
        });
    }

    public void addHeadline(String headline, String content) {
        headlines.add(0, headline);
        newsContents.add(0, content);
        if (showingHeadlines) {
            displayHeadlines();
        } else {
            displayNewsContents();
        }
        listView.setSelection(0); // Scrolls the ListView to the top after adding a new item
    }

    private void displayHeadlines() {
        adapter.clear();
        adapter.addAll(headlines);
        adapter.notifyDataSetChanged();
    }

    private void displayNewsContents() {
        adapter.clear();
        adapter.addAll(newsContents);
        adapter.notifyDataSetChanged();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("headlines", headlines);
        outState.putStringArrayList("newsContents", newsContents);
        outState.putBoolean("showingHeadlines", showingHeadlines);
    }
}