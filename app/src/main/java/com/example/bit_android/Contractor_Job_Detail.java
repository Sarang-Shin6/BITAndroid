package com.example.bit_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Contractor_Job_Detail extends AppCompatActivity {
    ListView lstJobDetail;
    Bundle bundle;
    ArrayList<String> jobDetails;
    String Address, jobID, companyName, branch;
    Button btnEmail, btnSms, btnMaps;
    public static String clientName, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor__job__detail);
        lstJobDetail = findViewById(R.id.lstJobDetail);
        btnEmail = findViewById(R.id.btnEmail);
        btnSms = findViewById(R.id.btnSMS);
        btnMaps = findViewById(R.id.btnMaps);
        bundle = getIntent().getExtras();
        jobDetails = new ArrayList<>();
        jobID = bundle.getString("jobid");
        jobDetails.add("Job ID: \n" + jobID);
        jobDetails.add("Due Date: \n" + bundle.getString("duedate"));
        companyName = bundle.getString("name");
        jobDetails.add("Company Name: \n" + companyName);
        clientName = bundle.getString("clientname");
        jobDetails.add("Client Name: \n" + clientName);
        phone = bundle.getString("phone");
        jobDetails.add("Phone: \n" + phone);
        branch = bundle.getString("branch");
        jobDetails.add("Branch: \n" + branch);
        Address =  bundle.getString("address");
        jobDetails.add("Address: \n" + Address);
        jobDetails.add("Description: \n" + bundle.getString("desc"));
        jobDetails.add("Status: \n" + bundle.getString("status"));

        if (bundle.getString("startdate") != "null") {
            jobDetails.add("Start Date: \n" + bundle.getString("startdate"));
        } else {
            jobDetails.add("Not Started");
        }
        jobDetails.add("Job Category: \n" + bundle.getString("jobcat"));
        if (bundle.getString("duration") != "null") {
            jobDetails.add("Duration: \n" + bundle.getString("duration"));
        } else {
            jobDetails.add("Not Logged");
        }
        if (bundle.getString("loggedkm") != "null") {
            jobDetails.add("Logged Km's: \n" + bundle.getString("loggedkm"));
        } else {
            jobDetails.add("Not Logged");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, jobDetails);
        lstJobDetail.setAdapter(adapter);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locateClient();
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Contractor_TimePicker.class);
                startActivity(intent);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailBody = "Job no.: " + jobID + " for " + companyName + " represented by " + clientName + " for their branch in " + branch + " at " + Address + "Has been successfully completed";
                Intent emailIntent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{ "BITServices@bit.com"})
                        .putExtra(Intent.EXTRA_SUBJECT, "Job Complete")
                        .putExtra(Intent.EXTRA_TEXT, emailBody)
                        .setType("message/rfc822");
                startActivity(emailIntent);
            }
        });



    }

    private void locateClient(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //insert client address details below

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Address);
                // you can read above from your database
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        }, 1000);
    }

}
