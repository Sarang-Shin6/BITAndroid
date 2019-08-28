package com.example.bit_android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;


public class Contractor_TimePicker extends AppCompatActivity {
    Button btnSend;
    TimePicker tp;
    FragmentManager manager;
    private static final int REQUEST_CODE = 100;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor__time_picker);
        btnSend = findViewById(R.id.btnSendSMS);
        tp = findViewById(R.id.timePicker);
        activity = this;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SmsBody = "Dear " + Contractor_Job_Detail.clientName + ",\nYour Contractor from BIT Services will be arriving today at " + tp.getCurrentHour() + ":" + tp.getCurrentMinute() + ".";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Contractor_Job_Detail.phone , null));
                intent.putExtra("sms_body", SmsBody);
                startActivity(intent);
            }
        });
    }
}
