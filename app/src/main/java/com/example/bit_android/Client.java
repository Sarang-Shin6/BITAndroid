package com.example.bit_android;

//import android.app.Fragment;
import android.support.v4.app.*;
//import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Client extends AppCompatActivity implements Client_Job.ClientJobListener, Client_Contact.ClientContactListener, Client_Request.ClientRequestListener {

    private TextView ClientTitle;
    Fragment jobFrag, contactFrag, requestFrag;
    FragmentManager jobmanager;


    public Client(){
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_job:
                    ClientTitle.setText(R.string.title_job);
                    if (requestFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(requestFrag)
                                .commit();
                    }
                    if (contactFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(contactFrag)
                                .commit();
                    }
                    if (jobFrag.isHidden()) {
                        jobmanager.beginTransaction()
                                .show(jobFrag)
                                .commit();
                    }

                    return true;
                case R.id.navigation_contact:
                    ClientTitle.setText(R.string.title_contact);
                    if (requestFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(requestFrag)
                                .commit();
                    }
                    if (jobFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(jobFrag)
                                .commit();
                    }
                    if (contactFrag.isHidden()) {
                        jobmanager.beginTransaction()
                                .show(contactFrag)
                                .commit();
                    }
                    return true;
                case R.id.navigation_request:
                    ClientTitle.setText(R.string.title_request);
                    if (contactFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(contactFrag)
                                .commit();
                    }
                    if (jobFrag.isVisible()) {
                        jobmanager.beginTransaction()
                                .hide(jobFrag)
                                .commit();
                    }
                    if (requestFrag.isHidden()) {
                        jobmanager.beginTransaction()
                                .show(requestFrag)
                                .commit();
                    }
            }
            return false;
        }
    };

    private void setFrag(){
        jobFrag = jobmanager.findFragmentById(R.id.JobFragment);
        contactFrag = jobmanager.findFragmentById(R.id.ContactFragment);
        requestFrag = jobmanager.findFragmentById(R.id.RequestFragment);
    }

    private void setDefaultFrag(){
        setFrag();
            jobmanager.beginTransaction().hide(requestFrag).commit();
            jobmanager.beginTransaction().hide(contactFrag).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        ClientTitle = (TextView) findViewById(R.id.txtTitle);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        jobmanager = getSupportFragmentManager();
        setDefaultFrag();

    }

    @Override
    public void GetJobs() {

    }

    @Override
    public void GetContacts() {

    }

    @Override
    public void SendRequest() {

    }
}
