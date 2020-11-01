package com.example.necessaryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int READ_CONTACT_REQUEST = 1;

    EditText txt_phone;
    Button btn_call;
    Button btn_sms;
    Button btn_send;
    Button btn_get;
    Button btn_auto;
    Cursor curs;
    List<String> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE}, READ_CONTACT_REQUEST);

        } else {
            Toast.makeText(this,"Application Can't Take All Permissions, Go To settings and allow all permissions",Toast.LENGTH_LONG).show();
        }

        final Intent call = new Intent(Intent.ACTION_CALL);
        final Intent sms = new Intent(Intent.ACTION_VIEW);
        final Intent send = new Intent(this,msg.class);
        final Intent auto = new Intent(this,auto_msg.class);
        final Intent get_con = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);


        txt_phone = findViewById(R.id.txt_phone);
        btn_call = findViewById(R.id.btn_call);
        btn_sms = findViewById(R.id.btn_sms);
        btn_send = findViewById(R.id.btn_send);
        btn_get = findViewById(R.id.btn_get);
        btn_auto = findViewById(R.id.btn_auto);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.setData(Uri.parse("tel:" + txt_phone.getText()));
                startActivity(call);
                txt_phone.setText("");
                txt_phone.requestFocus();
            }
        });
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms.setData(Uri.parse("sms:" + txt_phone.getText()));
                startActivity(sms);
                txt_phone.setText("");
                txt_phone.requestFocus();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPhone.phone = txt_phone.getText()+"";
                startActivity(send);
            }
        });
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(get_con,1);
            }
        });

        btn_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPhone.phone = txt_phone.getText()+"";
                startActivity(auto);
            }
        });

    }

    @Override
    protected void onActivityResult (int Request_CODE,int Result_CODE, Intent Data) {
        super.onActivityResult(Request_CODE, Result_CODE, Data);

        Uri uri = Data.getData();
        Cursor c = getContentResolver().query(uri, null, null, null, null);
        c.moveToFirst();
        int phone_index = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String phone = c.getString(phone_index);
        txt_phone.setText(phone);

        if (Request_CODE == READ_CONTACT_REQUEST) {
            Uri u = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            curs = getContentResolver().query(u, null, null, null, null);
        }
    }

    /*protected void fillList(int count) {

        int i = 0;

        //Uri u = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //curs = getContentResolver().query(u, null, null, null, null);

        while (curs.moveToNext()) {
            int phone_index = curs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int Name_index = curs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String phone = curs.getString(phone_index);
            String name = curs.getString(Name_index);
            contacts.add(name + "\n               " + phone);
            i++;
            if (i == count)
                break;
        }
        ArrayAdapter<String> contact = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        lv.setAdapter(contact);
    }*/

}