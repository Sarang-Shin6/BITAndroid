package com.example.bit_android;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Client_Request extends Fragment{
    private ClientRequestListener listener;
    Button btnSend, btnPicture;
    static ArrayList<String> jobCats = new ArrayList<>();
    static ArrayList<String> branches = new ArrayList<>();
    static Spinner cboBranch, cboJobCats;
    static EditText txtDesc, txtDueDate;
    private static Context context = null;
    final Calendar myCalendar = Calendar.getInstance();
    static NotificationManager mNotific;

    public Client_Request(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client__request, container, false);
        btnSend = view.findViewById(R.id.btnSend);
        btnPicture = view.findViewById(R.id.btnPicture);
        cboBranch = view.findViewById(R.id.cboBranch);
        cboJobCats = view.findViewById(R.id.cboJobCat);
        txtDesc = view.findViewById(R.id.txtDesc);
        txtDueDate = view.findViewById(R.id.txtDueDate);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String duedate = txtDueDate.getText().toString(),
                desc = txtDesc.getText().toString();

                // Validation
                try {
                    if (desc.isEmpty() || duedate.isEmpty()) {
                        Toast.makeText(context, "Please make sure all fields are filled out.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = dateFormat.parse(duedate);
                    Date today = new Date();

                    Calendar c = Calendar.getInstance();
                    c.setTime(dt);
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


                    if (dayOfWeek == 1 || dayOfWeek == 7) {
                        Toast.makeText(context, "Please enter a weekday only.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (dt.before(today)) {
                        Toast.makeText(context, "Please enter a future date.", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int branchPos = cboBranch.getSelectedItemPosition(), jobcat = cboJobCats.getSelectedItemPosition() + 1;
                String companyid = GetClientBranch.ids.get(branchPos);
                Context context = getContext();
                SendClientRequest sendClientRequest = new SendClientRequest(Integer.toString(jobcat), duedate, desc, companyid, context);
                sendClientRequest.execute();
            }
        });
        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });


        return view;
    }

    public interface ClientRequestListener {
        void SendRequest();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClientRequestListener){
            // Forcing the listener to be listener of activity/context rather than the fragment
            listener = (ClientRequestListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must use ClientRequestListener to hold the fragment");
        }


    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDueDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void onStart() {
        super.onStart();
        GetJobCategory gjc = new GetJobCategory();
        GetClientBranch gcb = new GetClientBranch();
        gjc.execute();
        gcb.execute();
        context = getActivity();
        mNotific = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        txtDueDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public static void GetJobCats(ArrayList<String> pJobCats) {
        for (String s: pJobCats) {
            jobCats.add(s);
        }
        ArrayAdapter jobCatAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, jobCats);
        cboJobCats.setAdapter(jobCatAdapter);

    }

    public static void GetBranches(ArrayList<String> pBranches) {
        for (String s: pBranches) {
            branches.add(s);
        }
        ArrayAdapter branchAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, branches);
        cboBranch.setAdapter(branchAdapter);
    }

    public static void ShowNotification(){
        CharSequence name = "requestSent";
        String desc = "JobRequestSent!";
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
        String Body = "Your Job Request has been sent. ";
        Notification n = new Notification.Builder(context, ChannelID)
                .setContentTitle("Sent!")
                .setContentText(Body)
                .setBadgeIconType(R.mipmap.ic_launcher)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .build();

        mNotific.notify(ncode, n);

        cboBranch.setSelection(0);
        cboJobCats.setSelection(0);
        txtDesc.setText("");
        txtDueDate.setText("");
    }
}
