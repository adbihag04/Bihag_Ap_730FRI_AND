package com.example.bottomnavigation.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;

public class CalculatorFragment extends Fragment {

    private TextView resultText;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        resultText = view.findViewById(R.id.resultText);


        Button btn0 = view.findViewById(R.id.btn0);
        Button btn1 = view.findViewById(R.id.btn1);
        Button btn2 = view.findViewById(R.id.btn2);
        Button btn3 = view.findViewById(R.id.btn3);
        Button btn4 = view.findViewById(R.id.btn4);
        Button btn5 = view.findViewById(R.id.btn5);
        Button btn6 = view.findViewById(R.id.btn6);
        Button btn7 = view.findViewById(R.id.btn7);
        Button btn8 = view.findViewById(R.id.btn8);
        Button btn9 = view.findViewById(R.id.btn9);


        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnSubtract = view.findViewById(R.id.btnSubtract);
        Button btnMultiply = view.findViewById(R.id.btnMultiply);
        Button btnDivide = view.findViewById(R.id.btnDivide);
        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnEqual = view.findViewById(R.id.btnEqual);
        Button btnDot = view.findViewById(R.id.btnDot);


        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentNumber += button.getText().toString();
                resultText.setText(currentNumber);
            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        // Operation buttons
        btnAdd.setOnClickListener(v -> setOperator("+"));
        btnSubtract.setOnClickListener(v -> setOperator("-"));
        btnMultiply.setOnClickListener(v -> setOperator("*"));
        btnDivide.setOnClickListener(v -> setOperator("/"));


        btnDot.setOnClickListener(v -> {
            if (!currentNumber.contains(".")) {
                currentNumber += ".";
                resultText.setText(currentNumber);
            }
        });

        btnEqual.setOnClickListener(v -> calculateResult());


        btnClear.setOnClickListener(v -> clear());

        return view;
    }

    private void setOperator(String op) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
            operator = op;
            currentNumber = "";
        }
    }

    private void calculateResult() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultText.setText("Error");
                        return;
                    }
                    break;
            }
            resultText.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operator = "";
        }
    }

    private void clear() {
        currentNumber = "";
        firstNumber = 0;
        operator = "";
        resultText.setText("0");
    }
}