package com.example.bit_android;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class Contractor_Complete_Job extends Fragment {
    private ConCompJobListener listener;
    static Context context;
    static EditText txtDur, txtKm;
    static Button btnComplete;
    static Spinner lstJob;
    static int jobPos;
    static ArrayList<ConJob> jobs = new ArrayList<>();
    static ArrayList<String> jobHeader = new ArrayList<>();
    static NotificationManager mNotific;
    final static GetContractorComplete getContractorComplete = new GetContractorComplete();
    private boolean firstTimeRead = true;


    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConCompJobListener){
            listener = (ConCompJobListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must use ConCompJobListener to hold the fragment");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contractor__complete__job, container, false);
        txtDur = view.findViewById(R.id.txtDur);
        txtKm = view.findViewById(R.id.txtKm);
        btnComplete = view.findViewById(R.id.btnComplete);
        lstJob = view.findViewById(R.id.lstJob);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String duration = txtDur.getText().toString(),
                        kms = txtKm.getText().toString();
                if (duration.isEmpty() || kms.isEmpty()) {
                    Toast.makeText(context, "Please make sure all fields are filled out.", Toast.LENGTH_LONG).show();
                    return;
                }
                jobPos = lstJob.getSelectedItemPosition();
                String jobid = jobs.get(jobPos).getJobID();
                Context context = getContext();
                SendJobCompletion sendJobCompletion = new SendJobCompletion(duration, kms, jobid, context);
                sendJobCompletion.execute();
                btnComplete.setEnabled(false);

            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        if (firstTimeRead) {
            getContractorComplete.execute();
            firstTimeRead = false;
        }
        context = getActivity();
        mNotific = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    public interface ConCompJobListener {
        void CompleteJob();
    }

    static void getData(ArrayList<ConJob> jobsList){
        jobs.clear();
        for (int i = 0; i < jobsList.size(); i++) {
            jobs.add(jobsList.get(i));
        }

        jobHeader.clear();
        for (int i = 0; i < jobs.size(); i++) {
//            String header = "JobID: " + jobs.get(i).getJobID() + " - Company: " + jobs.get(i).getCompanyName() + " - Branch: " + jobs.get(i).getBranch() + " - Due Date: " + jobs.get(i).getJobcat() + " [" + jobs.get(i).getDueDate() + "]";
            String header = jobs.get(i).getJobID() + ": " + jobs.get(i).getCompanyName() + ": " + jobs.get(i).getBranch();

            jobHeader.add(header);
        }
        if (jobHeader.isEmpty()) {
            jobHeader.add("No Jobs are active at the moment");
            btnComplete.setEnabled(false);
        }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, jobHeader);
        lstJob.setAdapter(adapter);
    }

    public static void ShowNotification(){
        CharSequence name = "completeRequest";
        String desc = "completeRequestSent!";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        final String ChannelID = "my_channel_01";
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(ChannelID, name, importance);
            mChannel.setDescription(desc);
            mChannel.setLightColor(R.color.colorPrimary);
            mChannel.canShowBadge();
            mChannel.setShowBadge(true);
            mNotific.createNotificationChannel(mChannel);
        }

        final int ncode = 101;
        String Body = "Your Job has been set as complete!";
        Notification n = new Notification.Builder(context, ChannelID)
                .setContentTitle("Sent!")
                .setContentText(Body)
                .setBadgeIconType(R.mipmap.ic_launcher)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .build();

        mNotific.notify(ncode, n);
        btnComplete.setEnabled(true);
        txtDur.setText("");
        txtKm.setText("");
        jobs.remove(jobPos);
        jobHeader.remove(jobPos);

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, jobHeader);
        lstJob.setAdapter(adapter);
    }
}
