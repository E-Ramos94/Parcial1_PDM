package com.enrique.parcilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyConversionActivity extends AppCompatActivity {

    private EditText edtDollars;
    private Button btnConvert, btnContinueConversion;
    private TextView txtBitcoinValue;

    // Valor estático de conversión. En la vida real, obtendrías esto de una API.
    private static final double DOLLAR_TO_BITCOIN_RATE = 0.000039; // Ejemplo de tasa, asegúrate de usar una tasa actualizada.

    String leapYearResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_conversion);

        edtDollars = findViewById(R.id.edtDollars);
        btnConvert = findViewById(R.id.btnConvert);
        btnContinueConversion = findViewById(R.id.btnContinueConversion);
        txtBitcoinValue = findViewById(R.id.txtBitcoinValue);

        leapYearResult = getIntent().getStringExtra("LeapYearResult");

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToBitcoin();
            }
        });

        btnContinueConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bitcoin = txtBitcoinValue.getText().toString();

                Intent intent = new Intent(CurrencyConversionActivity.this, SummaryActivity.class);
                intent.putExtra("LeapYearResult", leapYearResult); // pasas el resultado del año bisiesto que obtuviste anteriormente
                intent.putExtra("BitcoinConversionResult", bitcoin); // pasas el resultado de la conversión
                startActivity(intent);
            }
        });
    }

    private void convertToBitcoin() {
        double dollars = Double.parseDouble(edtDollars.getText().toString());
        double bitcoinValue = dollars * DOLLAR_TO_BITCOIN_RATE;

        txtBitcoinValue.setText(String.format("%.8f BTC", bitcoinValue));

        btnContinueConversion.setEnabled(true);
    }
}