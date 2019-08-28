package com.example.bit_android;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendJobCompletion  extends AsyncTask<Void, Void, Void> {

    String duration, kms, jobid, response;
    Context context;

    public SendJobCompletion (String duration, String kms, String jobid, Context pcontext){
        this.duration = duration;
        this.kms = kms;
        this.jobid = jobid;
        context = pcontext;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Contractor/ContractorComplete.php?jobid=" + jobid + "&dur=" + duration + "&km=" + kms);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                response = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (response != null && response != "false") {
            Contractor_Complete_Job.ShowNotification();
        }
    }
}
