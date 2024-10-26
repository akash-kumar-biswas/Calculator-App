package com.example.myfirstapp; // MainActivity.java

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumber, secondNumber;
    private TextView resultText;
    private String lastOperation = ""; // Store the last operation
    private int equalButtonClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        resultText = findViewById(R.id.resultText);

        Button addButton = findViewById(R.id.addButton);
        Button mulButton = findViewById(R.id.mulButton);
        Button subButton = findViewById(R.id.subButton);
        Button divButton = findViewById(R.id.divButton);
        Button powButton = findViewById(R.id.powButton);
        Button equalButton = findViewById(R.id.equalButton);

        addButton.setOnClickListener(view -> performOperation("+"));
        mulButton.setOnClickListener(view -> performOperation("*"));
        subButton.setOnClickListener(view -> performOperation("-"));
        divButton.setOnClickListener(view -> performOperation("/"));
        powButton.setOnClickListener(view -> performOperation("^"));

        equalButton.setOnClickListener(view -> {
            equalButtonClickCount++;
            performOperation("=");

            new Handler().postDelayed(() -> equalButtonClickCount = 0, 300);

            if (equalButtonClickCount == 2) {

                double result = getResultFromLastOperation();

                if(isFractionOfInteger(result)) {
                    firstNumber.setText(String.valueOf((int)result));
                }
                else {
                    firstNumber.setText(String.valueOf(result));
                }
                secondNumber.setText("");
                equalButtonClickCount = 0;
            }
        });

        equalButton.setOnLongClickListener(view -> {
            resultText.setText("Please enter both numbers");
            firstNumber.setText("");
            secondNumber.setText("");
            return false;
        });

    }

    public static boolean isFractionOfInteger(double number) {
        return (number - Math.floor(number)) == 0 && number <= 1e9 + 10;
    }

    @SuppressLint("SetTextI18n")
    private void performOperation(String operator) {
        // Get input numbers
        String firstInput = firstNumber.getText().toString();
        String secondInput = secondNumber.getText().toString();

        if (firstInput.isEmpty() && secondInput.isEmpty()) {
            resultText.setText("Please enter both numbers");
            return;
        } else if (firstInput.isEmpty()) {
            resultText.setText("Please enter the first number");
            return;
        } else if (secondInput.isEmpty()) {
            resultText.setText("Please enter the second number");
            return;
        }

        double num1 = Double.parseDouble(firstInput);
        double num2 = Double.parseDouble(secondInput);
        double result;

        if (!operator.equals("=")) {
            lastOperation = operator;
            resultText.setText(firstInput + " " + operator + " " + secondInput + " = ?");
            return;
        }

        switch (lastOperation) {
            case "+":
                result = num1 + num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                if(num1==0 && num2==0){
                    resultText.setText("Indeterminate form");
                    return;
                }
                else if (num2 == 0) {
                    resultText.setText("Can not divide by zero");
                    return;
                }

                result = num1 / num2;
                break;
            case "^":
                if(num1==0 && num2==0){
                    resultText.setText("Indeterminate form");
                    return;
                }

                result = Math.pow(num1, num2);
                break;
            default:
                resultText.setText("Please select an operation");
                return;
        }

        if (isFractionOfInteger(result))
            resultText.setText(firstInput + " " + lastOperation + " " + secondInput + " = " + (int) Math.floor(result));
        else
            resultText.setText(firstInput + " " + lastOperation + " " + secondInput + " = " + result);
    }

    private double getResultFromLastOperation() {
        String firstInput = firstNumber.getText().toString();
        String secondInput = secondNumber.getText().toString();

        double num1 = Double.parseDouble(firstInput);
        double num2 = Double.parseDouble(secondInput);
        double result = 0;

        switch (lastOperation) {
            case "+":
                result = num1 + num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
            case "^":
                if(num1!=0 && num2!=0) {
                    result = Math.pow(num1, num2);
                }
                break;
        }
        return result;
    }
}
