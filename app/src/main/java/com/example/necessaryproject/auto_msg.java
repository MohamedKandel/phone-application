package com.example.necessaryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class auto_msg extends AppCompatActivity {

    Spinner spn;
    TextView phone;
    Button btn_send;
    Button btn_back;
    Toast ts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_msg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
        }
        phone = findViewById(R.id.txt_phone);
        spn = findViewById(R.id.spn);
        btn_back = findViewById(R.id.btn_back);
        phone.setText(myPhone.phone);
        btn_send = findViewById(R.id.btn_send);
        spn.setSelection(0);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(phone.getText().toString());
            }
        });

        /*spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sendSMS(phone.getText()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    protected void sendSMS(String phone) {
        try {
            String msg="";
            int index = spn.getSelectedItemPosition();
            switch (index) {
                case 0 :
                    msg = "";
                    break;
                case 1 :
                    msg = "أنا مشغول ، سوف اتصل بك لاحقًا";
                    break;
                case 2 :
                    msg = "أنا في الطريق";
                    break;
                case 3 :
                    msg = "عذرًا ، أنا مرهق لا استطيع التحدث الآن";
                    break;
                case 4 :
                    msg = "أهلًا بك ، مرحبًا";
                    break;
                default:
                    msg = "";
                    break;
            }
            if ("".equals(msg)) {
            } else {
                //================================================
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phone.toString(), null, msg, null, null);
                //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Message is sent", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}