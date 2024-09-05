package com.bihag.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnAdd, btnSubtract, btnMultiply, btnDivide, btnEqual, btnClear;
    private TextView resultText;
    private String input = ""; // To store user input
    private double num1 = 0, num2 = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultText = findViewById(R.id.resultText);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);


        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                resultText.setText(input);
            }
        };


        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);
        btnDot.setOnClickListener(numberListener);


        btnAdd.setOnClickListener(createOperatorListener("+"));
        btnSubtract.setOnClickListener(createOperatorListener("-"));
        btnMultiply.setOnClickListener(createOperatorListener("*"));
        btnDivide.setOnClickListener(createOperatorListener("/"));


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty() && isOperatorPressed) {
                    num2 = Double.parseDouble(input);
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                resultText.setText("Error");
                                input = "";
                                return;
                            }
                            break;
                    }
                    // Format the result to 2 decimal places
                    String formattedResult = String.format("%.2f", result);
                    resultText.setText(formattedResult);
                    input = formattedResult;
                    isOperatorPressed = false;
                }
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                num1 = 0;
                num2 = 0;
                operator = "";
                isOperatorPressed = false;
                resultText.setText("0");
            }
        });
    }


    private View.OnClickListener createOperatorListener(final String op) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    num1 = Double.parseDouble(input);
                    operator = op;
                    input = "";
                    isOperatorPressed = true;
                }
            }
        };
    }
}
