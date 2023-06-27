package com.example.currencyconverterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        private TextView fromLabel;
        private TextView toLabel;
        private TextView fromAmount;
        private TextView toAmount;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_convert);
            convert = findViewById(R.id.convert);
            fromLabel = findViewById(R.id.fromLabel);
            toLabel = findViewById(R.id.toLabel);
            fromAmount = findViewById(R.id.fromAmount);
            toAmount = findViewById(R.id.toAmount);

            String currency = getIntent().getStringExtra("currency");
            if(currency != null) {
                fromLabel.setText("USD");
                toLabel.setText(currency);
            }
        }

        public void convertButton(View view) {
            String convertFrom = fromAmount.getText().toString();
            String convertTo = toAmount.getText().toString();
            Double amount = Double.parseDouble(fromAmount.getText().toString());
            try {
                sendHttpGETRequest(convertFrom, convertTo, amount);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    private static void sendHttpGETRequest(String convertFrom, String convertTo, double amount) throws IOException, JSONException {

        DecimalFormat f = new DecimalFormat("00.00");
        String GET_URL = "https://v6.exchangerate-api.com/v6/7d20d4932f7305a978100fd5/pair/" + convertFrom + "/" + convertTo;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }in.close();

            JSONObject obj = new JSONObject(response.toString());
            Double exchangeRate = obj.getDouble("conversion_rate");
            System.out.println();
            System.out.println(f.format(amount) + " " + convertFrom + " = " + f.format(amount*exchangeRate) + " " + convertTo); //conversion result

        }
        else{
            System.out.println("Get request failed");
        }
    }

}
