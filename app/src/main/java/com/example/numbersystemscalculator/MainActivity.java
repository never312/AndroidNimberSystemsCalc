package com.example.numbersystemscalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Button calculateButton;
    private TextView binaryResult, octalResult, decimalResult, hexadecimalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим элементы по ID
        numberInput = findViewById(R.id.numberInput);
        calculateButton = findViewById(R.id.calculateButton);
        binaryResult = findViewById(R.id.binaryResult);
        octalResult = findViewById(R.id.octalResult);
        decimalResult = findViewById(R.id.decimalResult);
        hexadecimalResult = findViewById(R.id.hexadecimalResult);

        // Устанавливаем обработчик нажатия на кнопку "Рассчитать"
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    // Функция для рассчета результатов
    private void calculate() {
        String input = numberInput.getText().toString().trim();

        if (TextUtils.isEmpty(input)) {
            numberInput.setError("Введите число");
            return;
        }

        try {
            double number = Double.parseDouble(input);

            // Рассчитываем результаты для разных систем счисления
            String binary = Integer.toBinaryString((int) number);
            String octal = Integer.toOctalString((int) number);
            String decimal = String.valueOf(number);
            String hexadecimal = Integer.toHexString((int) number);

            // Если введено дробное число, то рассчитываем его дробную часть в двоичной системе счисления
            if (input.contains(".")) {
                String[] parts = input.split("\\.");
                double fraction = Double.parseDouble("0." + parts[1]);
                StringBuilder fractionBinary = new StringBuilder(".");
                for (int i = 0; i < 8; i++) {
                    fraction *= 2;
                    fractionBinary.append((int) fraction);
                    fraction %= 1;
                }
                binary += fractionBinary.toString();
            }

            // Обновляем текст в соответствующих TextView
            binaryResult.setText("Двоичная: " + binary);
            octalResult.setText("Восьмеричная: " + octal);
            decimalResult.setText("Десятичная: " + decimal);
            hexadecimalResult.setText("Шестнадцатеричная: " + hexadecimal);

        } catch (NumberFormatException e) {
            numberInput.setError("Введите правильное число");
        }
    }
}
