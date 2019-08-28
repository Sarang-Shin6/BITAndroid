package com.example.bit_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Contractor extends AppCompatActivity implements Contractor_Job.ConJobListener, Contractor_Complete_Job.ConCompJobListener{

    private TextView txtTitle;
    Fragment jobFrag, compFrag;
    FragmentManager jobmanager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_job:
                    txtTitle.setText(R.string.title_job);
                    if (compFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(compFrag)
                                .commit();
                    }
                    if (jobFrag.isHidden()) {
                        jobmanager.beginTransaction()
                                .show(jobFrag)
                                .commit();
                    }
                    return true;

                case R.id.navigation_complete:
                    txtTitle.setText(R.string.title_complete);
                    if (jobFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(jobFrag)
                                .commit();
                    }
                    if (compFrag.isHidden()) {
                        jobmanager.beginTransaction()
                                .show(compFrag)
                                .commit();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        jobmanager = getSupportFragmentManager();
        setFrag();
    }

    private void setFrag(){
        jobFrag = jobmanager.findFragmentById(R.id.ConJobFragment);
        compFrag = jobmanager.findFragmentById(R.id.ConCompJobFragment);
        jobmanager.beginTransaction().hide(compFrag).commit();
        jobmanager.beginTransaction().show(jobFrag).commit();
    }

    @Override
    public void GetJobs() {

    }

    @Override
    public void CompleteJob() {

    }
}
