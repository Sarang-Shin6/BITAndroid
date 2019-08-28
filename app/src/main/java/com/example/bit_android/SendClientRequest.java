package com.example.bit_android;

import android.os.AsyncTask;
import android.content.Context;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendClientRequest extends AsyncTask<Void, Void, Void> {
    private String preferredenddate, description, jobcatid, companyid, clientid, response;
    Context context;


    public SendClientRequest(String jobcatid, String preferredenddate, String description, String companyid, Context pcontext) {
        setJobcatid(jobcatid);
        setPreferredenddate(preferredenddate);
        setDescription(description);
        setCompanyid(companyid);
        context = pcontext;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://" + IpAddress.IP + "/bitsite/Android/Client/sendJobRequest.php?clientid=" + Session.id + "&companyid=" + companyid + "&jobcat=" + jobcatid + "&desc=" + description + "&duedate=" + preferredenddate);

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

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (response != null && response != "false") {
            Client_Request.ShowNotification();
        }
    }

    public String getClientid() {
        return clientid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public String getJobcatid() {
        return jobcatid;
    }

    public String getPreferredenddate() {
        return preferredenddate;
    }

    public String getDescription() {
        return description;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJobcatid(String jobcatid) {
        this.jobcatid = jobcatid;
    }

    public void setPreferredenddate(String preferredenddate) {
        this.preferredenddate = preferredenddate;
    }

}
