package com.example.bit_android;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static android.content.ContentValues.TAG;

public class Login extends AsyncTask<Void, Void, Void> {

    Session session = new Session();
    GetHttpHelper httpHelper;
    Context context;
    String username, password;

    public Login(Context c, String u, String p){
        username = u;
        password = p;
        context = c;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Contractor Login
            IpAddress address = new IpAddress();
            URL contractorURL = new URL("http://" + address.IP + "/bitsite/Android/Contractor/contractorlogin.php?username=" + username + "&password=" + password);
            httpHelper = new GetHttpHelper();
            String contractorLogin = httpHelper.Read(contractorURL);
            if (contractorLogin != null) {

                try {
                    JSONArray ja = new JSONArray(contractorLogin);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        Session.client = false;
                        Session.id = jo.getString("staffid");
                        return null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // Client Login
            URL clientURL = new URL("http://" + IpAddress.IP + "/bitsite/Android/Client/clientLogin.php?username=" + username + "&password=" + password);
            String clientLogin = httpHelper.Read(clientURL);
            if (clientLogin != null) {

                try {
                    JSONArray ja = new JSONArray(clientLogin);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        Session.client = true;
                        Session.id = jo.getString("clientid");
                        return null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Log.d(TAG, "Read: " + ex.toString());
        }
        return null;
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.CheckLogin();
    }
}
