package com.alex.androidintentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button mBtnDial, mBtnCall, mBtnSms, mBtnCamera,mBtnEmail, mBtnShare, mBtnMpesa, mBtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBtnDial = findViewById(R.id.btnDial);
        mBtnCall = findViewById(R.id.btnCall);
        mBtnSms = findViewById(R.id.btnSms);
        mBtnCamera = findViewById(R.id.btnCamera);
        mBtnEmail = findViewById(R.id.btnMail);
        mBtnShare = findViewById(R.id.btnShare);
        mBtnMpesa = findViewById(R.id.btnPesa);
        mBtnHome = findViewById(R.id.btnHome);

        //Dial
        mBtnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "07";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        //Call
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0765546256"));
                if (ContextCompat.checkSelfPermission(SecondActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        //SMS
        mBtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:0726206546");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Niaje msee");
                startActivity(intent);

            }
        });

        //Camera
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 1);
            }
        });

        //Email
        mBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","alexngibuini6@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Job Application");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Sir, I hope this finds you well, this is in regard to your job posting.");
                startActivity(Intent.createChooser(emailIntent, "Sending Email from this app"));
            }
        });

        //Share
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app from king.com!");
                startActivity(shareIntent);
            }
        });

        //Mpesa
        mBtnMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent simToolKitLaunchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.android.stk");
                if(simToolKitLaunchIntent != null) {
                    startActivity(simToolKitLaunchIntent);
                }
            }
        });

        //Home
        mBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent goHome = new Intent(SecondActivity.this,MainActivity.class);
            startActivity(goHome);
            }
        });


    }
}
