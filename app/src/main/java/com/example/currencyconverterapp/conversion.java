package com.example.currencyconverterapp;


import static com.example.currencyconverterapp.convertActivity.toAmount;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class conversion extends AppCompatActivity {
    private DecimalFormat f = new DecimalFormat("00.00");
    void sendHttpGetRequest(String convertFrom, String convertTo, double amount) {
        new AsyncTask<Void, Void, Double>() {
            @Override
            protected Double doInBackground(Void... voids) {
                try {
                    String GET_URL = "https://v6.exchangerate-api.com/v6/7d20d4932f7305a978100fd5/pair/" + convertFrom + "/" + convertTo;
                    URL url = new URL(GET_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        JSONObject obj = new JSONObject(response.toString());
                        return obj.getDouble("conversion_rate") * amount;
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Double result) {
                if (result != null) {
                    toAmount.setText(f.format(result));
                } else {
                    System.out.println("Get request failed");
                }
            }
        }.execute();
    }


}
