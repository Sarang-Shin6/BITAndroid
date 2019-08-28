package com.example.bit_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class Client_Job extends Fragment implements AdapterView.OnItemClickListener{
    static ArrayList<ClientJob> jobs = new ArrayList<>();
    static ArrayList<String> jobHeader = new ArrayList<>();
    static ListView lstJobs;
    private ClientJobListener listener;
    private static Context context = null;
    private boolean firstTime = true;

    public Client_Job(){}

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClientJobListener){
            listener = (ClientJobListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must use ClientJobListener to hold the fragment");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client__job, container, false);
        lstJobs = view.findViewById(R.id.lstJob);
        lstJobs.setOnItemClickListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();
        final GetClientJob getClientJob = new GetClientJob();
        if (firstTime) {
            getClientJob.execute();
            firstTime = false;
        }
        context = getActivity();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ClientJob cj = jobs.get(i);
        Intent intent = new Intent(getContext(), Client_Job_Detail.class)
                .putExtra("jobid",cj.getJobID())
                .putExtra("startdate",cj.getStartDate())
                .putExtra("firstname",cj.getFirstname())
                .putExtra("lastname",cj.getLastName())
                .putExtra("description",cj.getJobDesc())
                .putExtra("jobcategory",cj.getJobCat())
                .putExtra("status",cj.getStatus());
        startActivity(intent);
    }

    public interface ClientJobListener {
        void GetJobs();
    }

    static void getData(ArrayList<ClientJob> jobsList) {

        jobs.clear();
        for (int i = 0; i < jobsList.size(); i++) {
            jobs.add(jobsList.get(i));
        }
        jobHeader.clear();
        for (int i = 0; i < jobs.size(); i++) {
            String header = "Job ID: " + jobs.get(i).getJobID() + " - Category: " + jobs.get(i).getJobCat() + " [" + jobs.get(i).getStatus() + "]";
            jobHeader.add(header);
        }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, jobHeader);
        lstJobs.setAdapter(adapter);

    }
}
