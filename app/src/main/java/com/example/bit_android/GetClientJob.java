package com.example.bit_android;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetClientJob extends AsyncTask<Void, Void, Void>{

    static ArrayList<ClientJob> jobs = new ArrayList<>();
    ClientJob clientJob;

    public GetClientJob(){}

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            GetHttpHelper getHttpHelper = new GetHttpHelper();
            jobs.clear();
            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Client/getClientJobs.php?id=" + Session.id);

            try {
                JSONArray ja = new JSONArray(getHttpHelper.Read(url));

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo2 = ja.getJSONObject(i);
                    clientJob = new ClientJob();
                    clientJob.setStartDate(jo2.getString("StartDate"));
                    clientJob.setJobID(jo2.getString("JobID"));
                    clientJob.setFirstname(jo2.getString("FirstName"));
                    clientJob.setLastName(jo2.getString("LastName"));
                    clientJob.setJobDesc(jo2.getString("description"));
                    clientJob.setJobCat(jo2.getString("Description"));
                    clientJob.setStatus(jo2.getString("Status"));
                    jobs.add(clientJob);
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
        Client_Job.getData(jobs);
    }
}
