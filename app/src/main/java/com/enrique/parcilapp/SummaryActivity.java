package com.enrique.parcilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TableLayout summaryTable;
    private static final String PREFERENCES_FILE = "calculations_prefs";
    private static final String LEAP_YEAR_KEY = "LeapYearResult";
    private static final String BITCOIN_CONVERSION_KEY = "BitcoinConversionResult";
    private Button btnSaveCalculations, btnFinish;

    private TextView txtLeapYearResult, txtBitcoinConversionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        txtLeapYearResult = findViewById(R.id.txtLeapYearResult);
        txtBitcoinConversionResult = findViewById(R.id.txtBitcoinConversionResult);
        btnSaveCalculations = findViewById(R.id.btnSaveCalculations);
        btnFinish = findViewById(R.id.btnFinish);

        // Asumiendo que pasas los resultados como extras con las claves "LeapYearResult" y "BitcoinConversionResult"
        String leapYearResult = getIntent().getStringExtra("LeapYearResult");
        String bitcoinConversionResult = getIntent().getStringExtra("BitcoinConversionResult");

        if (leapYearResult == null && bitcoinConversionResult == null) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);

            String storedLeapYearResult = sharedPreferences.getString(LEAP_YEAR_KEY, "No disponible");
            String storedBitcoinConversionResult = sharedPreferences.getString(BITCOIN_CONVERSION_KEY, "No disponible");

            // Configura los TextViews con los datos
            txtLeapYearResult.setText(storedLeapYearResult);
            txtBitcoinConversionResult.setText("Conversión Dólar-Bitcoin: " + storedBitcoinConversionResult);


        } else {
            txtLeapYearResult.setText(leapYearResult);
            txtBitcoinConversionResult.setText("Conversión Dólar-Bitcoin: " + bitcoinConversionResult);
        }


        btnSaveCalculations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCalculations();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    private void saveCalculations() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String leapYearResult = getIntent().getStringExtra("LeapYearResult");
        String bitcoinConversionResult = getIntent().getStringExtra("BitcoinConversionResult");

        editor.putString(LEAP_YEAR_KEY, leapYearResult);
        editor.putString(BITCOIN_CONVERSION_KEY, bitcoinConversionResult);
        editor.apply();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Esto garantiza que MainActivity esté en la parte superior de la pila, limpiando otras Activities si es necesario.
        startActivity(intent);
        finish(); // Finaliza la SummaryActivity.
    }
}