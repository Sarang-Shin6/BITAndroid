package com.example.bit_android;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static Button btnLogin;
    EditText txtUserName, txtPassword;
    static Context context;
//    static NotificationManager mNotific;

    static Animation blink;
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        txtUserName = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        context = getApplicationContext();
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        sound = MediaPlayer.create(getApplicationContext(),
                R.raw.but_click);
//        mNotific = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login login = new Login(getApplicationContext(), txtUserName.getText().toString(), txtPassword.getText().toString());
                login.execute();
                sound.start();
                btnLogin.setEnabled(false);
            }
        });
    }

    static void CheckLogin() {
        if (Session.id == null) {
            Toast.makeText(context, "Invalid Login. Please try again.", Toast.LENGTH_LONG).show();
            MainActivity.btnLogin.startAnimation(blink);
        } else {
            if (Session.client) {
                Intent intent = new Intent(context, Client.class);
//                notification();
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, Contractor.class);
//                notification();
                context.startActivity(intent);
            }

        }
        btnLogin.setEnabled(true);

    }

//    static void notification (){
//        CharSequence name = "loggedin";
//        String desc = "loginNotif!";
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        final String ChannelID = "my_channel_01";
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel mChannel = new NotificationChannel(ChannelID, name, importance);
//            mChannel.setDescription(desc);
//            mChannel.setLightColor(R.color.colorPrimary);
//            mChannel.canShowBadge();
//            mChannel.setShowBadge(true);
//            mNotific.createNotificationChannel(mChannel);
//        }
//
//        final int ncode = 101;
//        String Body = "You have logged into BIT";
//        Notification n = new Notification.Builder(context, ChannelID)
//                .setContentTitle("Welcome!")
//                .setContentText(Body)
//                .setBadgeIconType(R.mipmap.ic_launcher)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setAutoCancel(true)
//                .build();
//
//        mNotific.notify(ncode, n);
//    }
}
