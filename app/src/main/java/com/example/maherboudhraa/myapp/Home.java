package com.example.maherboudhraa.myapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends AppCompatActivity {
    String API="http://api.football-data.org/v1/competitions/398/leagueTable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DownloadTask task = new DownloadTask();
        task.execute(API);

    }




    class DownloadTask extends AsyncTask<String, Void, String> {
        public String result;


        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url = null;
            HttpURLConnection urlConnection = null;


            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                urlConnection.disconnect();
                Log.i("websitecontent result", "resultat getten " + result);
                // Log.i("websitecontent result", "resultat getten est deja affichier");
                return result;
            } catch (MalformedURLException e) {
                Log.i("websitecontent result", "web connection error");
                e.printStackTrace();

            } catch (IOException e) {
                Log.i("websitecontent result", "web connection error 2");
                e.printStackTrace();

            }


            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("websitecontent result", "debut parsing " + result);


            try {
                //  result=result.toString();
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=new JSONArray(jsonObject.getJSONArray("standing"));
                JSONObject jsonobj;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobj = jsonArray.getJSONObject(i);

                    Log.i("websitecontent result", jsonobj.getString("position")+"\t"+jsonobj.getString("teamName")+"\t"+jsonobj.getString("points"));
                    Toast.makeText(getApplicationContext(), jsonobj.getString("teamName") + "  " + jsonobj.getString("points"), Toast.LENGTH_SHORT).show();
                }



            } catch (JSONException e) {
                Log.i("websitecontent result", "json error");
                e.printStackTrace();

            }

        }
}}
