package com.example.bottomnavigation.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bottomnavigation.R;

public class AddItemDialogFragment extends DialogFragment {

    private EditText newItemEditText;
    private OnItemAddedListener listener;

    public interface OnItemAddedListener {
        void onItemAdded(String item);
    }

    public void setOnItemAddedListener(OnItemAddedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());


        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_item, null);
        newItemEditText = view.findViewById(R.id.newItemEditText);


        builder.setTitle("Add New Item")
                .setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onItemAdded(newItemEditText.getText().toString());
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }
}
