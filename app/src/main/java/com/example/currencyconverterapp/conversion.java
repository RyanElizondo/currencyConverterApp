package com.example.currencyconverterapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class conversion {

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
