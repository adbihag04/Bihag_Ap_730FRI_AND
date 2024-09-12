package com.example.listviewapril;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listView;
    private EditText addItemEditText;
    private ArrayList<Item> itemList;
    private ItemAdapter itemAdapter;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.itemListView);
        addItemEditText = findViewById(R.id.addItemEditText);

        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, itemList);
        listView.setAdapter(itemAdapter);

        addItemEditText.setOnClickListener(v -> showAddEditDialog(null, -1));

        listView.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    public void showAddEditDialog(Item existingItem, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_dialog_add_edit_item, null);
        builder.setView(dialogView);

        final ImageView itemImageView = dialogView.findViewById(R.id.itemImageView);
        final EditText itemNameEditText = dialogView.findViewById(R.id.itemNameEditText);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button saveButton = dialogView.findViewById(R.id.saveButton);

        if (existingItem != null) {
            itemNameEditText.setText(existingItem.getName());
            itemImageView.setImageURI(existingItem.getImageUri());
            selectedImageUri = existingItem.getImageUri();
        }

        itemImageView.setOnClickListener(v -> pickImageFromGallery());

        final AlertDialog dialog = builder.create();
        dialog.show();

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        saveButton.setOnClickListener(v -> {
            String itemName = itemNameEditText.getText().toString();

            if (itemName.isEmpty() || selectedImageUri == null) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (existingItem == null) {
                Item newItem = new Item(itemName, selectedImageUri);
                itemList.add(newItem);
            } else {
                existingItem.setName(itemName);
                existingItem.setImageUri(selectedImageUri);
                itemList.set(position, existingItem);
            }

            itemAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
        }
    }
}

