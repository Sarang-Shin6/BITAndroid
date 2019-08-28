package com.example.bit_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Contractor_Job extends Fragment implements AdapterView.OnItemClickListener{

    static ArrayList<ConJob> jobs = new ArrayList<>();
    static ArrayList<String> jobHeader = new ArrayList<>();
    static ListView lstJobs;
    private Contractor_Job.ConJobListener listener;
    private static Context context = null;
    public boolean firsttime = true;

    public Contractor_Job(){}

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Contractor_Job.ConJobListener){
            listener = (Contractor_Job.ConJobListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must use ConJobListener to hold the fragment");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contractor__job, container, false);
        lstJobs = view.findViewById(R.id.lstJob);
        lstJobs.setOnItemClickListener(this);
        return view;
    }

    public void onStart() {
        super.onStart();
        final GetContractorJob getContractorJob = new GetContractorJob();
        if (firsttime) {
            getContractorJob.execute();
            firsttime = false;
        }
        context = getActivity();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ConJob cj = jobs.get(i);
        Intent intent = new Intent(getContext(), Contractor_Job_Detail.class)
                .putExtra("jobid",cj.getJobID())
                .putExtra("duedate",cj.getDueDate())
                .putExtra("name",cj.getCompanyName())
                .putExtra("clientname",cj.getClientName())
                .putExtra("phone",cj.getPhone())
                .putExtra("branch",cj.getBranch())
                .putExtra("address",cj.getAddress())
                .putExtra("desc",cj.getJobdesc())
                .putExtra("status",cj.getStatus())
                .putExtra("loggedkm",cj.getLoggedkm())
                .putExtra("duration",cj.getDuration())
                .putExtra("jobcat",cj.getJobcat());
        startActivity(intent);
    }

    public interface ConJobListener {
        void GetJobs();
    }

    static void getData(ArrayList<ConJob> jobsList) {

        jobs.clear();
        for (int i = 0; i < jobsList.size(); i++) {
            jobs.add(jobsList.get(i));
        }
        jobHeader.clear();
        for (int i = 0; i < jobs.size(); i++) {
            String header = "Company: " + jobs.get(i).getCompanyName() + " - Category: " + jobs.get(i).getJobcat() + " [" + jobs.get(i).getStatus() + "]";
            jobHeader.add(header);
        }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, jobHeader);
        lstJobs.setAdapter(adapter);

    }
}
