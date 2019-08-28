package com.example.bit_android;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetJobCategory extends AsyncTask<Void, Void, Void> {

    static ArrayList<String> jobCats = new ArrayList<>();

    public GetJobCategory(){}

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            GetHttpHelper getHttpHelper = new GetHttpHelper();
            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Client/getJobCat.php");

            try {
                JSONArray ja = new JSONArray(getHttpHelper.Read(url));
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo2 = ja.getJSONObject(i);
                    jobCats.add(jo2.getString("Description"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Read From JSON", e.toString());
        }
        return null;
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Client_Request.GetJobCats(jobCats);
    }
}

