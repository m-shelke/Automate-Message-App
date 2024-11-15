package com.example.automatemessageapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

//    Declaring .xml variable here
    EditText edNum,edMessage;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        finding .xml tags id here
        edNum = findViewById(R.id.edNum);
        edMessage = findViewById(R.id.edMessage);
        btSend = findViewById(R.id.btSend);

//        Handler class for delay for 10 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                after 5 sec. visibility of the edNum,edMessage,btSend as a GONE
                        edNum.setVisibility(View.GONE);
                        edMessage.setVisibility(View.GONE);
                        btSend.setVisibility(View.GONE);
            }
        },10000);

//       explicitly requesting SEND_SMS and READ_SMS permission from user and devices
        ActivityCompat.requestPermissions(MainActivity.this,new  String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
    }

//       creating method for sending message and handle it
    public void sendSMS(View view){

//        getting  data from the .xml tags and stored it to variable
        String getNumber = edNum.getText().toString().trim();
        String getMessage = edMessage.getText().toString().trim();

//        edNum.setVisibility(View.GONE);
//        edMessage.setVisibility(View.GONE);
//        btSend.setVisibility(View.GONE);


//        SmsManager class for sending and receiving message
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(getNumber,null,getMessage,null,null);
    }
}