package com.example.bit_android;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetClientBranch extends AsyncTask<Void, Void, Void> {
    static ArrayList<String> branches = new ArrayList<>();
    static ArrayList<String> ids = new ArrayList<>();


    public GetClientBranch(){}

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            GetHttpHelper getHttpHelper = new GetHttpHelper();
            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Client/getClientBranches.php?clientid=" + Session.id);

            try {
                JSONArray ja = new JSONArray(getHttpHelper.Read(url));
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo2 = ja.getJSONObject(i);
                    branches.add(jo2.getString("Branch"));
                    ids.add(jo2.getString("companyid"));
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
        Client_Request.GetBranches(branches);
    }
}
