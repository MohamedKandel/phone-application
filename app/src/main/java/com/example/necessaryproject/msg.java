package com.example.necessaryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class msg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
        }
        final TextView phone = findViewById(R.id.txt_phone);
        final EditText msg = findViewById(R.id.txt_message);
        Button btn_send = findViewById(R.id.btn_send);
        Button btn_back = findViewById(R.id.btn_back);

        phone.setText(myPhone.phone);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(phone.getText()+"" , msg.getText()+"");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    protected void sendSMS(String phone,String msg) {
        try {

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, msg, null, null);
            Toast.makeText(this,"Message is sent",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}