package com.example.bottomnavigation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class ToDoListFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText addItemEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);


        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);


        listView = view.findViewById(R.id.itemListView);
        addItemEditText = view.findViewById(R.id.addItemEditText);


        listView.setAdapter(adapter);


        addItemEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });

        return view;
    }

    private void showAddItemDialog() {
        // Create and show dialog fragment to add new item
        AddItemDialogFragment dialog = new AddItemDialogFragment();
        dialog.setOnItemAddedListener(new AddItemDialogFragment.OnItemAddedListener() {
            @Override
            public void onItemAdded(String item) {
                if (!item.isEmpty()) {
                    items.add(item);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "Item cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show(getParentFragmentManager(), "AddItemDialog");
    }
}
