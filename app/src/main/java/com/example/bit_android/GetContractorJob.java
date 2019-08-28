package com.example.bit_android;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetContractorJob extends AsyncTask<Void, Void, Void> {
    static ArrayList<ConJob> jobs = new ArrayList<>();
    ConJob conJob;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            GetHttpHelper getHttpHelper = new GetHttpHelper();
            jobs.clear();

            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Contractor/getContractorJobs.php?conid=" + Session.id);

            try {
                JSONArray ja = new JSONArray(getHttpHelper.Read(url));

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo2 = ja.getJSONObject(i);
                    conJob = new ConJob();
                    conJob.setJobID(jo2.getString("JobID"));
                    conJob.setDueDate(jo2.getString("PreferredEndDate"));
                    conJob.setCompanyName(jo2.getString("Name"));
                    conJob.setClientFirst(jo2.getString("FirstName"));
                    conJob.setClientLast(jo2.getString("LastName"));
                    conJob.setPhone(jo2.getString("Phone"));
                    conJob.setBranch(jo2.getString("Branch"));
                    conJob.setStreet(jo2.getString("Street"));
                    conJob.setState(jo2.getString("State"));
                    conJob.setSuburb(jo2.getString("Suburb"));
                    conJob.setPostcode(jo2.getString("Postcode"));
                    conJob.setJobdesc(jo2.getString("jobdesc"));
                    conJob.setJobcat(jo2.getString("jobcatdesc"));
                    conJob.setStatus(jo2.getString("statusdesc"));
                    conJob.setLoggedkm(jo2.getString("loggedkm"));
                    conJob.setDuration(jo2.getString("duration"));
                    conJob.GetFullNameAddress();
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Contractor_Job.getData(jobs);
    }
}

