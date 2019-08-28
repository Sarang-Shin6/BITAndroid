package com.example.bit_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Client_Job_Detail extends AppCompatActivity {
    ListView lstJobDetail;
    Bundle bundle;
    ArrayList<String> jobDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_job_detail);
        lstJobDetail = findViewById(R.id.lstJobDetail);
        bundle = getIntent().getExtras();
        jobDetails = new ArrayList<>();
        jobDetails.add("Job ID: \n" + bundle.getString("jobid"));

        if (bundle.getString("startdate").equals("null")) {
            jobDetails.add("Start Date: \nNot Started");
        } else {
            jobDetails.add("Start Date: \n" + bundle.getString("startdate"));
        }

        if (bundle.getString("firstname").equals("null")) {
            jobDetails.add("Contractor: \nNo Contractor has been Assigned");
        } else {
            jobDetails.add("Contractor Name: \n" + bundle.getString("firstname") + " " + bundle.getString("lastname"));
        }

        jobDetails.add("Description: \n" + bundle.getString("description"));
        jobDetails.add("Job Category: \n" + bundle.getString("jobcategory"));
        jobDetails.add("Status: \n" + bundle.getString("status"));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, jobDetails);
        lstJobDetail.setAdapter(adapter);
    }

}
