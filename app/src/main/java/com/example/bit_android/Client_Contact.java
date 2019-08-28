package com.example.bit_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Client_Contact extends Fragment {
    ClientContactListener listener;
    Button btnCall, btnEmail, btnWebsite;

    public Client_Contact(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client__contact, container, false);
        btnCall = view.findViewById(R.id.btnCall);
        btnEmail = view.findViewById(R.id.btnSMS);
        btnWebsite = view.findViewById(R.id.btnWebsite);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallIntent();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmailIntent();
            }
        });

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebsiteIntent();
            }
        });
        return view;
    }

    public interface ClientContactListener{
        void GetContacts();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClientContactListener){
            listener = (ClientContactListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must use ClientContactListener to hold the fragment");
        }
    }

    private void EmailIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_EMAIL, new String[]{"contact-us@bit.com"})
//                .putExtra(Intent.EXTRA_SUBJECT, "test email")
//                .putExtra(Intent.EXTRA_TEXT, "hi")
                .setType("message/rfc822");
        startActivity(intent);
    }

    private void WebsiteIntent() {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.15/bitsite/"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.19.29.69/bitsite/"));
        startActivity(intent);
    }

    private void CallIntent() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(02)28384848"));
        startActivity(intent);
    }
}
