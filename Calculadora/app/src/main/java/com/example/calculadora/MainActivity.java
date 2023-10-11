package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private StringBuilder currentInput;
    private String currentOperator;
    private String calculationBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        currentInput = new StringBuilder();
        currentOperator = null;
        calculationBuffer = "";

        // Obtener referencias a los botones y establecer escuchadores de clic
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numberButtons[i] = findViewById(resID);
        }

        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonClear = findViewById(R.id.buttonClear);

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appendNumber(finalI);
                }
            });
        }

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator("-");
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculator();
            }
        });
    }

    private void appendNumber(int number) {
        if (currentInput.toString().equals("0")) {
            currentInput = new StringBuilder();
        }
        currentInput.append(number);
        updateDisplay();
    }

    private void operator(String operator) {
        if (currentOperator != null) {
            calculate();
        }
        currentOperator = operator;
        calculationBuffer = currentInput.toString();
        currentInput = new StringBuilder();
    }

    private void calculate() {
        if (currentOperator != null && !calculationBuffer.isEmpty() && !currentInput.toString().isEmpty()) {
            int operand1 = Integer.parseInt(calculationBuffer);
            int operand2 = Integer.parseInt(currentInput.toString());
            int result = 0;

            switch (currentOperator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                // Agrega más casos según sea necesario

            }

            currentInput = new StringBuilder(String.valueOf(result));
            calculationBuffer = "";
            currentOperator = null;
            updateDisplay();
        }
    }

    private void clearCalculator() {
        currentInput = new StringBuilder();
        currentOperator = null;
        calculationBuffer = "";
        updateDisplay();
    }

    private void updateDisplay() {
        editText.setText(currentInput.toString());
    }
}
