package com.example.bit_android;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetContractorComplete extends AsyncTask<Void, Void, Void> {
    static ArrayList<ConJob> jobs = new ArrayList<>();
    ConJob conJob;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            GetHttpHelper getHttpHelper = new GetHttpHelper();
            jobs.clear();

            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Contractor/getContractorCommenced.php?conid=" + Session.id);

            try {
                JSONArray ja = new JSONArray(getHttpHelper.Read(url));

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo2 = ja.getJSONObject(i);
                    conJob = new ConJob();
                    conJob.setJobID(jo2.getString("jobid"));
                    conJob.setDueDate(jo2.getString("preferredenddate"));
                    conJob.setCompanyName(jo2.getString("name"));
                    conJob.setBranch(jo2.getString("branch"));
                    conJob.setJobcat(jo2.getString("description"));
                    jobs.add(conJob);
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Contractor_Complete_Job.getData(jobs);
    }
}
