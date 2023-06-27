package com.example.currencyconverterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity  extends AppCompatActivity {

    private Button EUR;
    private Button CAD;
    private Button JPY;
    private Button KRW;
    private Button AUD;
    private Button INR;
    private Button OTHER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EUR = findViewById(R.id.EUR);
        CAD = findViewById(R.id.CAD);
        JPY = findViewById(R.id.JPY);
        KRW = findViewById(R.id.KRW);
        AUD = findViewById(R.id.AUD);
        INR = findViewById(R.id.INR);
        OTHER = findViewById(R.id.OTHER);
    }

    public void EURButton(View view) {
        String currency = "EUR";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void CADButton(View view) {
        String currency = "CAD";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void JPYButton(View view) {
        String currency = "JPY";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void KRWButton(View view) {
        String currency = "KRW";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void AUDButton(View view) {
        String currency = "AUD";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void INRButton(View view) {
        String currency = "INR";
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        intent.putExtra("currency", currency);
        mainActivity.this.startActivity(intent);
    }

    public void OTHERButton(View view) {
        Intent intent = new Intent(mainActivity.this, convertActivity.class);
        mainActivity.this.startActivity(intent);
    }
}
