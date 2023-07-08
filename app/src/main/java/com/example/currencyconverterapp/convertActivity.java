package com.example.currencyconverterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;


public class convertActivity  extends AppCompatActivity {

    private Button convert;
    private Button back;
    private TextView fromLabel;
    private TextView toLabel;
    static TextView toAmount;
    private EditText fromAmount;

    conversion conversion = new conversion();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        convert = findViewById(R.id.convert);
        back = findViewById(R.id.back);
        fromLabel = findViewById(R.id.fromLabel);
        toLabel = findViewById(R.id.toLabel);
        toAmount = findViewById(R.id.toAmount);
        fromAmount = findViewById(R.id.fromAmount);

        String currency = getIntent().getStringExtra("currency");
        if (currency != null) {
            fromLabel.setText("USD");
            toLabel.setText(currency);
        }
    }

    public void backButton(View view) {
        Intent intent = new Intent(convertActivity.this, mainActivity.class);
        convertActivity.this.startActivity(intent);
    }


    public void convertButton(View view) throws JSONException, IOException {
        String convertFrom = fromLabel.getText().toString();
        System.out.println(convertFrom);
        String convertTo = toLabel.getText().toString();
        System.out.println(convertTo);
        double amount = Double.parseDouble(fromAmount.getText().toString());
        System.out.println(amount);
        conversion.sendHttpGetRequest(convertFrom, convertTo, amount);
    }
}


//TODO
// 1. Add a button to the conversion activity that will take the user back to the main activity
// 2. figure out how to display the conversion result in the provided TextView
// 3. Move networking code to a separate class
