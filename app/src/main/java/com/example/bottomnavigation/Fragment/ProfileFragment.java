package com.example.bottomnavigation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;

public class ProfileFragment extends Fragment {

    private EditText nameEditText, emailEditText;
    private RadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private CheckBox termsCheckBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        nameEditText = view.findViewById(R.id.editTextName);
        emailEditText = view.findViewById(R.id.editTextEmail);


        maleRadioButton = view.findViewById(R.id.radioButtonMale);
        femaleRadioButton = view.findViewById(R.id.radioButtonFemale);
        otherRadioButton = view.findViewById(R.id.radioButtonOther);


        termsCheckBox = view.findViewById(R.id.checkBoxTerms);

        return view;
    }
}
