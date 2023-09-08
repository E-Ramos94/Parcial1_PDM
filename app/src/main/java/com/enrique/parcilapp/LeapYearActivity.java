package com.enrique.parcilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LeapYearActivity extends AppCompatActivity {

    private EditText edtYear;
    private Button btnResolve, btnContinue;
    private TextView txtResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leap_year);

        edtYear = findViewById(R.id.edtYear);
        btnResolve = findViewById(R.id.btnResolve);
        btnContinue = findViewById(R.id.btnContinue);
        txtResult = findViewById(R.id.txtResult);

        btnResolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLeapYear();
            }
        });

        // Aquí puedes agregar la lógica para el botón "Continuar" si es necesario
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeapYearActivity.this, CurrencyConversionActivity.class);
                intent.putExtra("LeapYearResult", txtResult.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void checkLeapYear() {
        int year = Integer.parseInt(edtYear.getText().toString());

        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            txtResult.setText(year + " es un año bisiesto.");
        } else {
            txtResult.setText(year + " no es un año bisiesto.");
        }

        btnContinue.setEnabled(true);
    }
}