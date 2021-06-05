package com.example.genericfinder.httpConnector;

import android.os.AsyncTask;
import android.security.NetworkSecurityPolicy;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String receiveMsg = null;

        try {
            String str;
            URL url = new URL(strings[0]);

            System.out.println(url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//            String sendMsg = strings[1].replaceAll("\\`", "\\\\`" ).replaceAll("\\\"", "\\\\\"" ).replaceAll("\\'", "\\\\'" );
//            osw.write(sendMsg);
            osw.flush();

                System.out.println(conn.getResponseCode());
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder buffer = new StringBuilder();

                    while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                receiveMsg = buffer.toString();
            } else {
                Log.i("통신 결과", conn.getResponseCode()+"에러");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
